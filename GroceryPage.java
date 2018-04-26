package com.dinner2.sarahgardiner.whatsfordinner;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GroceryPage extends AppCompatActivity {


    public static ArrayList<Ingredient> GroceryListObj = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> GL = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Produce = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Dairy = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Meat = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Frozen = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Bakery = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> CannedDryGoods = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Spices = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> PastaRice = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Oilsauces = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> SnacksCrackers = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> Drinks = new ArrayList<Ingredient>();
    public static ArrayList<Ingredient> GLsorted = new ArrayList<Ingredient>();




    public static void combine(){
        for (int i = 0; i < GroceryListObj.size(); i++) {
            Ingredient one = GroceryListObj.get(i);
            for (int j = 1+i; j < GroceryListObj.size(); j++){
                Ingredient two = GroceryListObj.get(j);
                if(one.getName().equals(two.getName())){
                    double totAmount = one.toTsp() + two.toTsp();
                    one.setAmount(totAmount);
                    if(!one.getMeasurment().equals("Item"))
                        one.setMeasurement("Tsp");
                    GroceryListObj.remove(j);
                    if(!GL.contains(one)){
                        GL.add(one);
                    }
                    else{
                        for(int k = 0; k < GL.size(); k++){
                            if(GL.get(k).getName().equals(one.getName())){
                                double amount = GL.get(k).toTsp() + one.toTsp();
                                GL.get(k).setAmount(amount);
                                if(!one.getMeasurment().equals("Item"))
                                    GL.get(k).setMeasurement("Tsp");
                            }
                        }
                    }
                }
            }
            if(!GL.contains(one)){
                GL.add(one);
            }
        }
    }

    public static void sort(){
        for(int i = 0; i < GL.size(); i++){
            Ingredient a = GL.get(i);
            if(a.getType().equals("Produce")){
                System.out.println("Ingrdient: "+ a.getStringIngredient());
                Produce.add(a);
            }
            else if(a.getType().equals("Dairy")){
                Dairy.add(a);
            }
            else if(a.getType().equals("Meat")){
                Meat.add(a);
            }
            else if(a.getType().equals("Frozen")){
                Frozen.add(a);
            }
            else if(a.getType().equals("Bakery")){
                Bakery.add(a);
            }
            else if(a.getType().equals("Canned and Dry Goods")){
                CannedDryGoods.add(a);
            }
            else if(a.getType().equals("Spices")){
                Spices.add(a);
            }
            else if(a.getType().equals("Pasta and Rice")){
                PastaRice.add(a);
            }
            else if(a.getType().equals("Oils and Sauces")){
                Oilsauces.add(a);
            }
            else if(a.getType().equals("Snacks and Crackers")){
                SnacksCrackers.add(a);
            }
            else if(a.getType().equals("Drinks")){
                Drinks.add(a);
            }
        }
        GLsorted.addAll(Produce);
        GLsorted.addAll(Dairy);
        GLsorted.addAll(Meat);
        GLsorted.addAll(Frozen);
        GLsorted.addAll(Bakery);
        GLsorted.addAll(CannedDryGoods);
        GLsorted.addAll(Spices);
        GLsorted.addAll(PastaRice);
        GLsorted.addAll(Oilsauces);
        GLsorted.addAll(SnacksCrackers);
        GLsorted.addAll(Drinks);
    }

    public static void condense(){
        for(int i = 0; i < GLsorted.size(); i++){
            Ingredient g = GLsorted.get(i);
            if(g.getMeasurment().equals("Tsp")){
                double amount = g.getAmount();
                if(amount <= 9){
                    g.setAmount((double)Math.round(amount * 100d) / 100d);
                    g.setMeasurement("Tsp");
                }
                else{
                    double Cup = amount/48.6922;
                    if(Cup <= .2){
                        double Tbsp = amount/.33;
                        g.setAmount((double)Math.round(Tbsp * 100d) / 100d);
                        g.setMeasurement("Tbsp");
                    }
                    else{
                        g.setAmount((double)Math.round(Cup * 100d) / 100d);
                        g.setMeasurement("Cup");
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_page);

        for(int i = 0; i < MyWeekPage.WeekRecipes.size(); i++){
            Recipe r = MyWeekPage.WeekRecipes.get(i);
            //Log.d("myTag", "rIngredient size: "+ rIngredients.size());
            for(int k = 0; k < r.getIngredients().size(); k++){
                GroceryListObj.add((Ingredient) r.getIngredientsOneAtATime(k));
            }
        }

        GroceryListObj.addAll(AddGroceryItemPage.AddedGroceries);

        combine();
        sort();
        condense();


        //tiffany below here please :) please generate check boxes instead of buttons
        //the sorted and condensed array list to use is Glsorted
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //CheckBox cb = new CheckBox([GLsorted.size()]);
        TextView pro = new TextView(this);
        TextView pro1 = new TextView(this);
        TextView pro2 = new TextView(this);
        TextView pro3 = new TextView(this);
        TextView pro4 = new TextView(this);
        TextView pro5 = new TextView(this);
        TextView pro6 = new TextView(this);
        TextView pro7 = new TextView(this);
        TextView pro8 = new TextView(this);
        TextView pro9 = new TextView(this);
        TextView pro10 = new TextView(this);
        pro.setTypeface(null, Typeface.BOLD);
        pro.setTextSize(30);
        pro.setGravity(Gravity.LEFT);
        pro.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro.setText("PRODUCE");
        linearLayout.addView(pro);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("produce")) {
               final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele2.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro1.setTypeface(null, Typeface.BOLD);
        pro1.setTextSize(40);
        pro1.setGravity(Gravity.LEFT);
        pro1.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro1.setText("DAIRY");
        linearLayout.addView(pro1);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("dairy")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro2.setTypeface(null, Typeface.BOLD);
        pro2.setTextSize(40);
        pro2.setGravity(Gravity.LEFT);
        pro2.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro2.setText("MEAT");
        linearLayout.addView(pro2);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("meat")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro3.setTypeface(null, Typeface.BOLD);
        pro3.setTextSize(40);
        pro3.setGravity(Gravity.LEFT);
        pro3.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro3.setText("FROZEN");
        linearLayout.addView(pro3);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("frozen")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro4.setTypeface(null, Typeface.BOLD);
        pro4.setTextSize(40);
        pro4.setGravity(Gravity.LEFT);
        pro4.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro4.setText("BAKERY");
        linearLayout.addView(pro4);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("bakery")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro5.setTypeface(null, Typeface.BOLD);
        pro5.setTextSize(40);
        pro5.setGravity(Gravity.LEFT);
        pro5.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro5.setText("CANNED/DRY GOODS");
        linearLayout.addView(pro5);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("canned and dry goods")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro6.setTypeface(null, Typeface.BOLD);
        pro6.setTextSize(40);
        pro6.setGravity(Gravity.LEFT);
        pro6.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro6.setText("SPICES");
        linearLayout.addView(pro6);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("spices")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro7.setTypeface(null, Typeface.BOLD);
        pro7.setTextSize(40);
        pro7.setGravity(Gravity.LEFT);
        pro7.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro7.setText("PASTA/RICE");
        linearLayout.addView(pro7);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("pasta and rice")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro8.setTypeface(null, Typeface.BOLD);
        pro8.setTextSize(40);
        pro8.setGravity(Gravity.LEFT);
        pro8.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro8.setText("OILS/SAUCES");
        linearLayout.addView(pro8);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("oils and sauces")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro9.setTypeface(null, Typeface.BOLD);
        pro9.setTextSize(40);
        pro9.setGravity(Gravity.LEFT);
        pro9.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro9.setText("SNACKS/CRACKERS");
        linearLayout.addView(pro9);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("snacks and crackers")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }
        pro10.setTypeface(null, Typeface.BOLD);
        pro10.setTextSize(40);
        pro10.setGravity(Gravity.LEFT);
        pro10.setTextColor(ContextCompat.getColor(this, R.color.black));
        pro10.setText("DRINKS");
        linearLayout.addView(pro10);
        for(Ingredient ele : GLsorted){
            if(ele.getType().toLowerCase().equals("drinks")) {
                final Ingredient ele2 = ele;
                CheckBox cb = new CheckBox(this);
                final String n = ele.getStringIngredient();
                cb.setText(n);
                cb.setTextSize(25);
                cb.setGravity(Gravity.CENTER);
                if(ele2.getcheck() == true){
                    cb.setChecked(true);
                }
                else
                    cb.setChecked(false);
                cb.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(ele2.getcheck() == false)
                            ele2.setcheck(true);
                        else
                            ele2.setcheck(false);
                        String fn = "Week.ser";

                        try {
                            FileOutputStream f = openFileOutput(fn, Context.MODE_PRIVATE);
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(MyWeekPage.WeekRecipes);
                            o.close();
                            Log.d("myTag", "File writing: " + true);
                        } catch (Exception ex) {
                            Log.d("myTag", "File writing: " + false);
                            ex.printStackTrace();
                        }
                    }
                });
                linearLayout.addView(cb);
            }
        }

        /*
        for(int i = 0; i < GLsorted.size(); i++) {
            CheckBox cb = new CheckBox(this);
            Ingredient g = GLsorted.get(i);
            Log.d("myTag", "Ingredient2: "+ g.getName());
            final String n = g.getStringIngredient();
            Log.d("myTag", "Ingredient2: "+ g.getStringIngredient());
            //cb[i] = new Button(this);
            cb.setText(n);
            cb.setTextSize(20);
            cb.setGravity(Gravity.CENTER);
            linearLayout.addView(cb);
        }*/
        scrollView.addView(linearLayout);

        this.setContentView(scrollView);
       //this.setContentView(linearLayout, new LinearLayout.LayoutParams(
         //       LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        GroceryListObj.clear();
        GL.clear();
        GLsorted.clear();
        Produce.clear();
        Dairy.clear();
        Meat.clear();
        Bakery.clear();
        CannedDryGoods.clear();
        Spices.clear();
        PastaRice.clear();
        Oilsauces.clear();
        SnacksCrackers.clear();
        Drinks.clear();
        GLsorted.clear();

    }
}
