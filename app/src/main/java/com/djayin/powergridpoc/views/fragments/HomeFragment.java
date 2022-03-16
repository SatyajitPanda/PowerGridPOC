package com.djayin.powergridpoc.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djayin.powergridpoc.R;
import com.djayin.powergridpoc.databinding.FragmentHomeBinding;
import com.djayin.powergridpoc.views.activities.Custommap;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView(){
        binding.layoutSurvey1.setOnClickListener(this);
        binding.layoutSurvey2.setOnClickListener(this);
        binding.layoutSurvey3.setOnClickListener(this);
        binding.layoutSurvey4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Navigation.findNavController(binding.getRoot()).navigate(HomeFragmentDirections.actionHomeFragmentToMapFragment());
        Intent intent = new Intent(getActivity(), Custommap.class);
        startActivity(intent);
    }
}