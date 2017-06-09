package com.example.android.dbintegration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by simranjain1507 on 03/06/17.
 */

public class PersonDetailsAdapter extends RecyclerView.Adapter<PersonDetailsAdapter.PersonDetailsViewHolder> {
    private List<PersonDetails> personDetailses;
    Context context;
    DatabaseHandler db;
    int pos;
    public PersonDetailsAdapter(Context c,List<PersonDetails> personDetails) {
        this.context=c;
        this.personDetailses = personDetails;
        db=new DatabaseHandler(context);

    }

    public class PersonDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, phn, gender, area;
        LinearLayout linearLayout;


        public PersonDetailsViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            phn = (TextView) itemView.findViewById(R.id.phn);
            gender = (TextView) itemView.findViewById(R.id.gen);
            area=(TextView) itemView.findViewById(R.id.area);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.totallist);

        }
    }


    @Override
    public PersonDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new PersonDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonDetailsViewHolder holder, final int position) {
        holder.name.setText("NAME: " + personDetailses.get(position).getName());
        holder.email.setText("E-MAIL: " + personDetailses.get(position).getEmail());
        holder.phn.setText("PHONE: " + personDetailses.get(position).getPhone());
        holder.gender.setText("GENDER: " + personDetailses.get(position).getGender());
        holder.area.setText("AREA: " + personDetailses.get(position).getArea());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=personDetailses.get(position).getId();
                Log.e ("Item selected: ", String.valueOf(personDetailses.get(position).getId()));
                Intent editIntent=new Intent(context, UpdateActivity.class);
                editIntent.putExtra("position_db",String.valueOf(personDetailses.get(position).getId()));
                editIntent.putExtra("name_db", personDetailses.get(position).getName());
                editIntent.putExtra("email_db", personDetailses.get(position).getEmail());
                editIntent.putExtra("phone_db", personDetailses.get(position).getPhone());
                editIntent.putExtra("gender_db", personDetailses.get(position).getGender());
                editIntent.putExtra("area_db", personDetailses.get(position).getArea());

                context.startActivity(editIntent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return personDetailses.size();
    }
}


