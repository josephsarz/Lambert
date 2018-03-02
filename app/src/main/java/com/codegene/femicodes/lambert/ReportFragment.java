package com.codegene.femicodes.lambert;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codegene.femicodes.lambert.model.Report;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {


    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;


    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_report, container, false);


        //initialize recyclerview and FIrebase objects
        recyclerView = view.findViewById(R.id.report_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("reports");


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.report_add_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddReport.class));
            }
        });

       return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Report");
    }


    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Report, ReportViewHolder> FBRA = new FirebaseRecyclerAdapter<Report, ReportViewHolder>(
                Report.class,
                R.layout.report_card_items,
                ReportViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(ReportViewHolder viewHolder, Report model, int position) {
                final String post_key = getRef(position).getKey().toString();
                viewHolder.setName(model.getName());
                viewHolder.setPhoneNumber(model.getPhoneNumber());
                viewHolder.setDescription(model.getDescription());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(), post_key, Toast.LENGTH_LONG).show();

                    }
                });
            }
        };
        recyclerView.setAdapter(FBRA);
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ReportViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            TextView pname = mView.findViewById(R.id.report_name);
            pname.setText("name: "+ name);
        }

        public void setPhoneNumber(String phoneNumber) {
            TextView phone = mView.findViewById(R.id.report_phone);
            phone.setText("phone: "+phoneNumber);
        }

        public void setDescription(String description) {
            TextView desc = mView.findViewById(R.id.report_desc);
            desc.setText("description: "+description);
        }
    }



}
