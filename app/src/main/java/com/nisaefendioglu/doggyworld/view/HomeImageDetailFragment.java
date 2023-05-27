package com.nisaefendioglu.doggyworld.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nisaefendioglu.doggyworld.R;
import com.nisaefendioglu.doggyworld.databinding.FragmentHomeImageDetailBinding;

public class HomeImageDetailFragment extends Fragment {

    private static final String ARG_IMAGE_URL = "image_url";

    private FragmentHomeImageDetailBinding binding;

    public HomeImageDetailFragment() {
    }

    public static HomeImageDetailFragment newInstance(String imageUrl) {
        HomeImageDetailFragment fragment = new HomeImageDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeImageDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageButton backButton = binding.backButton;
        backButton.setOnClickListener(v -> getActivity().onBackPressed());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String imageUrl = getArguments().getString(ARG_IMAGE_URL);
            loadImage(imageUrl);
        }
    }

    private void loadImage(String imageUrl) {
        Glide.with(requireContext())
                .load(imageUrl)
                .into(binding.imageView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
