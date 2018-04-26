package com.dinner2.sarahgardiner.whatsfordinner;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sarahgardiner on 3/11/18.
 */

public class Recipe implements Serializable {
    private String name;
    private String instructions;
    private String category;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private int servings;
    private String StrIngredients;
    private String picturePath;

    public Recipe(String name, String instructions, String category, int servings, String s){//, ArrayList<Ingredient> i){
        this.name = name;
        if(instructions.equals("")){
            this.instructions = "No Instructions";
        }
        else
            this.instructions = instructions;
        this.category = category;
        this.servings = servings;
        //this.ingredients = i;
        String answer = "";
        for (int j = 0; j < AddIngredientPage.IngredientList.size(); j++){
            Ingredient temp = AddIngredientPage.IngredientList.get(j);
            String a = temp.getStringIngredient();
            answer = answer + a + " \n";
        }
        this.StrIngredients = answer;
        picturePath = s;
    }

    public Recipe(){
        name = null;
        instructions = null;
        category = null;
        servings = 0;
        ingredients = null;
        StrIngredients = null;
    }

    public String getRecipePicture(){ return this.picturePath;}

    public String getName(){
        return this.name;
    }

    public String getInstructions(){
        return this.instructions;
    }

    public String getCategory(){
        return this.category;
    }

    public int getServings(){
        return this.servings;
    }

    public ArrayList getIngredients(){
         return this.ingredients;
    }

    public String getstrIngredients(){
        return this.StrIngredients;
        /*
        String answer = "";
        for (int i = 0; i < AddIngredientPage.IngredientList.size(); i++){
            Ingredient temp = AddIngredientPage.IngredientList.get(i);
            String a = temp.getStringIngredient();
            answer = answer + a + " \n";
        }
        this.StrIngredients = answer;
        return answer;
        */
    }

    public void setName(String n){
        name = n;
    }

    public void setInstructions(String i){
        instructions = i;
    }

    public void setCategory(String c){
        category = c;
    }

    public void setServings(int s){
        servings = s;
    }

    public void addIngredient(Ingredient i){
        ingredients.add(i);
    }

    public Ingredient getIngredientsOneAtATime(int index){
        return (Ingredient) ingredients.get(index);
    }

}
