package com.nisaefendioglu.doggyworld.api;

import com.nisaefendioglu.doggyworld.model.DogImageResponse;
import com.nisaefendioglu.doggyworld.model.DogResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("breeds/list/all")
    Call<DogResponse> getDogBreeds();

    @GET("breed/{breed}/images")
    Call<DogImageResponse> getBreedImages(@Path("breed") String breed);
}
