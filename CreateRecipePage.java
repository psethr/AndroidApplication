package com.dinner2.sarahgardiner.whatsfordinner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateRecipePage extends AppCompatActivity {

    public static ArrayList<Recipe> RecipeList = new ArrayList<Recipe>();
    private static int RESULT_LOAD_IMAGE = 1;
    public static String picturePath = "none";
    private final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 1;
    private final int MY_PERMISSIONS_MANAGE_DOCUMENTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_page);
    }


    public void onSubmitClick(View view) throws PackageManager.NameNotFoundException, IOException {
        //-------------- get recipe name ---------------------------------
        EditText mEdit   = (EditText)findViewById(R.id.recipeName);
        String rName = mEdit.getText().toString().toLowerCase();
        Log.d("myTag", "RecipeName: "+ rName);

        //-------------- get serving size ---------------------------------
        EditText mEdit2   = (EditText)findViewById(R.id.recipeServings);
        String ser = mEdit2.getText().toString();
        int rServing = Integer.parseInt(ser);
        Log.d("myTag", "Recipe serving: "+ rServing);

        //-------------- get recipe instructions ----------------------------
        EditText mEdit3   = (EditText)findViewById(R.id.recipeInstuctions);
        String rInstructions = mEdit3.getText().toString();
        Log.d("myTag", "rInstructions: "+ rInstructions);

        //---------------- get category ------------------------------------
        Spinner cat = (Spinner)findViewById(R.id.Category);
        String rCategory = cat.getSelectedItem().toString();
        Log.d("myTag", "rCategory: "+ rCategory);

        //--------------- recipe object created ----------------------------
        if(rName.trim().length() == 0 || ser.trim().length() == 0 || rCategory.trim().length() == 0){
            finish();

        }
        else {
            Recipe r = new Recipe(rName, rInstructions, rCategory,
                    rServing, picturePath);//, AddIngredientPage.IngredientList);
            //add recipe to arraylist thing
            for(int i = 0; i < AddIngredientPage.IngredientList.size(); i++){
                Ingredient in = AddIngredientPage.IngredientList.get(i);
                r.addIngredient(in);
            }
            String ingred = r.getstrIngredients();
            Log.d("myTag", "ingred (create): "+ ingred);
            RecipeList.add(r);
            picturePath = "none";

        }

        AddIngredientPage.IngredientList.clear();

        //-------------save recipe list to phone -----------------------

        String packageName = this.getPackageName();
        String path = getFilesDir().getAbsolutePath()+ packageName;
        Log.d("myTag", "File writing: "+ path);

        String fn = "Recipes.ser";

        try {
            FileOutputStream f = this.openFileOutput(fn, Context.MODE_PRIVATE);
            ObjectOutputStream o = new ObjectOutputStream (f);
            o.writeObject (RecipeList);
            o.close ();
            Log.d("myTag", "File writing: "+ true);
        }
        catch ( Exception ex ) {
            Log.d("myTag", "File writing: "+ false);
            ex.printStackTrace ();
        }

        String msg = "Recipe Added";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
        finish();
    }

    public void onIngredientClick(View view) {
        Intent intent = new Intent(this, AddIngredientPage.class);
        startActivity(intent);
    }

    public void addPicture(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE);


        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_DOCUMENTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_MANAGE_DOCUMENTS);
        }


        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            case MY_PERMISSIONS_MANAGE_DOCUMENTS :{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }


    }

}
