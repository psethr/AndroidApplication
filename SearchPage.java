package com.dinner2.sarahgardiner.whatsfordinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {

    public static ArrayList<Recipe> searchArray = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
    }


    public void onSearchClick(View view) {
        Spinner cat = (Spinner)findViewById(R.id.spinner);
        String rCategory = cat.getSelectedItem().toString();
        Log.d("myTag", "rCategory: "+ rCategory);

        if(rCategory.equals("All")){
            Intent intent = new Intent(this, BrowseRecipeAdminPage.class);
            startActivity(intent);
        }
        else {
            for (int i = 0; i < CreateRecipePage.RecipeList.size(); i++) {
                Recipe temp = new Recipe();
                temp = CreateRecipePage.RecipeList.get(i);
                Log.d("myTag", "rName: " + temp.getName());
                if (temp.getCategory().equals(rCategory)) {
                    searchArray.add(temp);
                }
            }

            Intent intent = new Intent(this, SearchResultsPage.class);
            startActivity(intent);
        }

    }
}
