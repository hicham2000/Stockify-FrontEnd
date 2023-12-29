package com.example.stockifi.recettes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stockifi.R;
import com.example.stockifi.recettes.RecetteModel;

import java.util.List;

public class RecettesAdapter extends RecyclerView.Adapter<RecettesAdapter.RecetteViewHolder> {
    private List<RecetteModel> recetteList;
    private Context context;

    public RecettesAdapter(Context context, List<RecetteModel> recetteList) {
        this.context = context;
        this.recetteList = recetteList;
    }

    public void setRecetteList(List<RecetteModel> recetteList) {
        this.recetteList = recetteList;
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
        RecetteModel recette = recetteList.get(position);
        holder.checkBoxFavoris.setChecked(recette.isFavoris());
        holder.textViewMinutes.setText(String.valueOf(recette.getDuration()) + " min");
        holder.textViewRecetteName.setText(recette.getRecetteName());
        holder.textViewNbrIngredientsManquantes.setText(String.valueOf(recette.getIngredientsMissing()) + " ingrédients manquants");

        // Async Process
        String imageUrl = recette.getImageUrl();
        // Assuming you have a method to load the image asynchronously
        loadImageAsync(holder.recetteImageView, imageUrl);

        // Change background color based on checked state
        int backgroundDrawable = recette.isFavoris() ?
                R.drawable.heart_vector_checked :
                R.drawable.heart_vector_normal;
        holder.checkBoxFavoris.setBackgroundResource(backgroundDrawable);
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
    }

    @Override
    public int getItemCount() {
        return recetteList.size();
    }

    public static class RecetteViewHolder extends RecyclerView.ViewHolder {
        public ImageView recetteImageView;
        public CheckBox checkBoxFavoris;
        public TextView textViewMinutes;
        public TextView textViewRecetteName;
        public TextView textViewNbrIngredientsManquantes;

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
