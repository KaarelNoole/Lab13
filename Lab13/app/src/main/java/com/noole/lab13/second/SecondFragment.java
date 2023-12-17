package com.noole.lab13.second;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noole.lab13.databinding.SecondFragmentBinding;
import com.noole.lab13.R;

public class SecondFragment extends Fragment {

    private SecondFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        SecondViewModel secondViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
        binding = SecondFragmentBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        final TextView textView = binding.textSecond;
        secondViewModel.getText().observe(getViewLifecycleOwner(),textView::setText);
        return root;
    }

}