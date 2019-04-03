package conraud.sylvain.mynews.utils;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Calendar;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Root;
import conraud.sylvain.mynews.ui.activity.ResultsSearchActivity;

public class NotificationReceveir extends BroadcastReceiver implements CallService.Callback{
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
    this.context = context;
       call();

    }
    /*Create Notification*/
    void createNotif(Root root){

        Intent repeatingIntent = new Intent(context, ResultsSearchActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        repeatingIntent.putExtra("root", root);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notif" )
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Vos articles")
                .setContentText("Les articles correspondants à votre recherche sont disponibles")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0, builder.build());

    }
    /*Call search*/
    private void call(){
        String search = Save.getInstance().loadSearchNotification();
        String filter = Save.getInstance().loadFilter();
        String date = getDate();

        CallService.callSearch(this,search,filter,date,date, CallBack.KEY_NOTIFICATION,context);
    }

    /*Callback search*/
    @Override
    public void onResponse(Root root, int id) {
        createNotif(root);
    }

    @Override
    public void onFailure(Context context) {
        createNotif(null);
    }
    /*today date*/
    private String getDate(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month+=1;
        String monthString = String.valueOf(month);
        if(monthString.length() == 1)
            monthString = "0" + monthString;
        String dayString = String.valueOf(day);
        if(dayString.length() == 1)
            dayString = "0"+dayString;
        return String.valueOf(year)+monthString+dayString;
    }
}
