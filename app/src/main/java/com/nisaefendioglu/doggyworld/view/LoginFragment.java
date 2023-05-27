package com.nisaefendioglu.doggyworld.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nisaefendioglu.doggyworld.MainActivity;
import com.nisaefendioglu.doggyworld.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private static final int LOADING_DELAY = 5000;
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        simulateLoading();
    }

    private void simulateLoading() {
        new Handler().postDelayed(() -> {
            MainActivity mainActivity = (MainActivity) requireActivity();
            if (mainActivity != null) {
                mainActivity.showHomeFragment();
            }
        }, LOADING_DELAY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
