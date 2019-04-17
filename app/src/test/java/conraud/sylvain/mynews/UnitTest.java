package conraud.sylvain.mynews;

import org.junit.Test;

import java.util.Calendar;

import conraud.sylvain.mynews.ui.activity.SearchActivity;
import conraud.sylvain.mynews.utils.NotificationReceiver;

import static org.junit.Assert.*;


public class UnitTest {

    /*Search Activity*/

        @Test
        public void setDateTest(){
            SearchActivity searchActivity = new SearchActivity();
            searchActivity.setDate(2019,5,25,2);
            System.out.println(searchActivity.beginDate);
            assertEquals("20190625", searchActivity.beginDate);
            assertEquals("25/06/2019", searchActivity.dateTest);
        }


        /*Notification Recever*/
        @Test
        public void getDateTest(){
            NotificationReceiver notificationReceiver = new NotificationReceiver();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            notificationReceiver.getDate();
            month+=1;
            String monthString = String.valueOf(month);
            if(monthString.length() == 1)
                monthString = "0" + monthString;
            String dayString = String.valueOf(day);
            if(dayString.length() == 1)
                dayString = "0"+dayString;
           assertEquals(  String.valueOf(year)+monthString+dayString,notificationReceiver.getDate());
        }

        @Test
    public void textNotificationTest(){
            NotificationReceiver notificationReceiver = new NotificationReceiver();
            assertEquals("3 articles correspondent à votre recherche", notificationReceiver.textNotification(3));

        }
}
