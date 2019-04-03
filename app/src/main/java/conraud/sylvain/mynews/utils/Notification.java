package conraud.sylvain.mynews.utils;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

import conraud.sylvain.mynews.ui.activity.MainActivity;

import static android.content.Context.ALARM_SERVICE;

public class Notification {
    private static Notification instance = null;
    public Context context;
    /* Private constructor*/
    private Notification() {

    }
    /* Static function*/
    public static Notification getInstance() {
        if (instance == null) {
            instance = new Notification();
        }
        return instance;
    }

    /*configure channel and calendar for notifications*/
    public void configureNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notif", "notifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager =context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);}

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE, 36);
        calendar.set(Calendar.SECOND, 10);

        Intent intent = new Intent(context, NotificationReceveir.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,100,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Save.getInstance().loadActivationNotification()){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 2*60*1000,pendingIntent);
        }else{
            alarmManager.cancel(pendingIntent);
        }
    }
}
