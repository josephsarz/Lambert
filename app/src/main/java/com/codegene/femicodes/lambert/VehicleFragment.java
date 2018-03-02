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

import com.codegene.femicodes.lambert.model.Vehicle;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleFragment extends Fragment{


    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;


    public VehicleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle, container, false);

        //initialize recyclerview and FIrebase objects
        recyclerView = view.findViewById(R.id.vehicle_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("vehicles");


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.add_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddVehicleActivity.class));
            }
        });



        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Vehicle");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Vehicle, VehicleViewHolder> FBRA = new FirebaseRecyclerAdapter<Vehicle, VehicleViewHolder>(
                Vehicle.class,
                R.layout.vehicle_card_items,
                VehicleViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(VehicleViewHolder viewHolder, Vehicle model, int position) {
                final String post_key = getRef(position).getKey().toString();
                viewHolder.setVehicleCategory(model.getLicenseNumber());
                viewHolder.setVehicleColour(model.getVehicleColour());
                viewHolder.setVehicleModel(model.getVehicleModel());
                viewHolder.setLicenseNumber(model.getLicenseNumber());
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

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public VehicleViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setVehicleCategory(String vehicleCategory){
            TextView category = mView.findViewById(R.id.vehicle_category);
            category.setText(vehicleCategory);
        }


        public void setVehicleColour(String vehicleColour) {
            TextView colour = mView.findViewById(R.id.vehicle_colour);
            colour.setText(vehicleColour);
        }

        public void setVehicleModel(String vehicleModel) {
            TextView model = mView.findViewById(R.id.vehicle_model);
            model.setText(vehicleModel);
        }

        public void setLicenseNumber(String licenseNumber) {
            TextView license = mView.findViewById(R.id.license_number);
            license.setText(licenseNumber);
        }
    }


}
