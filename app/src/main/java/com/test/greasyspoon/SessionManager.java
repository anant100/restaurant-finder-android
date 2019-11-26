package com.test.greasyspoon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences pref1;

    // Editor for Shared preferences
    Editor editor;
    Editor editor1;

    // Context
    Context _context;
    Context _context1;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Pref";
    private static final String PREF_NAME1 = "Pref1";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String TITLE = "tl";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public SessionManager(Context context,String title){
        _context1 = context;
        pref1 = _context1.getSharedPreferences(PREF_NAME1, PRIVATE_MODE);
        editor1 = pref1.edit();
    }
    public void createtitlesession(String tl)
    {
        editor1.putBoolean(IS_LOGIN, true);
    	  editor1.putString(TITLE,tl);
    	  editor1.commit();
    }
    public void deletetitlesession()
    {
    	editor1.clear();
        editor1.commit();
    	
    }
    public String getsessiontitle()
    {
    	 String maintitle = new  String();
         
    	 maintitle = pref1.getString(TITLE, null);
    	return maintitle;
    	
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }   

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public Boolean checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
        	//Toast.makeText(_context, "Please Login First", Toast.LENGTH_LONG).show();
            Intent i = new Intent(_context,Login.class);
           
         // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         // Staring Login Activity
            _context.startActivity(i);
            return false;
        }
        else {
			return true;
		}

    }
    public Boolean checkLoginhome(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
           
            return false;
        }
        else {
			return true;
		}

    }



    /**
     * Get stored session data
     * */
//    public HashMap<String, String> getUserDetails(){
//        HashMap<String, String> user = new HashMap<String, String>();
//        // user name
//        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
//
//        // user email id
//        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
//
//        // return user
//        return user;
//    }
    public String getUserDetails(){
        String user = new  String();
        // user name
        user = pref.getString(KEY_NAME, null);
//        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
//
//        // user email id
//        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Staring Login Activity
        _context.startActivity(i);
         
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref1.getBoolean(IS_LOGIN,false);
    }
    
    public Boolean checkinternetandlogin()
    {
    	if(this.isLoggedIn())
    	{ 
    		
//    		if (new SecondActivity().isOnline())
//			 {
//					Intent i = new Intent(_context, Report_Info.class);
//					_context.startActivity(i);
//
//			 }
//			 else
//				{
//					new SecondActivity().Validation("plz turn on your internet connection!!");
//				}
    		
    		return true;
    	}
    	else
    	{
    		Toast.makeText(_context, "Please Login First", Toast.LENGTH_LONG).show();
            Intent i = new Intent(_context,Login.class);
           
         // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         // Staring Login Activity
            _context.startActivity(i);
    		
    		return false;
    	}
    	
    	
    }
}