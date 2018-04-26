package com.dinner2.sarahgardiner.whatsfordinner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SplashPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
    }

    public void onSplashPageClick(View view) {
        // String packageName = this.getPackageName();
        //String path = getFilesDir().getAbsolutePath()+ packageName;
        // String FILENAME = "Recipes.ser";

        try {
            String fn = this.getFilesDir() + "/Recipes.ser"; // "/data/data/com.dinner2.sarahgardiner.whatsfordinner/files/Recipes.ser";
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fn));
            ArrayList<Recipe> RecipesFile = (ArrayList<Recipe>) input.readObject();
            input.close();
            CreateRecipePage.RecipeList = RecipesFile;
            Log.d("myTag", "File writing: "+ true);
        }
        catch (Exception e) {
            Log.d("myTag", "File writing: "+ false);
            e.printStackTrace();
        }

        try {
            String fn = this.getFilesDir() + "/Week.ser"; //  "/data/data/com.dinner2.sarahgardiner.whatsfordinner/files/Week.ser";
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(fn));
            ArrayList<Recipe> WeekFile = (ArrayList<Recipe>) input.readObject();
            input.close();
            MyWeekPage.WeekRecipes = WeekFile;
            Log.d("myTag", "File writing: "+ true);
        }
        catch (Exception e) {
            Log.d("myTag", "File writing: "+ false);
            e.printStackTrace();
        }
        Intent intent = new Intent(this, adminMenu.class);
        startActivity(intent);
        finish();

///data/user/0/com.dinner.sarahgardiner.whatsfordinner/filescom.dinner.sarahgardiner.whatsfordinner
    }
}
