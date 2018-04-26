package com.dinner2.sarahgardiner.whatsfordinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MyWeekPage extends AppCompatActivity {

    public static ArrayList<Recipe> WeekRecipes = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_week_page);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button btn[] = new Button[WeekRecipes.size()];
        for(int i = 0; i < WeekRecipes.size(); i++) {
            Recipe r = WeekRecipes.get(i);
            Log.d("myTag", "RecipeName: "+ r.getName());
            final String n = r.getName();
            btn[i] = new Button(this);
            btn[i].setText(n);
            btn[i].setTextSize(20);
            btn[i].setGravity(Gravity.CENTER);
            btn[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), ViewRecipeWeekPage.class);
                    intent.putExtra("RecipeName", n);
                    startActivity(intent);
                    //startActivity(new Intent(BrowseRecipesPage.this, ViewRecipePage.class));
                }
            });
            linearLayout.addView(btn[i]);
        }

        scrollView.addView(linearLayout);

        this.setContentView(scrollView);

       // this.setContentView(linearLayout, new LinearLayout.LayoutParams(
         //       LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


    }
}
