package com.example.android.dbintegration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, email, phone,area;
    RadioGroup radioGroup;
    RadioButton male, female;
    Button submitButton;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        final DatabaseHandler db = new DatabaseHandler(MainActivity.this);

        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        phone = (EditText) findViewById(R.id.et_pn);
        area=(EditText) findViewById(R.id.et_area) ;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        submitButton = (Button) findViewById(R.id.submitbutton);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
//        View radioButton=radioGroup.findViewById(radioButtonId);
//        int index=radioGroup.indexOfChild(radioButton);
//        RadioButton r=(RadioButton) findViewById(radioButtonId);

        //       Toast.makeText(getApplicationContext(), gender , Toast.LENGTH_SHORT).show();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dname = name.getText().toString();
                String demail = email.getText().toString();
                String dphn = phone.getText().toString();
                String darea=area.getText().toString();

                if (male.isChecked()) {
                    gender = male.getText().toString();
                } else {
                    gender = female.getText().toString();
                }
                if ((TextUtils.isEmpty(dname) && TextUtils.isEmpty(demail) && TextUtils.isEmpty(dphn)) || (TextUtils.isEmpty(dname) || TextUtils.isEmpty(demail) || TextUtils.isEmpty(dphn))) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    db.addDetails(new PersonDetails(dname, demail, dphn, gender,darea));
//                    List<PersonDetails> person = new ArrayList<PersonDetails>();
//                    person = db.getAllDetails();
//
//                    for (PersonDetails pd : person) {
//                        String log = "Id: " + pd.getId() + " ,Name: " + pd.getName() + " ,Email: " + pd.getEmail() + " ,Phone: " + pd.getPhone() + " ,Gender: " + pd.getGender();
//                        Log.e("DB: ", log);
//                    }


                    name.setText(" ");
                    email.setText(" ");
                    phone.setText(" ");
                    area.setText(" ");
                    Intent listIntent = new Intent(MainActivity.this, ListRecycle.class);
                    //Bundle args = new Bundle();
                    //args.putSerializable("ARRAYLIST", (Serializable) person);
                    //listIntent.putExtra("BUNDLE", args);
                    startActivity(listIntent);

                }
//                name.setText(" ");
//                email.setText(" ");
//                phone.setText(" ");
//                Intent listIntent=new Intent(MainActivity.this, ListRecycle.class);
//                Bundle args=new Bundle();
//                args.putSerializable("ARRAYLIST", (Serializable) person);
//                listIntent.putExtra("BUNDLE", args);
//                startActivity(listIntent);
//                Intent listIntent=new Intent(MainActivity.this, ListRecycle.class);
//                Bundle args=new Bundle();
//                args.putSerializable("ARRAYLIST", (Serializable) person);
//                listIntent.putExtra("BUNDLE", args);
//                startActivity(listIntent);

            }
        });


        TextView tv = (TextView) findViewById(R.id.listText);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PersonDetails> person = new ArrayList<PersonDetails>();
                person = db.getAllDetails();
                Intent listIntent = new Intent(MainActivity.this, ListRecycle.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) person);
                listIntent.putExtra("BUNDLE", args);
                startActivity(listIntent);
            }
        });


    }
}
