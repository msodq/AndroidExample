package com.sodiq.postsecure.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mohamad Sodiq on 24/08/2015.
 */
public class DataPreferences {
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_TOKEN = "token";
    private static final String PREF_NAME = "postsecure";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public DataPreferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public int getUserId() {
        return pref.getInt(KEY_USER_ID, 0);
    }

    public String getToken() {
        return pref.getString(KEY_TOKEN, "");
    }

    public void clearCahce() {
        editor.clear();
        editor.commit();

    }
}
