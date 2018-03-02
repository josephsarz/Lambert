package com.codegene.femicodes.lambert;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView mVehicleCategory;
    TextView mVehicleSubCategory;
    TextView mPolicyNumber;
    TextView mVehicleModel;
    TextView mVehicleType;
    TextView mVehicleColor;
    TextView mChasisNumber;
    TextView mEngineNumber;
    TextView mEngineCapacity;
    TextView mFuelType;
    TextView mTankCapacity;
    TextView mManufactureYear;
    TextView mMileage;
    TextView mPlateAllocationState;
    TextView mLicenseNumber;
    TextView mBearerName;
    TextView mIdentificationType;
    TextView mIdentificationNumber;
    TextView mBearerAddress;
    TextView mBearerState;
    TextView mBearerLga;
    TextView mBearerPhoneNumber;
    TextView mBearerEmail;

     String vehicleCategory;
     String vehicleSubCategory;
     String policyNumber;
     String vehicleModel;
     String vehicleType;
     String vehicleColor;
     String chasisNumber;
     String engineNumber;
     String engineCapacity;
     String fuelType;
     String tankCapacity;
     String manufactureYear;
     String mileage;
     String plateAllocationState;
     String licenseNumber;
     String bearerName;
     String identificationType;
     String identificationNumber;
     String bearerAddress;
     String bearerState;
     String bearerLga;
     String bearerPhoneNumber;
     String bearerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vehicle Details");


        mVehicleCategory = findViewById(R.id.vehicleCategoryTv);
        mVehicleSubCategory = findViewById(R.id.vehicleSubCategoryTv);
        mPolicyNumber = findViewById(R.id.policyNumberTv);
        mVehicleModel = findViewById(R.id.vehicleModelTv);
        mVehicleType = findViewById(R.id.vehicleTypeTv);
        mVehicleColor = findViewById(R.id.vehicleColorTv);
        mChasisNumber = findViewById(R.id.chasisNumberTv);
        mEngineNumber  = findViewById(R.id.engineNumberTv);
        mEngineCapacity = findViewById(R.id.engineCapacityTv);
        mFuelType = findViewById(R.id.fuelTypeTv);
        mTankCapacity = findViewById(R.id.tankCapacityTv);
        mManufactureYear = findViewById(R.id.manufactureYearTv);
        mMileage = findViewById(R.id.mileageTv);
        mPlateAllocationState = findViewById(R.id.plateAllocationStateTv);
        mLicenseNumber = findViewById(R.id.licenseNumberTv);
        mBearerName = findViewById(R.id.bearerNameTv);
        mIdentificationType = findViewById(R.id.identificationTypeTv);
        mIdentificationNumber =findViewById(R.id.identificationNumberTv);
        mBearerAddress = findViewById(R.id.bearerAddressTv);
        mBearerState = findViewById(R.id.bearerStateTv);
        mBearerLga = findViewById(R.id.bearerLgaTv);
        mBearerPhoneNumber = findViewById(R.id.bearerPhoneNumberTv);
        mBearerEmail = findViewById(R.id.bearerEmailTv);

        //getting data from previous activity
        vehicleCategory = getIntent().getStringExtra("vehicleCategory");
        vehicleSubCategory = getIntent().getStringExtra("vehicleSubCategory");
        policyNumber = getIntent().getStringExtra("policyNumber");
       vehicleModel = getIntent().getStringExtra("vehicleModel");
        vehicleType = getIntent().getStringExtra("vehicleType");
        vehicleColor = getIntent().getStringExtra("vehicleColor");
        chasisNumber = getIntent().getStringExtra("chasisNumber");
        engineNumber = getIntent().getStringExtra("engineNumber");
        engineCapacity = getIntent().getStringExtra("engineCapacity");
        fuelType = getIntent().getStringExtra("fuelType");
        tankCapacity = getIntent().getStringExtra("tankCapacity");
        manufactureYear =getIntent().getStringExtra("manufactureYear");
        mileage = getIntent().getStringExtra("mileage");
       plateAllocationState = getIntent().getStringExtra("plateAllocationState");
        licenseNumber = getIntent().getStringExtra("licenseNumber");
        bearerName = getIntent().getStringExtra("bearerName");
        identificationType = getIntent().getStringExtra("identificationType");
        identificationNumber = getIntent().getStringExtra("identificationNumber");
        bearerAddress = getIntent().getStringExtra("bearerAddress");
        bearerState = getIntent().getStringExtra("bearerState");
        bearerLga = getIntent().getStringExtra("bearerLga");
        bearerPhoneNumber = getIntent().getStringExtra("bearerPhoneNumber");
        bearerEmail = getIntent().getStringExtra("bearerEmail");


        //set content to textview

        mVehicleCategory.setText(vehicleCategory);
         mVehicleSubCategory.setText(vehicleSubCategory);
        mPolicyNumber.setText(policyNumber);
        mVehicleModel.setText(vehicleModel);
        mVehicleType.setText(vehicleType);
        mVehicleColor.setText(vehicleColor);
        mChasisNumber.setText(chasisNumber);
        mEngineNumber.setText(engineNumber);
        mEngineCapacity.setText(engineCapacity);
        mFuelType.setText(fuelType);
        mTankCapacity.setText(tankCapacity);
        mManufactureYear.setText(manufactureYear);
        mMileage.setText(mileage);
        mPlateAllocationState.setText(plateAllocationState);
        mLicenseNumber.setText(licenseNumber);
        mBearerName.setText(bearerName);
        mIdentificationType.setText(identificationType);
        mIdentificationNumber.setText(identificationNumber);
        mBearerAddress.setText(bearerAddress);
        mBearerState.setText(bearerState);
         mBearerLga.setText(bearerLga);
        mBearerPhoneNumber.setText(bearerPhoneNumber);
        mBearerEmail.setText(bearerEmail);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
