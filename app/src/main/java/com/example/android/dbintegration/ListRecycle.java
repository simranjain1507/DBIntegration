package com.example.android.dbintegration;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class ListRecycle extends AppCompatActivity {
    RecyclerView rvList;
    LinearLayout linearLayout;
    PersonDetailsAdapter adapter;
    DatabaseHandler db;
    int pos;
//    List<PersonDetails> personDetailses = new ArrayList<PersonDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycle);
      //  List<PersonDetails> personDetailses = new ArrayList<PersonDetails>();
       // Intent intent = getIntent();
        //Bundle args = intent.getBundleExtra("BUNDLE");
        //personDetailses = (ArrayList<PersonDetails>) args.getSerializable("ARRAYLIST");
        db = new DatabaseHandler(ListRecycle.this);

       // personDetailses=db.getAllDetails();

        rvList = (RecyclerView) findViewById(R.id.rv_details);
        linearLayout = (LinearLayout) findViewById(R.id.totallist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);



        //Log.e("Name: at 1 position", db.getPersonDetails(1).getName());
        // db.getPersonDetails(0).getName();

        List<PersonDetails> pd = db.getAllDetails();

        if (pd.size() > 0) {
            rvList.setVisibility(View.VISIBLE);
            adapter = new PersonDetailsAdapter(this, pd);
            rvList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            rvList.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
        }


    }
}
