package com.movile.common.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * This utility class offers basic and common I/O methods
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class IOUtils {

    /** The files default charset **/
    private static final String DEFAULT_CHARSET = "UTF-8";

    /** Private constructor to avoid instances **/
    private IOUtils() {}

    /**
     * This method reads the given properties file and returns the loaded properties
     *
     * @param context
     *         Application context
     * @param propertiesPath
     *         The properties file path
     *
     * @return The loaded properties
     *
     * @throws IOException
     *         If an error occurs while reading the properties file
     */
    public static Properties readProperties(Context context, String propertiesPath) throws
            IOException {
        InputStreamReader isr = new InputStreamReader(context.getAssets().open(propertiesPath),
                DEFAULT_CHARSET);
        Properties properties = new Properties();
        properties.load(isr);

        return properties;
    }

}
