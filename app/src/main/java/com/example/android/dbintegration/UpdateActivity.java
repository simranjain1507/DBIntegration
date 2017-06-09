package com.example.android.dbintegration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHandler db;
    int position_db;
    RadioGroup radioGroup;
    RadioButton male, female;
    String name_db, email_db, phone_db,gender_db, area_db;
    EditText name, email, phone,area;
    Button updateButton;
    String gender;
    ImageView deleteButton;
    ArrayList<PersonDetails> personDetailses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        position_db = Integer.parseInt(intent.getStringExtra("position_db"));
        name_db=intent.getStringExtra("name_db");
        email_db=intent.getStringExtra("email_db");
        phone_db=intent.getStringExtra("phone_db");
        gender_db=intent.getStringExtra("gender_db");
        area_db=intent.getStringExtra("area_db");
        //intent.getExtras();
        Log.e("position_db", String.valueOf(position_db) + "\n" + name_db +"\n" + email_db +"\n" + phone_db + "\n" + gender_db + "\n" + area_db);
        db = new DatabaseHandler(UpdateActivity.this);

        Log.e("Name: at 1 position", db.getPersonDetails(position_db).getName());

        name = (EditText) findViewById(R.id.et_name);
        email = (EditText) findViewById(R.id.et_email);
        phone = (EditText) findViewById(R.id.et_pn);
        area=(EditText) findViewById(R.id.et_area);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        updateButton=(Button) findViewById(R.id.updatebutton);
        deleteButton=(ImageView) findViewById(R.id.deleteButton);
        name.setText(name_db);
        email.setText(email_db);
        phone.setText(phone_db);
        area.setText(area_db);
        if(gender_db.equals("Male")) {
            male.setChecked(true);
        }
        if(gender_db.equals("Female"))
        {
            female.setChecked(true);
        }
        updateButton.setOnClickListener(new View.OnClickListener() {
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
                gender="male";
                if ((TextUtils.isEmpty(dname) && TextUtils.isEmpty(demail) && TextUtils.isEmpty(dphn) && TextUtils.isEmpty(darea))
                        || (TextUtils.isEmpty(dname) || TextUtils.isEmpty(demail) || TextUtils.isEmpty(dphn) || TextUtils.isEmpty(darea))) {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }

                else{
                    db.updateDetails(new PersonDetails(position_db,dname,demail,dphn,gender,darea), position_db);
                    String log = "Id: " + position_db + " ,Name: " + dname + " ,Email: " + demail + " ,Phone: " + dphn + " ,Gender: " + gender + " ,Area: " + darea;
                        Log.e("DB: ", log);
                    Intent listIntent=new Intent(UpdateActivity.this, ListRecycle.class);
                    startActivity(listIntent);
                }

            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(position_db);
                AlertDialog.Builder dialog=new AlertDialog.Builder(UpdateActivity.this);
                dialog.setTitle("Delete");
                dialog.setMessage("Are you sure you want to delete");
                dialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.delete(position_db);
                        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                        Intent listIntent=new Intent(UpdateActivity.this, ListRecycle.class);
                        startActivity(listIntent);
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());


                    }
                });
                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }


        });

    }

}
