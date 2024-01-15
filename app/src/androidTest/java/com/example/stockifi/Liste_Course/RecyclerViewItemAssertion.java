package com.example.stockifi.Liste_Course;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import com.example.stockifi.Liste_Course.Produit;
import com.example.stockifi.R;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.is;

public class RecyclerViewItemAssertion<T> implements ViewAssertion {

    private final T expectedItem;
    private final int recyclerViewId;

    public RecyclerViewItemAssertion(T expectedItem, int recyclerViewId) {
        this.expectedItem = expectedItem;
        this.recyclerViewId = recyclerViewId;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = view.findViewById(recyclerViewId);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        for (int position = 0; position < adapter.getItemCount(); position++) {
            if (expectedItem.equals(adapter.getItemId(position))) {
                return; // L'élément attendu a été trouvé dans la liste
            }
        }

        // L'élément attendu n'a pas été trouvé
        throw new AssertionError("Expected item not found in the RecyclerView");
    }
}
