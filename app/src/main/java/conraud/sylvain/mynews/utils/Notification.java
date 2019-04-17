package conraud.sylvain.mynews.utils;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class Notification {
    private static Notification instance = null;

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
    public void configureNotification(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notif", "notifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 18);


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*60*1000, pendingIntent);
        System.out.println("notif init");
        Save.getInstance().saveFirstStart();

        if (!Save.getInstance().loadActivationNotification()) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
