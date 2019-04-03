package conraud.sylvain.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import conraud.sylvain.mynews.ui.activity.MainActivity;

public class Save {

    private static Save instance = null;
    public SharedPreferences preferences;
    public Context context;


    /* Private constructor*/
    private Save() {

    }
    /* Static function*/
    public static Save getInstance() {
        if (instance == null) {
            instance = new Save();
        }
        return instance;

    }
    public void saveSearchNotification(String search){
        preferences.edit().putString("searchNotification", search).apply();
    }
    public String loadSearchNotification(){
        return preferences.getString("searchNotification",null);
    }
    public void saveActivationNotification(Boolean enable){
        preferences.edit().putBoolean("notificationEnable", enable).apply();
        Notification.getInstance().configureNotification();
    }
    public Boolean loadActivationNotification(){
        return preferences.getBoolean("notificationEnable", true);

    }

    public void saveCheckbox(Boolean arts, Boolean sports, Boolean travel,Boolean entrepreneur, Boolean politic, Boolean business, String filter){
        preferences.edit().putBoolean("arts",arts).apply();
        preferences.edit().putBoolean("sports",sports).apply();
        preferences.edit().putBoolean("travel",travel).apply();
        preferences.edit().putBoolean("entrepreneur",entrepreneur).apply();
        preferences.edit().putBoolean("politic",politic).apply();
        preferences.edit().putBoolean("business",business).apply();
        preferences.edit().putString("filter", filter).apply();
    }
     public Boolean loadCheckboxArts (){
        return preferences.getBoolean("arts", true);
     }
    public Boolean loadCheckboxSports (){
        return preferences.getBoolean("sports", true);
    }
    public Boolean loadCheckboxTravel (){
        return preferences.getBoolean("travel", true);
    }
    public Boolean loadCheckboxEntrepreneur (){
        return preferences.getBoolean("entrepreneur", true);
    }
    public Boolean loadCheckboxpolitic (){
        return preferences.getBoolean("politic", true);
    }
    public Boolean loadCheckboxbusiness (){
        return preferences.getBoolean("business", true);
    }
    public String loadFilter(){
        return preferences.getString("filter", null);
    }
}
