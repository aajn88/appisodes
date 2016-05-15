package com.movile.appisodes.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.widget.ImageView;

import com.movile.appisodes.R;
import com.movile.common.utils.StringUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

/**
 * This is the Image Utils class for basic and common methods such as load image in a {@link
 * ImageView}
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class ImageUtils {

    /** Tag for logs **/
    private static final String TAG_LOG = ImageUtils.class.getName();

    /** Default image options builder **/
    private static final DisplayImageOptions.Builder DEFAULT_DISPLAY_IMAGE_OPTIONS_BUIDLER = new DisplayImageOptions.Builder()
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
            .displayer(new FadeInBitmapDisplayer(300, true, false, false))
            .showImageForEmptyUri(R.drawable.default_image)
            .showImageOnLoading(R.drawable.default_image).considerExifParams(true)
            .showImageOnFail(R.drawable.default_image).cacheOnDisk(true).cacheInMemory(true)
            .bitmapConfig(Config.ARGB_8888);

    /** Default display image options **/
    private static final DisplayImageOptions DEFAULT_DISPLAY_IMAGE_OPTIONS = DEFAULT_DISPLAY_IMAGE_OPTIONS_BUIDLER
            .build();

    /** Default rounded image options **/
    private static final DisplayImageOptions ROUND_DISPLAY_IMAGE_OPTIONS = DEFAULT_DISPLAY_IMAGE_OPTIONS_BUIDLER
            .displayer(new RoundedBitmapDisplayer(500)).build();

    /**
     * This method displays an image in a given image view
     *
     * @param view
     *         Target ImageView
     * @param path
     *         Image path
     * @param listener
     *         Listener for loading image result
     */
    public static void displayImage(ImageView view, String path, ImageLoadingListener listener) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            loader.displayImage(path, view, DEFAULT_DISPLAY_IMAGE_OPTIONS, listener);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            loader.clearMemoryCache();
        }
    }

    /**
     * This method displays a rounded image in a given image view
     *
     * @param view
     *         Target ImageView
     * @param path
     *         Image path
     * @param listener
     *         Listener for loading image result
     */
    public static void displayRoundImage(ImageView view, String path,
                                         ImageLoadingListener listener) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            loader.displayImage(path, view, ROUND_DISPLAY_IMAGE_OPTIONS, listener);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            loader.clearMemoryCache();
        }
    }

    /**
     * This method loads an image
     *
     * @param path
     *         Image path
     * @param listener
     *         Listener for loading image result
     */
    public static void loadImage(String path, ImageLoadingListener listener) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            loader.loadImage(path, DEFAULT_DISPLAY_IMAGE_OPTIONS, listener);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    /**
     * This method loads an image synchronously
     *
     * @param path
     *         Image path
     *
     * @return Image bitmap
     */
    public static Bitmap loadImageSync(String path) {
        ImageLoader loader = ImageLoader.getInstance();
        try {
            return loader.loadImageSync(path, DEFAULT_DISPLAY_IMAGE_OPTIONS);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method removes an image from caché
     *
     * @param url
     *         Image Url
     */
    public static void removeFromCache(String url) {
        try {
            MemoryCacheUtils.removeFromCache(url, ImageLoader.getInstance().getMemoryCache());
            DiskCacheUtils.removeFromCache(url, ImageLoader.getInstance().getDiskCache());
        } catch (Exception e) {
            Log.e(TAG_LOG, StringUtils
                    .format("An error occurs while removeing the URL [%s] from caché", url), e);
        }
    }
}
