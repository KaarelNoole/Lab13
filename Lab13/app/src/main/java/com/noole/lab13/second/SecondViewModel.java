package com.noole.lab13.second;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SecondViewModel extends ViewModel {
    private final MutableLiveData<String> text;

    public SecondViewModel(){
        text = new MutableLiveData<>();
        text.setValue("This is second fragment");
    }

    public LiveData<String> getText(){
        return text;
    }

}