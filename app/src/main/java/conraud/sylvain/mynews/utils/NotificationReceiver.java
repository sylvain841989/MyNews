package conraud.sylvain.mynews.utils;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.List;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Article;
import conraud.sylvain.mynews.data.Root;
import conraud.sylvain.mynews.ui.activity.ResultsSearchActivity;

import static android.content.Context.MODE_PRIVATE;

public class NotificationReceiver extends BroadcastReceiver implements CallService.Callback{
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
    this.context = context;
       call();
    }

    /*Create Notification*/
    private void createNotif(Root root){
        Intent repeatingIntent = new Intent(context, ResultsSearchActivity.class);
        repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        repeatingIntent.putExtra("root", root);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(repeatingIntent);
        PendingIntent pendingIntent1 = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notif" )
                .setContentIntent(pendingIntent1)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true)
                .setContentTitle("Vos articles")
                //.setContentText(textNotification(checkNbArticle(root)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(textNotification(checkNbArticle(root))));
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0, builder.build());
    }

    /*Call search*/
    private void call(){
        SharedPreferences preferences = context.getSharedPreferences("save",MODE_PRIVATE);
        String search =preferences.getString("searchNotification","");
        String filter = preferences.getString("filter", "arts+business+entrepreneurs+politic+sport+travel+");
        String date = getDate();
        CallService.callNotification(this,search,filter,date,date, CallBack.KEY_SEARCH,context);
    }

    /*Callback search*/
    @Override
    public void onResponse(Root root, int id, Context context) {
        createNotif(root);
    }

    @Override
    public void onFailure(Context context) {
    }

    /*today date*/
    public String getDate(){    //testing
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

    private int checkNbArticle(Root root){
        List<Article> articleList;
        if(root.response!=null && root.response.docs != null){
            articleList = root.response.docs;
        }else{
            articleList = root.results;
        }

        return articleList.size();
    }

    public String textNotification(int nbArticle){  //testing
        if(nbArticle>0){
            return nbArticle + " articles correspondent à votre recherche";
        }else{
            return "Aucun article ne correspond à votre recherche, élargissez les critères";
        }
    }
}
