package com.nisaefendioglu.doggyworld.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nisaefendioglu.doggyworld.R;
import com.nisaefendioglu.doggyworld.adapter.DogAdapter;
import com.nisaefendioglu.doggyworld.api.ApiClient;
import com.nisaefendioglu.doggyworld.api.ApiService;
import com.nisaefendioglu.doggyworld.databinding.FragmentHomeBinding;
import com.nisaefendioglu.doggyworld.model.DogResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements DogAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private DogAdapter dogAdapter;
    private List<String> dogBreedsList;
    private Map<String, List<String>> dogBreedsMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        setupRecyclerView();
        getDogBreeds();
        return view;
    }

    private void setupRecyclerView() {
        dogBreedsList = new ArrayList<>();
        dogAdapter = new DogAdapter(dogBreedsList);
        dogAdapter.setOnItemClickListener(this); // Listener'ı ayarla
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(dogAdapter);
    }

    private void getDogBreeds() {
        ApiService apiService = ApiClient.getApiService();
        Call<DogResponse> call = apiService.getDogBreeds();
        call.enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if (response.isSuccessful()) {
                    DogResponse dogResponse = response.body();
                    if (dogResponse != null) {
                        dogBreedsMap = dogResponse.getMessage();
                        dogBreedsList.addAll(dogBreedsMap.keySet());
                        dogAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getActivity(), "Failed to fetch dog breeds", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(String breed) {
        HomeDetailFragment fragment = HomeDetailFragment.newInstance(breed);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
