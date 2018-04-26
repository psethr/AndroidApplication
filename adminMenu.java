package com.dinner2.sarahgardiner.whatsfordinner;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class adminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
    }

    public void onCreateClick(View view) {
        Intent intent = new Intent(this, CreateRecipePage.class);
        startActivity(intent);
    }

    public void onBrowseClick(View view) {
        Intent intent = new Intent(this, BrowseRecipeAdminPage.class);
        startActivity(intent);
    }

    public void onWeekClick(View view) {
        Intent intent = new Intent(this, MyWeekPage.class);
        startActivity(intent);
    }

    public void onSearchClick(View view) {
        Intent intent = new Intent(this, SearchPage.class);
        startActivity(intent);
    }

    public void onGroceryClick(View view) {
        Intent intent = new Intent(this, GroceryPage.class);
        startActivity(intent);
    }

    public void onFaqClick(View view) {
        Intent intent = new Intent(this, FaqAdminPage.class);
        startActivity(intent);
    }

    public void onAddGroceryClick(View view) {
        Intent intent = new Intent(this, AddGroceryItemPage.class);
        startActivity(intent);
    }

    public void onDeleteClick(View view) {
        AddGroceryItemPage.AddedGroceries.clear();
        String msg = "Deleted";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    public void onEmailClick(View view) {
        for(int i = 0; i < MyWeekPage.WeekRecipes.size(); i++){
            Recipe r = MyWeekPage.WeekRecipes.get(i);
            //Log.d("myTag", "rIngredient size: "+ rIngredients.size());
            for(int k = 0; k < r.getIngredients().size(); k++){
                GroceryPage.GroceryListObj.add((Ingredient) r.getIngredientsOneAtATime(k));
            }
        }

        GroceryPage.GroceryListObj.addAll(AddGroceryItemPage.AddedGroceries);

        GroceryPage.combine();
        GroceryPage.sort();
        GroceryPage.condense();


        Log.i("AmminMenu", "                 1                 ");
        String[] to = {""};
        String[] cc = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Grocery List - What's for Dinner?");
        String buffer = "Your Grocery List:\n\n";
        Log.i("AmminMenu", "                 Size of GLsorted Arraylist: "+GroceryPage.GLsorted.size()+"                 ");
        buffer = buffer+"PRODUCE\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("produce")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nDAIRY\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("dairy")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nMEAT\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("meat")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nFROZEN\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("frozen")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nBAKERY\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("bakery")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nCANNED/ DRY GOODS\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("canned and dry goods")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nSPICES\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("spices")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nPASTA/ RICE\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("pasta and rice")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nOILS/ SAUCES\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("oils and sauces")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nSNACKS/ CRACKERS\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("snacks and crackers")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }
        buffer = buffer+"\nDRINKS\n";
        for(Ingredient ele : GroceryPage.GLsorted)
        {
            if(ele.getType().toLowerCase().equals("drinks")) {
                buffer = buffer + ele.getStringIngredient() + "\n";
            }
        }

        emailIntent.putExtra(Intent.EXTRA_TEXT, buffer);
        Log.i("AmminMenu", "                 3                 ");

        GroceryPage.GroceryListObj.clear();
        GroceryPage.GL.clear();
        GroceryPage.GLsorted.clear();
        GroceryPage.Produce.clear();
        GroceryPage.Dairy.clear();
        GroceryPage.Meat.clear();
        GroceryPage.Frozen.clear();
        GroceryPage.Bakery.clear();
        GroceryPage.CannedDryGoods.clear();
        GroceryPage.Spices.clear();
        GroceryPage.PastaRice.clear();
        GroceryPage.Oilsauces.clear();
        GroceryPage.SnacksCrackers.clear();
        GroceryPage.Drinks.clear();
        GroceryPage.GLsorted.clear();

        try{
            startActivity(Intent.createChooser(emailIntent, "Sending mail..."));
            Log.i("AmminMenu", "                 4                 ");
            Log.i("AmminMenu", "                 5                 ");
        } catch (ActivityNotFoundException e)
        {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
