package com.nisaefendioglu.doggyworld.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nisaefendioglu.doggyworld.R;
import com.nisaefendioglu.doggyworld.adapter.DogImageAdapter;
import com.nisaefendioglu.doggyworld.api.ApiClient;
import com.nisaefendioglu.doggyworld.api.ApiService;
import com.nisaefendioglu.doggyworld.databinding.FragmentHomeDetailBinding;
import com.nisaefendioglu.doggyworld.model.DogImageResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDetailFragment extends Fragment {
    private FragmentHomeDetailBinding binding;
    private DogImageAdapter dogImageAdapter;
    private List<String> dogImagesList;
    private String selectedBreed;
    private GridView gridView;

    public HomeDetailFragment(String breed) {
        this.selectedBreed = breed;
    }

    public static HomeDetailFragment newInstance(String breed) {
        HomeDetailFragment fragment = new HomeDetailFragment(breed);
        Bundle args = new Bundle();
        args.putString("breed", breed);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        gridView = binding.gridView;
        dogImagesList = new ArrayList<>();
        dogImageAdapter = new DogImageAdapter(getActivity(), dogImagesList);
        gridView.setAdapter(dogImageAdapter);

        binding.backButton.setOnClickListener(v -> getActivity().onBackPressed());

        if (getArguments() != null) {
            selectedBreed = getArguments().getString("breed");
        }

        getBreedImages();
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            String imageUrl = dogImagesList.get(position);
            showImageDetail(imageUrl);
        });

        return view;
    }

    private void getBreedImages() {
        ApiService apiService = ApiClient.getApiService();
        Call<DogImageResponse> call = apiService.getBreedImages(selectedBreed);
        call.enqueue(new Callback<DogImageResponse>() {
            @Override
            public void onResponse(Call<DogImageResponse> call, Response<DogImageResponse> response) {
                if (response.isSuccessful()) {
                    DogImageResponse dogResponse = response.body();
                    if (dogResponse != null) {
                        dogImagesList.addAll(dogResponse.getMessage());
                        dogImageAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch breed images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DogImageResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showImageDetail(String imageUrl) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, HomeImageDetailFragment.newInstance(imageUrl));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
