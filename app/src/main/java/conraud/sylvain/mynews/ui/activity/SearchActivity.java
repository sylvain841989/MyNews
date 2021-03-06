package conraud.sylvain.mynews.ui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.data.Root;
import conraud.sylvain.mynews.utils.CallBack;
import conraud.sylvain.mynews.utils.CallService;

public class SearchActivity extends AppCompatActivity implements CallService.Callback{

    private EditText editTextSearch, editTextBeginDate, editTextEndDate;
    private CheckBox checkBoxArts, checkBoxBusiness, checkBoxEntrepreneurs, checkBoxPolitic, checkBoxSport, checkBoxTravel;
    //public for unit test
    public String beginDate;
    private String  endDate;
    public String dateTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       configureLayout();
       configureViews();
       configureToolbar();
       configureDate();
    }

    /*configure UI*/
    //Configure Toolbar
    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Search");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    //Hide Ui notifications
    private void configureLayout(){
        Switch switchNotification = findViewById(R.id.switch_notification);
        switchNotification.setVisibility(View.GONE);
    }

    //Link views
    private void configureViews(){
        editTextSearch = findViewById(R.id.edit_text_search);
        editTextBeginDate = findViewById(R.id.edit_text_begin_date);
        editTextEndDate = findViewById(R.id.edit_text_end_date);
        checkBoxArts = findViewById(R.id.checkbox_arts);
        checkBoxBusiness = findViewById(R.id.checkbox_business);
        checkBoxEntrepreneurs = findViewById(R.id.checkbox_entrepreneurs);
        checkBoxPolitic = findViewById(R.id.checkbox_politic);
        checkBoxSport = findViewById(R.id.checkbox_sport);
        checkBoxTravel = findViewById(R.id.checkbox_travel);
        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSearch();
            }
        });
    }

    /*configure date and click*/
    private void configureDate(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get (Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        //Get input date
        final DatePickerDialog.OnDateSetListener mDataBeginSetListener = new DatePickerDialog.OnDateSetListener() {
           @SuppressLint("SetTextI18n")
           @Override
           public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               setDate(year, month, dayOfMonth,0);
           }
       };
        final DatePickerDialog.OnDateSetListener mDataEndSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDate(year, month, dayOfMonth,1);
            }
        };

        //open datePicker
        editTextBeginDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, AlertDialog.THEME_HOLO_DARK,mDataBeginSetListener,year,month,day);
               Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.show();
           }
       });
       editTextEndDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DatePickerDialog dialog = new DatePickerDialog(SearchActivity.this, AlertDialog.THEME_HOLO_DARK,mDataEndSetListener,year,month,day);
               Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.show();
           }
       });
    }

    //display and save date
    @SuppressLint("SetTextI18n")
    public void setDate(int year, int month , int day, int id){ //testing
        month += 1 ;
        String monthString = String.valueOf(month);
        if(monthString.length() == 1)
            monthString = "0" + monthString;

        String dayString = String.valueOf(day);
        if(dayString.length() == 1)
            dayString = "0" +dayString;

        switch (id){
            case 0:editTextBeginDate.setText(dayString+"/" + monthString+"/" + year);
                beginDate = String.valueOf(year) + monthString + dayString;
                break;
            case 1:editTextEndDate.setText(dayString+"/" + monthString+"/" + year);
                endDate = String.valueOf(year) + monthString + dayString;
                break;
            case 2 : beginDate = String.valueOf(year) + monthString + dayString;
            dateTest =  dayString+"/" + monthString+"/" + year;
        }
    }

    /*Check checkbox and return String*/
    private String getFilters(){
        StringBuilder stringBuilderFilter = new StringBuilder();

        if(checkBoxArts.isChecked())
            stringBuilderFilter.append("arts+");
        if(checkBoxBusiness.isChecked())
            stringBuilderFilter.append("business+");
        if(checkBoxEntrepreneurs.isChecked())
            stringBuilderFilter.append("entrepreneurs+");
        if(checkBoxPolitic.isChecked())
            stringBuilderFilter.append("politic+");
        if(checkBoxSport.isChecked())
            stringBuilderFilter.append("sport+");
        if(checkBoxTravel.isChecked())
            stringBuilderFilter.append("travel+");

        return stringBuilderFilter.toString();
    }

    /*Start Call Search*/
    private void callSearch(){
        if(checkDateIfIsCorrect()){
            String search = editTextSearch.getText().toString();
            String filter = getFilters();
            if(filter.length()>1){
                CallService.callSearch(this, search, filter,beginDate, endDate,CallBack.KEY_SEARCH,this);
            }else{
                displayDialogErrorFilter();
            }
        }
        else{
            displayDialogErrorDate();
        }
    }

    /*Dialog error*/
    private void displayDialogErrorFilter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Au moins une catégorie doit être cochée")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    private void displayDialogErrorDate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Les dates pour la recherche ne sont pas remplies correctements")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    @Override
    public void onResponse(Root root, int id, Context context) {
        startResultsActivity(root);
    }

    @Override
    public void onFailure(Context context) {
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
    }

    private Boolean checkDateIfIsCorrect(){
        if(endDate == null && beginDate == null)
            return true;
        if(endDate ==null || beginDate == null)
            return false;
        return Integer.valueOf(beginDate) <= Integer.valueOf(endDate);
    }

    /*Open Results Activity*/
    private void startResultsActivity(Root root){
        Intent intent = new Intent(this, ResultsSearchActivity.class);
        intent.putExtra("root", root);
        startActivity(intent);
    }
}
