package com.codegene.femicodes.lambert;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.codegene.femicodes.lambert.model.Vehicle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddVehicleActivity extends AppCompatActivity {

    final static String REFERENCE_CHILD = "vehicles";
    FirebaseDatabase mReportDatabase;
    DatabaseReference mReportDatabaseReference;

    EditText mVehicleCategory, mVehicleSubCategory, mPolicyNumber, mVehicleModel, mVehicleType, mVehicleColour, mChasisNumber, mEngineNumber, mEngineCapacity;
    EditText mFuelType, mTankCapacity, mManufactureYear, mMileage, mPlateAllocationState, mLicenceNumber, mBearerName, mIdentificationType;
    EditText mIdentificationNumber, mBearerAddress, mBearerState, mBearerLga, mBearerPhoneNumber, mBearerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mReportDatabase = FirebaseDatabase.getInstance();
        mReportDatabaseReference = mReportDatabase.getReference(REFERENCE_CHILD);

        mVehicleCategory = findViewById(R.id.vehicleCategory);
        mVehicleSubCategory = findViewById(R.id.vehicleSubCategory);
        mPolicyNumber = findViewById(R.id.policyNumber);
        mVehicleModel = findViewById(R.id.vehicleModel);
        mVehicleType = findViewById(R.id.vehicleType);
        mVehicleColour = findViewById(R.id.vehicleColor);
        mChasisNumber = findViewById(R.id.chasisNumber);
        mEngineNumber = findViewById(R.id.engineNumber);
        mEngineCapacity = findViewById(R.id.engineCapacity);
        mFuelType = findViewById(R.id.fuelType);
        mTankCapacity = findViewById(R.id.tankCapacity);
        mManufactureYear = findViewById(R.id.manufactureYear);
        mMileage = findViewById(R.id.mileage);
        mPlateAllocationState = findViewById(R.id.plateAllocationState);
        mLicenceNumber = findViewById(R.id.licenseNumber);
        mBearerName = findViewById(R.id.bearerName);
        mIdentificationType = findViewById(R.id.identificationType);
        mIdentificationNumber = findViewById(R.id.identificationNumber);
        mBearerAddress = findViewById(R.id.bearerAddress);
        mBearerState = findViewById(R.id.bearerState);
        mBearerLga = findViewById(R.id.bearerLga);
        mBearerPhoneNumber = findViewById(R.id.bearerPhoneNumber);
        mBearerEmail = findViewById(R.id.bearerEmail);


    }

    public void addVehicle() {

        final String vehicleCategory = mVehicleCategory.getText().toString().trim();
        final String vehicleSubCategory = mVehicleSubCategory.getText().toString().trim();
        final String policyNumber = mPolicyNumber.getText().toString().trim();
        final String vehicleModel = mVehicleModel.getText().toString().trim();
        final String vehicleType = mVehicleType.getText().toString().trim();
        final String vehicleColour = mVehicleColour.getText().toString().trim();
        final String chasisNumber = mChasisNumber.getText().toString().trim();
        final String engineNumber = mEngineNumber.getText().toString().trim();
        final String engineCapacity = mEngineCapacity.getText().toString().trim();
        final String fuelType = mFuelType.getText().toString().trim();
        final String tankCapacity = mTankCapacity.getText().toString().trim();
        final String manufactureYear = mManufactureYear.getText().toString().trim();
        final String mileage = mMileage.getText().toString().trim();
        final String plateAllocationState = mPlateAllocationState.getText().toString().trim();
        final String licenceNumber = mLicenceNumber.getText().toString().trim();
        final String bearerName = mBearerName.getText().toString().trim();
        final String identificationType = mIdentificationType.getText().toString().trim();
        final String identificationNumber = mIdentificationNumber.getText().toString().trim();
        final String bearerAddress = mBearerAddress.getText().toString().trim();
        final String bearerState = mBearerState.getText().toString().trim();
        final String bearerLga = mBearerLga.getText().toString().trim();
        final String bearerPhoneNumber = mBearerPhoneNumber.getText().toString().trim();
        final String bearerEmail = mBearerEmail.getText().toString().trim();


        if (!TextUtils.isEmpty(licenceNumber)) {
            Toast.makeText(getApplicationContext(), "POSTING...", Toast.LENGTH_SHORT).show();

            Vehicle vehicle = new Vehicle(vehicleCategory, vehicleSubCategory, policyNumber, vehicleModel, vehicleType, vehicleColour,
                    chasisNumber, engineNumber, engineCapacity, fuelType, tankCapacity, manufactureYear, mileage, plateAllocationState,
                    licenceNumber, bearerName, identificationType, identificationNumber, bearerAddress, bearerState, bearerLga,
                    bearerPhoneNumber, bearerEmail);

            mReportDatabaseReference.push().setValue(vehicle).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), " Successful ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } else {
           Toast.makeText(getApplicationContext(), "all fields must be filled", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addvehicle_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_addVehicleDone){
            addVehicle();
        }

        return super.onOptionsItemSelected(item);
    }

}
