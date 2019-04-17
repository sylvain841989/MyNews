package conraud.sylvain.mynews.ui.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.utils.Save;

public class NotificationActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private Switch aSwitch;
    private CheckBox checkBoxArts, checkBoxBusiness, checkBoxEntrepreneurs, checkBoxPolitic, checkBoxSport, checkBoxTravel;
    private String filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        configureToolbar();
        configureLayout();
        configureEditTextSearch();
        configureSwitchEnableNotification();
        configureCheckbox();
    }

    /*configure UI*/
    //Configure Toolbar
    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notifications");
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //configure layout
    private void configureLayout(){
        editTextSearch = findViewById(R.id.edit_text_search);
        EditText editTextBeginDate = findViewById(R.id.edit_text_begin_date);
        EditText editTextEndDate = findViewById(R.id.edit_text_end_date);
        Button buttonSearch = findViewById(R.id.button_search);
        TextView textViewBeginDate = findViewById(R.id.text_view_begin_date);
        TextView textViewEndDate = findViewById(R.id.text_view_end_date);
        aSwitch = findViewById(R.id.switch_notification);
        checkBoxArts = findViewById(R.id.checkbox_arts);
        checkBoxBusiness = findViewById(R.id.checkbox_business);
        checkBoxEntrepreneurs = findViewById(R.id.checkbox_entrepreneurs);
        checkBoxPolitic = findViewById(R.id.checkbox_politic);
        checkBoxSport = findViewById(R.id.checkbox_sport);
        checkBoxTravel = findViewById(R.id.checkbox_travel);

        editTextBeginDate.setVisibility(View.GONE);
        editTextEndDate.setVisibility(View.GONE);
        buttonSearch.setVisibility(View.GONE);
        textViewBeginDate.setVisibility(View.GONE);
        textViewEndDate.setVisibility(View.GONE);
    }

    /*Save input search*/
    private void configureEditTextSearch(){
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = editTextSearch.getText().toString();
                Save.getInstance().saveSearchNotification(text);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editTextSearch.setText(Save.getInstance().loadSearchNotification());
    }

    /*Save enable notification*/
    private void configureSwitchEnableNotification(){
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Save.getInstance().saveActivationNotification(isChecked,NotificationActivity.this);
            }
        });
        aSwitch.setChecked(Save.getInstance().loadActivationNotification());
    }

    /*Configure and save checkbox*/
    private void configureCheckbox(){
        checkBoxArts.setChecked(Save.getInstance().loadCheckboxArts());
        checkBoxBusiness.setChecked(Save.getInstance().loadCheckboxbusiness());
        checkBoxEntrepreneurs.setChecked(Save.getInstance().loadCheckboxEntrepreneur());
        checkBoxPolitic.setChecked(Save.getInstance().loadCheckboxpolitic());
        checkBoxSport.setChecked(Save.getInstance().loadCheckboxSports());
        checkBoxTravel.setChecked(Save.getInstance().loadCheckboxTravel());

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                blockCheckboxChecked();
                getFilters();
                Save.getInstance().saveCheckbox(checkBoxArts.isChecked(),checkBoxSport.isChecked(),checkBoxTravel.isChecked(),
                        checkBoxEntrepreneurs.isChecked(),checkBoxPolitic.isChecked(),checkBoxBusiness.isChecked(),filter);
            }
        };
        checkBoxArts.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxBusiness.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxEntrepreneurs.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxPolitic.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxSport.setOnCheckedChangeListener(onCheckedChangeListener);
        checkBoxTravel.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private void blockCheckboxChecked(){
       int nbCheckboxChecked = 0;
       CheckBox[] arrayCheckbox = new CheckBox[6];
        arrayCheckbox[0]= checkBoxBusiness;
        arrayCheckbox[1]= checkBoxArts;
        arrayCheckbox[2]= checkBoxSport;
        arrayCheckbox[3]= checkBoxTravel;
        arrayCheckbox[4]= checkBoxEntrepreneurs;
        arrayCheckbox[5]= checkBoxPolitic;

        for(CheckBox checkBox : arrayCheckbox){
            if(checkBox.isChecked())
                nbCheckboxChecked+=1;
        }
        if(nbCheckboxChecked < 2){
            for(CheckBox checkBox : arrayCheckbox){
                if(checkBox.isChecked())
                    checkBox.setEnabled(false);
            }
        }else {
            for (CheckBox checkBox : arrayCheckbox) {
                checkBox.setEnabled(true);
            }
        }
    }

    /*Check checkbox and return String*/
    private void getFilters(){
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

        filter = stringBuilderFilter.toString();
    }
}
