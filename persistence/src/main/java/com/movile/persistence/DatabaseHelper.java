package com.movile.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;
import com.movile.common.model.authentication.User;

import java.sql.SQLException;

/**
 * This is the database helper where the DB is created and updated
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
@Singleton
public class DatabaseHelper extends SQLiteOpenHelper {

    /** TAG for Logs **/
    private static final String TAG_LOG = DatabaseHelper.class.getName();

    /** DB name **/
    private static final String DB_NAME = "appisodes.db";

    /** DB version **/
    private static final int DB_VERSION = 2;

    /** Connection source **/
    protected AndroidConnectionSource mConnectionSource = new AndroidConnectionSource(this);

    /**
     * DB Helper constructor
     *
     * @param context
     *         Application context
     */
    @Inject
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        configBd(db);
    }

    /**
     * This method sets up the DB
     *
     * @param db
     *         DB Connection
     */
    private void configBd(SQLiteDatabase db) {
        configBd(db, null, null);
    }

    /**
     * This method configs the DB when a version update exists. If any of the DB version values is
     * null, then update process will be avoided and build process will continue
     *
     * @param db
     *         DB Connection
     * @param oldVersion
     *         Previous DB version
     * @param newVersion
     *         New DB version
     */
    private void configBd(SQLiteDatabase db, Integer oldVersion, Integer newVersion) {
        DatabaseConnection con = mConnectionSource.getSpecialConnection();
        boolean specialClear = false;
        if (con == null) {
            con = new AndroidDatabaseConnection(db, true);

            try {
                mConnectionSource.saveSpecialConnection(con);
                specialClear = true;
            } catch (SQLException e) {
                throw new IllegalStateException("Special connection could not be stored", e);
            }
        }

        try {
            if (oldVersion == null || newVersion == null) {
                onCreate();
            } else {
                onUpgrade(oldVersion, newVersion);
            }
        } finally {
            if (specialClear) {
                mConnectionSource.clearSpecialConnection(con);
            }
        }
    }

    /**
     * Creates the DB scheme
     */
    private void onCreate() {
        try {
            Log.i(TAG_LOG, "DB onCreate");
            TableUtils.createTable(mConnectionSource, User.class);

            Log.i(TAG_LOG, "DB created");
        } catch (SQLException e) {
            Log.e(TAG_LOG, "DB could not be created", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        configBd(db, oldVersion, newVersion);
    }

    /**
     * Updates the DB given the previous and the new DB version
     *
     * @param oldVersion
     *         Previous DB version
     * @param newVersion
     *         New DB version
     */
    private void onUpgrade(int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade called");

            TableUtils.dropTable(mConnectionSource, User.class, true);

            onCreate();
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "DB cannot be dropped", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This method returns a DAO
     * <p/>
     * Extracted from: https://goo.gl/6LIYy2
     *
     * @param clazz
     *         Instance of the requested DAO class
     * @param <D>
     *         DAO superclass
     * @param <T>
     *         Requested DAO class
     *
     * @return The DAO
     *
     * @throws SQLException
     *         If an connection error occurs
     */
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        // lookup the dao, possibly invoking the cached database config
        Dao<T, ?> dao = DaoManager.lookupDao(mConnectionSource, clazz);
        if (dao == null) {
            // try to use our new reflection magic
            DatabaseTableConfig<T> tableConfig = DatabaseTableConfigUtil
                    .fromClass(mConnectionSource, clazz);
            if (tableConfig == null) {
                /**
                 * TODO: we have to do this to get to see if they are using the deprecated
                 * annotations like
                 * {@link DatabaseFieldSimple}.
                 */
                dao = (Dao<T, ?>) DaoManager.createDao(mConnectionSource, clazz);
            } else {
                dao = (Dao<T, ?>) DaoManager.createDao(mConnectionSource, tableConfig);
            }
        }

        @SuppressWarnings("unchecked")
        D castDao = (D) dao;
        return castDao;
    }
}
