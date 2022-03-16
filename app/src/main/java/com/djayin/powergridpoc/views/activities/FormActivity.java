package com.djayin.powergridpoc.views.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.djayin.powergridpoc.R;
import com.djayin.powergridpoc.databinding.ActivityFormBinding;
import com.djayin.powergridpoc.model.FormData;
import com.djayin.powergridpoc.utilities.AppUtils;

import java.util.Random;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding binding;
    double lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form);

        initializeView();
    }

    private void initializeView() {
        String selectedAddress = getIntent().getStringExtra("address");
        lat = getIntent().getDoubleExtra("lat", 0);
        lon = getIntent().getDoubleExtra("lon", 0);
        binding.editSelectedLocation.setText(selectedAddress);
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFormData(selectedAddress);
                finish();
            }
        });
    }

    private void submitFormData(String selectedAddress){
        FormData obj = new FormData();
        obj.setUniqueID(new Random().nextInt());
        obj.setLat(lat);
        obj.setLon(lon);
        obj.setSelectedAddress(selectedAddress);
        obj.setCircle(binding.editCircle.getText().toString());
        obj.setSubDivision(binding.editSubDivision.getText().toString());
        obj.setMobileNumber(binding.editMobileNumber.getText().toString());
        obj.setConsumerName(binding.editConsumerName.getText().toString());
        obj.setFlatNumber(binding.editFlatNumber.getText().toString());
        obj.setAddress1(binding.editAddress1.getText().toString());
        obj.setAddress2(binding.editAddress2.getText().toString());
        obj.setAddress3(binding.editAddress3.getText().toString());

        AppUtils.lstFormData.add(obj);

        Toast toast = Toast.makeText(this, "Data Submitted Successfully", Toast.LENGTH_SHORT);
        toast.show();
    }
}