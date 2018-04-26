package com.dinner2.sarahgardiner.whatsfordinner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ViewRecipeAdminPage extends AppCompatActivity {

    private String rName;
    private Recipe r;
    public static ArrayList<Recipe> UserRecipes = new ArrayList<Recipe>();
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe_admin_page);
        rName = getIntent().getStringExtra("RecipeName");
        r = new Recipe();

        for(int i = 0; i < CreateRecipePage.RecipeList.size(); i++){
            Recipe temp = CreateRecipePage.RecipeList.get(i);
            if(temp.getName().equals(rName)){
                r = temp;
            }
        }

        int ser = r.getServings();
        Log.d("myTag", "ser: "+ ser);
        String instruct = r.getInstructions();
        Log.d("myTag", "insrtuct: "+ instruct);
        String ingred = r.getstrIngredients();
        Log.d("myTag", "ingred: "+ ingred);

        TextView textView7 = (TextView)findViewById(R.id.textView7);
        textView7.setText(rName);

        TextView textView10 = (TextView)findViewById(R.id.textView10);
        textView10.setText("" + ser);

        TextView textView13 = (TextView)findViewById(R.id.textView13);
        textView13.setText(instruct);
        textView13.setMovementMethod(new ScrollingMovementMethod());

        TextView textView12 = (TextView)findViewById(R.id.textView12);
        textView12.setText(ingred);
        textView12.setMovementMethod(new ScrollingMovementMethod());


        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        if(!r.getRecipePicture().equals("none")) {
            Log.d("myTag", "pic: "+ true);
            imageView.setImageBitmap(BitmapFactory.decodeFile(r.getRecipePicture()));
        }
        else {
            //Drawable tempImage = getResources().getDrawable(R.drawable.noimage);
            Drawable tempImage = getResources().getDrawable(R.mipmap.noimage);
            imageView.setImageDrawable(tempImage);
            Log.d("myTag", "pic: "+ false);
        }
    }

    public void TakeOffMyWeekClick(View view) {
        for(int i = 0; i < MyWeekPage.WeekRecipes.size(); i++){
            Recipe temp = MyWeekPage.WeekRecipes.get(i);
            if(temp.getName().equals(rName)){
                MyWeekPage.WeekRecipes.remove(i);
            }
        }

        String fn = "Week.ser";
        try {
            FileOutputStream f = this.openFileOutput(fn, Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream (f);
            o.writeObject (MyWeekPage.WeekRecipes);
            o.close ();
            Log.d("myTag", "File writing: "+ true);
        }
        catch ( Exception ex ) {
            Log.d("myTag", "File writing: "+ false);
            ex.printStackTrace ();
        }

        Log.d("myTag", "arraylist size: "+ MyWeekPage.WeekRecipes.size());
        String msg = "Recipe Removed from Your Week";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, adminMenu.class);
        startActivity(intent);
    }

    public void AddToMyWeekClick(View view) {
        MyWeekPage.WeekRecipes.add(r);

        String fn = "Week.ser";
        try {
            FileOutputStream f = this.openFileOutput(fn, Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream (f);
            o.writeObject (MyWeekPage.WeekRecipes);
            o.close ();
            Log.d("myTag", "File writing: "+ true);
        }
        catch ( Exception ex ) {
            Log.d("myTag", "File writing: "+ false);
            ex.printStackTrace ();
        }

        String msg = "Recipe Added to Your Week";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, adminMenu.class);
        startActivity(intent);
    }

    public void DeleteRecipeClick(View view) {
        for(int i = 0; i < CreateRecipePage.RecipeList.size(); i++){
            Recipe temp = CreateRecipePage.RecipeList.get(i);
            if(temp.getName().equals(rName)){
                CreateRecipePage.RecipeList.remove(i);
            }
        }

        //-------------save recipe list to phone -----------------------

        String fn = "Recipes.ser";

        try {
            FileOutputStream f = this.openFileOutput(fn, Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream (f);
            o.writeObject (CreateRecipePage.RecipeList);
            o.close ();
            Log.d("myTag", "File writing: "+ true);
        }
        catch ( Exception ex ) {
            Log.d("myTag", "File writing: "+ false);
            ex.printStackTrace ();
        }

        String msg = "Recipe Deleted";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, adminMenu.class);
        startActivity(intent);

    }

    public void onEmailClick(View view) {
        String[] to = {""};
        String[] cc = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, r.getName()+" Recipe - What's for Dinner?");
        String buffer = "Name:  "+r.getName()+"\n";
        buffer += "Type:  "+r.getCategory()+"\n";
        buffer += "Servings:  "+r.getServings()+"\n\n";
        buffer += "Ingredients:\n"+r.getstrIngredients()+"\n";
        buffer += "Instructions:\n"+r.getInstructions()+"\n";
        emailIntent.putExtra(Intent.EXTRA_TEXT, buffer);
        startActivity(Intent.createChooser(emailIntent, "Sending mail..."));
        finish();

    }
}
