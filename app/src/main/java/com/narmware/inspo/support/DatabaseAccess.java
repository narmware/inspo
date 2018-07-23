package com.narmware.inspo.support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.narmware.inspo.pojo.Image;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    List<Image> quesAnsList;

    //QuizPojo quesDetails;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void setImages(String id, String name, String path, String isSelected, String album, String height, String width) {

        ContentValues values = new ContentValues();
        values.put(Constants.IMG_ID, id);
        values.put(Constants.IMG_NAME, name);
        values.put(Constants.IMG_PATH, path);
        values.put(Constants.IMG_SELECTED, isSelected);
        values.put(Constants.IMG_ALBUM,album);
        values.put(Constants.IMG_HEIGHT,height);
        values.put(Constants.IMG_WIDTH,width);

        database.insert("profilephotos", null, values);
        //database.close();
    }

    public List<Image> getAllDetails() {
        quesAnsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM profilephotos", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //  String str=Integer.toString(cursor.getInt(0))+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3);
             long id= Long.parseLong(cursor.getString(0));
             String name=cursor.getString(1);
             String path=cursor.getString(2);
             boolean isSelected= Boolean.parseBoolean(cursor.getString(3));
            String album=cursor.getString(4);
            String height=cursor.getString(5);
            String width=cursor.getString(6);

            Image quesDetails = new Image(id,name,path,isSelected,album,height,width);
                quesAnsList.add(quesDetails);

            cursor.moveToNext();
        }
        cursor.close();
        //database.close();
        return quesAnsList;
    }

    public List<Image> getSelectedImages(String albumName) {
        quesAnsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM profilephotos where isSelected = 'true' and album = '"+ albumName+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //  String str=Integer.toString(cursor.getInt(0))+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3);
            long id= Long.parseLong(cursor.getString(0));
            String name=cursor.getString(1);
            String path=cursor.getString(2);
            boolean isSelected= Boolean.parseBoolean(cursor.getString(3));
            String album=cursor.getString(4);
            String height=cursor.getString(5);
            String width=cursor.getString(6);

            if(cursor.getString(0)!="id") {
                Image quesDetails = new Image(id, name, path, isSelected, album, height, width);
                quesAnsList.add(quesDetails);
            }

            cursor.moveToNext();
        }
        cursor.close();
        //database.close();
        return quesAnsList;
    }
    public List<Image> getSelectedImagesOfOtherAlbums(String albumName) {
        quesAnsList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM profilephotos where isSelected = 'true' and album <> '"+ albumName+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //  String str=Integer.toString(cursor.getInt(0))+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3);
            long id= Long.parseLong(cursor.getString(0));
            String name=cursor.getString(1);
            String path=cursor.getString(2);
            boolean isSelected= Boolean.parseBoolean(cursor.getString(3));
            String album=cursor.getString(4);
            String height=cursor.getString(5);
            String width=cursor.getString(6);

            Image quesDetails = new Image(id,name,path,isSelected,album,height,width);
            quesAnsList.add(quesDetails);

            cursor.moveToNext();
        }
        cursor.close();
        //database.close();
        return quesAnsList;
    }

    public void deleteAll()
    {
        database.execSQL("delete from profilephotos");

    }

    public void deleteSingle(long removedId,String albumName)
    {
        database.execSQL("delete from profilephotos where id = "+removedId +" and album = '"+albumName+"'");
    }

}