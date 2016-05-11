package com.movile.common.helpers;

import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.constants.Property;
import com.movile.common.utils.IOUtils;

import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.util.Properties;

/**
 * This class manages the properties files of the Appisodes app
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
@Singleton
public class PropertiesAdminHelper {

    /** Appisodes file properties * */
    private static final String APPISODES_PROPERTIES_FILE = "appisodes.properties";

    /** Loaded Appisodes File Properties * */
    private Properties mProperties;

    /** Current Context * */
    private Context context;

    /**
     * Properties admin constructor
     *
     * @param context
     *         Application context
     *
     * @throws IOException
     *         If an error occurs while reading the properties file
     */
    @Inject
    public PropertiesAdminHelper(Context context) throws IOException {
        this.context = context;
        mProperties = IOUtils.readProperties(context, APPISODES_PROPERTIES_FILE);
    }

    /**
     * This method returns a requested property
     *
     * @param property
     *         Specifies a property
     *
     * @return Requested property value
     */
    public String getProperty(Property property) {
        Validate.notNull(property, "The property cannot be null");
        String propertyValue;
        switch (property) {
            case CLIENT_ID:
            case CLIENT_SECRET:
                propertyValue = mProperties.getProperty(property.getProperty());
                break;
            default:
                throw new IllegalArgumentException(
                        String.format("Config Property Type not supported [%s]", property.name()));
        }

        return propertyValue;
    }

}
