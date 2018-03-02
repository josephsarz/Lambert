package com.codegene.femicodes.lambert;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.codegene.femicodes.lambert.R;
import com.codegene.femicodes.lambert.ResultActivity;
import com.codegene.femicodes.lambert.model.Vehicle;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends Fragment {


    final static String REFERENCE_CHILD = "vehicles";

    EditText mLicenseNumber;
    FloatingActionButton mVerify;
    DatabaseReference myRef;
    ProgressDialog progressDialog;

    public VerifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_verify, container, false);
        mLicenseNumber = view.findViewById(R.id.verify_vehicle_license);


        myRef = FirebaseDatabase.getInstance().getReference(REFERENCE_CHILD);
        mVerify = view.findViewById(R.id.fab_verify);
        mVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = mLicenseNumber.getText().toString().trim();
                // close the activity in case of empty barcode
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(getActivity(), "field is empty!", Toast.LENGTH_LONG).show();
                } else {
                    FindItem(code);
                }
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Verify");
    }


    public void FindItem(String value) {
        Toast.makeText(getContext(), "Searching", Toast.LENGTH_SHORT).show();
        String child = "licenseNumber";

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(true);

        Query query = myRef.orderByChild(child).equalTo(value);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Vehicle vehicle = dataSnapshot.getValue(Vehicle.class);

                progressDialog.dismiss();

                if (vehicle != null) {


                    Intent intent = new Intent(getContext(), ResultActivity.class);
                    intent.putExtra("vehicleCategory", vehicle.getVehicleCategory());
                    intent.putExtra("vehicleSubCategory", vehicle.getVehicleSubCategory());
                    intent.putExtra("policyNumber", vehicle.getPolicyNumber());
                    intent.putExtra("vehicleModel", vehicle.getVehicleModel());
                    intent.putExtra("vehicleType", vehicle.getVehicleType());
                    intent.putExtra("vehicleColor", vehicle.getVehicleColour());
                    intent.putExtra("chasisNumber", vehicle.getChasisNumber());
                    intent.putExtra("engineNumber", vehicle.getEngineNumber());
                    intent.putExtra("engineCapacity", vehicle.getEngineCapacity());
                    intent.putExtra("fuelType", vehicle.getFuelType());
                    intent.putExtra("tankCapacity", vehicle.getTankCapacity());
                    intent.putExtra("manufactureYear", vehicle.getManufactureYear());
                    intent.putExtra("mileage", vehicle.getMileage());
                    intent.putExtra("plateAllocationState", vehicle.getPlateAllocationState());
                    intent.putExtra("licenseNumber", vehicle.getLicenseNumber());
                    intent.putExtra("bearerName", vehicle.getBearerName());
                    intent.putExtra("identificationType", vehicle.getIdentificationType());
                    intent.putExtra("identificationNumber", vehicle.getIdentificationNumber());
                    intent.putExtra("bearerAddress", vehicle.getBearerAddress());
                    intent.putExtra("bearerState", vehicle.getBearerState());
                    intent.putExtra("bearerLga", vehicle.getBearerLga());
                    intent.putExtra("bearerPhoneNumber", vehicle.getBearerPhoneNumber());
                    intent.putExtra("bearerEmail", vehicle.getBearerEmail());
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Vehicle is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Item not found: ", Toast.LENGTH_LONG).show();

            }
        });
    }


}
