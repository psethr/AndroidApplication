package com.dinner2.sarahgardiner.whatsfordinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddGroceryItemPage extends AppCompatActivity {

    public static ArrayList<Ingredient> AddedGroceries = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery_item_page);
    }

    public void addGroceryItemClick(View view) {
        EditText mEdit   = findViewById(R.id.iName);
        String IngredientName = mEdit.getText().toString();
        Log.d("myTag", "IngredientName: "+ IngredientName);

        Spinner Measurement = findViewById(R.id.iMeasurement);
        String IngredientMeasurement = Measurement.getSelectedItem().toString();

        Spinner fractions = findViewById(R.id.iFractions);
        String IngredientFractions = fractions.getSelectedItem().toString();
        //int F = Integer.parseInt(IngredientFractions);
        Log.d("myTag", "Fractions: "+ IngredientFractions);

        Spinner digits = findViewById(R.id.iDigits);
        String IngredientDigits = digits.getSelectedItem().toString();
        int D = Integer.parseInt(IngredientDigits);
        Log.d("myTag", "Fractions: "+ D);

        Spinner type = findViewById(R.id.iType);
        String IngredientType = type.getSelectedItem().toString();

        Log.d("myTag", "IngredientType: "+ IngredientType);

        boolean c = false;
        Ingredient I = new Ingredient(IngredientName, IngredientType, IngredientMeasurement,
                IngredientFractions, D);

        AddedGroceries.add(I);


        String msg = "Ingredient Added";
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
        finish();

    }

}
