package com.example.stockifi.recettes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.BackendManager;
import com.example.stockifi.R;

import java.util.ArrayList;
import java.util.List;

public class RecettesAdapter extends RecyclerView.Adapter<RecettesAdapter.RecetteViewHolder> {
    private List<RecetteModel> recetteList;
    private Context context;

    public RecettesAdapter(Context context, List<RecetteModel> recetteList) {
        this.context = context;
        this.recetteList = recetteList;
    }

    public void setRecetteList(List<RecetteModel> recetteList) {
        this.recetteList = new ArrayList<>(this.recetteList);
    }

    @NonNull
    @Override
    public RecetteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recette_recommende_item, parent, false);
        return new RecetteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetteViewHolder holder, int position) {
        RecetteModel recette = this.recetteList.get(position);
        holder.checkBoxFavoris.setChecked(recette.isFavoris());
        holder.textViewMinutes.setText(String.valueOf(recette.getDuration()) + " min");
        holder.textViewRecetteName.setText(recette.getRecetteName());
        holder.textViewNbrIngredientsManquantes.setText(String.valueOf(recette.getIngredientsMissing()) + " ingrédients manquants");

        // Async Process
        String imageUrl = recette.getImageUrl();
        // Assuming you have a method to load the image asynchronously
        loadImageAsync(holder.recetteImageView, imageUrl);

        // Change background drawable based on checked state
        int backgroundDrawable = recette.isFavoris() ?
                R.drawable.heart_vector_checked :
                R.drawable.heart_vector_normal;
        holder.checkBoxFavoris.setBackgroundResource(backgroundDrawable);

        // Set checked state change listener
        holder.checkBoxFavoris.setOnCheckedChangeListener(null); // Remove previous listener to avoid callback conflicts
        holder.checkBoxFavoris.setChecked(recette.isFavoris());
        holder.checkBoxFavoris.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Update background drawable when checked state changes
            int newBackgroundDrawable = isChecked ?
                    R.drawable.heart_vector_checked :
                    R.drawable.heart_vector_normal;
            holder.checkBoxFavoris.setBackgroundResource(newBackgroundDrawable);

            // You may also want to update the checked state in your data model
            recette.setFavoris(isChecked);
        });

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

    public static class RecetteViewHolder extends RecyclerView.ViewHolder {
        ImageView recetteImageView;
        CheckBox checkBoxFavoris;
        TextView textViewMinutes;
        TextView textViewRecetteName;
        TextView textViewNbrIngredientsManquantes;

        public RecetteViewHolder(@NonNull View itemView) {
            super(itemView);
            recetteImageView = itemView.findViewById(R.id.recette_image_view);
            checkBoxFavoris = itemView.findViewById(R.id.checkBox_favoris);
            textViewMinutes = itemView.findViewById(R.id.text_view_min);
            textViewRecetteName = itemView.findViewById(R.id.text_view_recette_name);
            textViewNbrIngredientsManquantes = itemView.findViewById(R.id.text_view_nbr_ingredients_manquantes);

        }
    }

    // Dummy method, replace with your actual image loading logic
    private void loadImageAsync(ImageView imageView, String imageUrl) {
        new GetImageFromUrl(imageView).execute(imageUrl);
    }
}
