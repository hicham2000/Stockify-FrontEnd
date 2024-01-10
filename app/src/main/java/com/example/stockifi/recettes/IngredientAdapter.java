package com.example.stockifi.recettes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.Liste_Course.AjouterProduit;
import com.example.stockifi.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private Context context;
    private List<RecetteModel.IngredientInfo> ingredients;

    public IngredientAdapter(Context context, List<RecetteModel.IngredientInfo> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recette_ingredient_item, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        RecetteModel.IngredientInfo ingredient = this.ingredients.get(position);

        holder.ingredientTextView.setText(formatDoubleValue(ingredient.getQuantity()) + " " + ingredient.getIntitule());

        if (!ingredient.isEnough()) {
            holder.ingredientValidImageView.setVisibility(View.GONE);
            holder.ingredientNotValidImageView.setVisibility(View.VISIBLE);
            holder.AddIngredientImageButton.setVisibility(View.VISIBLE);
        } else {
            holder.ingredientValidImageView.setVisibility(View.VISIBLE);
            holder.ingredientNotValidImageView.setVisibility(View.GONE);
            holder.AddIngredientImageButton.setVisibility(View.GONE);
        }

        holder.AddIngredientImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click, for example, navigate to AjouterProduit activity
                Intent intent = new Intent(context, AjouterProduit.class);
                intent.putExtra("ingredient", ingredient);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientTextView;
        ImageView ingredientValidImageView;
        ImageView ingredientNotValidImageView;
        ImageButton AddIngredientImageButton;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientTextView = itemView.findViewById(R.id.text_view_recette_ingredient);
            ingredientValidImageView = itemView.findViewById(R.id.image_view_ingredient_valide);
            ingredientNotValidImageView = itemView.findViewById(R.id.image_button_ingredient_non_valide);
            AddIngredientImageButton = itemView.findViewById(R.id.imageButton_add_ingredient);
        }
    }

    private String formatDoubleValue(double value) {
        return (value - (int) value == 0) ? String.valueOf((int) value) : String.valueOf(round(value, 2));
    }

    private double round(double val, int places) {
        if (places < 0) throw new IllegalArgumentException();
        if(places == 0) {
            return (double) Math.round(val);
        }
        int tmp = (int) Math.pow(10, places);
        return (double) ((double)Math.round(val * tmp) / tmp);
    }
}
