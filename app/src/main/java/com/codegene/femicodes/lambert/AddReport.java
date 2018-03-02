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

import com.codegene.femicodes.lambert.model.Report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddReport extends AppCompatActivity {

    EditText mName;
    EditText mPhoneNo;
    EditText mDescription;
    final static String REFERENCE_CHILD = "reports";
    FirebaseDatabase mReportDatabase;
    DatabaseReference mReportDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Report");

        mReportDatabase = FirebaseDatabase.getInstance();
        mReportDatabaseReference = mReportDatabase.getReference(REFERENCE_CHILD);


        mName = findViewById(R.id.person_name);
        mPhoneNo = findViewById(R.id.phone_no);
        mDescription = findViewById(R.id.report_description);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addreport_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.add_report_done){

            addReport();

        }

        return super.onOptionsItemSelected(item);
    }

    private void addReport(){
            final String name = mName.getText().toString().trim();
            final String phoneno = mPhoneNo.getText().toString().trim();
            final String desc = mDescription.getText().toString().trim();



            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(phoneno)) {
                Toast.makeText(getApplicationContext(), "POSTING...", Toast.LENGTH_SHORT).show();

                Report report = new Report(name, phoneno, desc);

                mReportDatabaseReference.push().setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
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
               Toast.makeText(getApplicationContext(), "all fields are required", Toast.LENGTH_SHORT).show();
            }


    }
}
