package com.nisaefendioglu.doggyworld.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class DogImageResponse {
    @SerializedName("message")
    private List<String> message;
    @SerializedName("status")
    private String status;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
