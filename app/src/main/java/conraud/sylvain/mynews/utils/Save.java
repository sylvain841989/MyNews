package conraud.sylvain.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class Save {

    public SharedPreferences preferences;
   // public Context context;
    private static Save instance = null;


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
    /* Save and load Notification search and enable*/
    public void saveSearchNotification(String search){
        preferences.edit().putString("searchNotification", search).apply();
    }
    public String loadSearchNotification(){
        return preferences.getString("searchNotification",null);
    }
    public void saveActivationNotification(Boolean enable, Context context){
        preferences.edit().putBoolean("notificationEnable", enable).apply();
        Notification.getInstance().changeStateNotification(context);
    }
    public Boolean loadActivationNotification(){
        return preferences.getBoolean("notificationEnable", true);

    }
    /* Save and load checkbox for article search*/
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
    String loadFilter(){
        return preferences.getString("filter", null);
    }

    /* Save and check url for color*/
    public void saveUrl(String url, Context context){
        if(preferences == null)
            preferences = context.getSharedPreferences("save",Context.MODE_PRIVATE);

        preferences.edit().putString(url,url).apply();
    }

    public Boolean checkUrl(String url, Context context){
        if(preferences == null)
            preferences = context.getSharedPreferences("save", Context.MODE_PRIVATE);

            return preferences.contains(url);


    }
}
