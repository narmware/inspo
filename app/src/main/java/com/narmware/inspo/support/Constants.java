package com.narmware.inspo.support;

/**
 * Created by Darshan on 5/26/2015.
 */
public class Constants {
    public static final int PERMISSION_REQUEST_CODE = 1000;
    public static final int PERMISSION_GRANTED = 1001;
    public static final int PERMISSION_DENIED = 1002;

    public static final int REQUEST_CODE = 2000;

    public static final int FETCH_STARTED = 2001;
    public static final int FETCH_COMPLETED = 2002;
    public static final int ERROR = 2005;

    public static final String INTENT_EXTRA_ALBUM = "album";
    public static final String INTENT_EXTRA_IMAGES = "images";
    public static final String INTENT_EXTRA_LIMIT = "limit";
    public static final int DEFAULT_LIMIT = 10;

    public static final int DEFAULT_HEIGHT = 400;
    public static final int DEFAULT_WIDTH = 600;
    //Maximum number of images that can be selected at a time
    public static int limit;


    //Image fields
    public static final String IMG_ID = "id";
    public static final String IMG_NAME = "name";
    public static final String IMG_PATH = "path";
    public static final String IMG_SELECTED = "isSelected";
    public static final String IMG_ALBUM = "album";
    public static final String IMG_HEIGHT = "height";
    public static final String IMG_WIDTH = "width";

    public static final String HELP_WITH = "helpWith";
    public static final String LOOKING_FOR = "lookingFor";
    public static final String SKILLS = "skills";

    public static final String SET_PROFILE = "setProfile";
    public static final String SET_PORTFOLIO = "setPortfolio";

}
