package com.movile.common.model.common;

import com.movile.common.constants.ImageType;

import java.util.HashMap;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class ImagesWrapper extends HashMap<String, Image> {

    /**
     * This method retrieves the requested image type, If it is
     *
     * @param type
     *         Type to be recovered
     *
     * @return Image information
     */
    public Image getImage(ImageType type) {
        if (type == null) {
            return null;
        }

        return get(type.getType());
    }

}
