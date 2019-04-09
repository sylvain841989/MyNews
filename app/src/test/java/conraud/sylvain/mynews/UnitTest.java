package conraud.sylvain.mynews;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;


public class UnitTest {

    /*Search Activity*/
    @Test
    public void setDate(){

        int month = 4;
        int day = 2;

        month += 1 ;
        String monthString = String.valueOf(month);
        if(monthString.length() == 1)
            monthString = "0" + monthString;

        String dayString = String.valueOf(day);
            dayString = "0" +dayString;

        assertEquals( "05" , monthString);
        assertEquals( "02" , dayString);

        }

    @Test
    public void getFilters(){
       StringBuilder stringBuilderFilter = new StringBuilder();

            stringBuilderFilter.append("arts+");
            stringBuilderFilter.append("business+");
            stringBuilderFilter.append("entrepreneurs+");
            stringBuilderFilter.append("politic+");
            stringBuilderFilter.append("sport+");
            stringBuilderFilter.append("travel+");

            assertEquals("arts+business+entrepreneurs+politic+sport+travel+", stringBuilderFilter.toString());
        }


        /*Notification Recever*/
        @Test
        public void getDate(){
            int year = 2019;
            int month = 3;
            int day = 9;
            month+=1;
            String monthString = String.valueOf(month);
            if(monthString.length() == 1)
                monthString = "0" + monthString;
            String dayString = String.valueOf(day);
                dayString = "0"+dayString;

           assertEquals( "20190409",String.valueOf(year)+monthString+dayString);
        }

    }
