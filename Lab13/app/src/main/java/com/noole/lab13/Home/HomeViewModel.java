package com.noole.lab13.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> text;
    MutableLiveData<List<String>> coordinates;

    public HomeViewModel(){
        text = new MutableLiveData<>();
        coordinates = new MutableLiveData<>();
        text.setValue("This is home fragment");
    }

    public LiveData<String> getText(){
        return text;
    }

    public MutableLiveData<List<String>> getCoordinates() {
        return coordinates;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}