package com.nisaefendioglu.doggyworld.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nisaefendioglu.doggyworld.R;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    private List<String> dogBreedsList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String breed);
    }

    public DogAdapter(List<String> dogBreedsList) {
        this.dogBreedsList = dogBreedsList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dog_breed, parent, false);
        return new DogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        String breed = dogBreedsList.get(position);
        holder.bind(breed);
    }

    @Override
    public int getItemCount() {
        return dogBreedsList.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        private TextView breedTextView;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            breedTextView = itemView.findViewById(R.id.breed_text_view);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String breed = dogBreedsList.get(position);
                        listener.onItemClick(breed);
                    }
                }
            });
        }

        public void bind(String breed) {
            breedTextView.setText(breed);
        }
    }
}
