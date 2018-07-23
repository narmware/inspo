package com.narmware.inspo.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by comp16 on 12/19/2017.
 */

public class SharedPreferencesHelper {

    private static final String IS_LOGIN="login";
    private static final String USER_ID="user_id";
    private static final String USER_PROF_IMG="user_img";
    private static final String SELECTION_FLAG="selection_flag";

    public static void setSelectionFlag(String selection_flag, Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit=pref.edit();
        edit.putString(SELECTION_FLAG,selection_flag);
        edit.commit();
    }

    public static String getSelectionFlag(Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        String selection_flag=pref.getString(SELECTION_FLAG,null);
        return selection_flag;
    }

    public static void setUserId(String user_id, Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit=pref.edit();
        edit.putString(USER_ID,user_id);
        edit.commit();
    }

    public static String getUserId(Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        String user_id=pref.getString(USER_ID,null);
        return user_id;
    }

    public static void setUserProfImg(String user_img, Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit=pref.edit();
        edit.putString(USER_PROF_IMG,user_img);
        edit.commit();
    }

    public static String getUserProfImg(Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        String user_img=pref.getString(USER_PROF_IMG,null);
        return user_img;
    }

    public static void setIsLogin(boolean login, Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit=pref.edit();
        edit.putBoolean(IS_LOGIN,login);
        edit.commit();
    }

    public static boolean getIsLogin(Context context)
    {
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        boolean login=pref.getBoolean(IS_LOGIN,false);
        return login;
    }

}
