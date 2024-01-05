package com.example.stockifi.recettes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.R;

import java.util.List;

public class InstructionDePreparationAdapter extends RecyclerView.Adapter<InstructionDePreparationAdapter.InstructionDePreparationViewHolder> {
    private Context context;
    private List<String> instructionsDePreparation;

    public InstructionDePreparationAdapter(Context context, List<String> instructions) {
        this.context = context;
        this.instructionsDePreparation = instructions;
    }

    @NonNull
    @Override
    public InstructionDePreparationAdapter.InstructionDePreparationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recette_instruction_de_preparation_item, parent, false);
        return new InstructionDePreparationAdapter.InstructionDePreparationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionDePreparationAdapter.InstructionDePreparationViewHolder holder, int position) {
        String instructionDePreparation = this.instructionsDePreparation.get(position);
        holder.instructionTextView.setText(instructionDePreparation);
    }

    @Override
    public int getItemCount() {
        return this.instructionsDePreparation.size();
    }

    public static class InstructionDePreparationViewHolder extends RecyclerView.ViewHolder {
        TextView instructionTextView;

        public InstructionDePreparationViewHolder(@NonNull View itemView) {
            super(itemView);
            instructionTextView = itemView.findViewById(R.id.recette_instruction_de_preparation_item_text_view);
        }
    }
}

