package com.noole.lab13.Home;

import androidx.lifecycle.ViewModelProvider;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.noole.lab13.LocationEvent;
import com.noole.lab13.LocationUpdateService;
import com.noole.lab13.R;
import com.noole.lab13.databinding.HomeFragmentBinding;
import com.noole.lab13.databinding.SecondFragmentBinding;
import com.noole.lab13.helper.Constants;
import com.noole.lab13.second.SecondViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeFragmentBinding binding;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private ToggleButton toggleButton;
    List<String> coordinatesList = new ArrayList<>();
    HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = HomeFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        final ListView listView = binding.coordinatesListview;
        homeViewModel.getText().observe(getViewLifecycleOwner(),textView::setText);
        homeViewModel.getCoordinates().observe(getViewLifecycleOwner(),coordinatesList ->{
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,coordinatesList);
            listView.setAdapter(adapter);
        });

        toggleButton = binding.toggleButton;
        toggleButton.setChecked(isLocationServiceRunning());
        toggleButton.setOnCheckedChangeListener((((compoundButton, isChecked) -> {
            if (isChecked){
                toggleButton.setText(toggleButton.getTextOn());
                startLocationService();
            }else{
                toggleButton.setText(toggleButton.getTextOff());
                stopLocationService();
            }
        })));

        return root;
    }

    private boolean isLocationServiceRunning(){
        ActivityManager activityManager = (ActivityManager) requireActivity().getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null){
            for (ActivityManager.RunningServiceInfo service :
                activityManager.getRunningServices(Integer.MAX_VALUE)){
                if (LocationUpdateService.class.getName().equals(service.service.getClassName())){
                    if (service.foreground){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void startLocationService(){
        if (!isLocationServiceRunning()){
            Intent intent = new Intent(getActivity(),LocationUpdateService.class);
            intent.setAction(Constants.ACTION_START_LOCATION_SERVICE);
            getActivity().startService(intent);
            Log.i(TAG, "startLocationService: Started");
        }
    }

    private void stopLocationService(){
        if (!isLocationServiceRunning()){
            Intent intent = new Intent(getActivity(),LocationUpdateService.class);
            intent.setAction(Constants.ACTION_STOP_LOCATION_SERVICE);
            getActivity().startService(intent);
            Log.i(TAG, "startLocationService: Stopped");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEvent(LocationEvent locationEvent){
        String location_result_text = locationEvent.getLocation().toString();
        coordinatesList.add(location_result_text);
        homeViewModel.coordinates.setValue(coordinatesList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}