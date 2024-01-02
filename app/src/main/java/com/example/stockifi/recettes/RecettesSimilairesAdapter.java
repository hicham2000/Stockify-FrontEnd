package com.example.stockifi.recettes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.R;

import java.util.ArrayList;
import java.util.List;

public class RecettesSimilairesAdapter extends RecyclerView.Adapter<RecettesSimilairesAdapter.RecetteSimilaireViewHolder> {

    private List<RecetteModel> recetteList;
    private Context context;

    public RecettesSimilairesAdapter(Context context, List<RecetteModel> recetteList) {
        this.context = context;
        this.recetteList = recetteList;
    }

    public void setRecetteList(List<RecetteModel> recetteList) {
        this.recetteList = new ArrayList<>(recetteList);
    }


    @NonNull
    @Override
    public RecetteSimilaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recette_similaire_item, parent, false);
        return new RecetteSimilaireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetteSimilaireViewHolder holder, int position) {
        RecetteModel recette = this.recetteList.get(position);
        holder.textViewRecetteName.setText(recette.getRecetteName());
        // Async Process
        String imageUrl = recette.getImageUrl();
        loadImageAsync(holder.recetteImageView, imageUrl);

        holder.recetteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecetteActivity.class);
                // Replace put with putExtra
                intent.putExtra("Recette", recette);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recetteList.size();
    }

    public class RecetteSimilaireViewHolder extends RecyclerView.ViewHolder {
        public ImageView recetteImageView;
        private TextView textViewRecetteName;

        public RecetteSimilaireViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRecetteName = itemView.findViewById(R.id.text_view_recette_similaire_name);
            recetteImageView = itemView.findViewById(R.id.recette_similaire_image_view);
        }
    }

    private void loadImageAsync(ImageView imageView, String imageUrl) {
        new GetImageFromUrl(imageView).execute(imageUrl);
    }
}
