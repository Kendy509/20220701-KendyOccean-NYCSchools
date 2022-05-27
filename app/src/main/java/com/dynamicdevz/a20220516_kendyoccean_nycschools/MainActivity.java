package com.dynamicdevz.a20220516_kendyoccean_nycschools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dynamicdevz.a20220516_kendyoccean_nycschools.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
