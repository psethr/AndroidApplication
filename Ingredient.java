package com.dinner2.sarahgardiner.whatsfordinner;

import java.io.Serializable;

/**
 * Created by sarahgardiner on 3/11/18.
 */

public class Ingredient implements Serializable {

        private String name;
        private String type;
        private String measurment;
        private double amount;
        private boolean check;

        public Ingredient(String n, String t, String m, String a, int b){
            name = n;
            type = t;
            measurment = m;
            double temp = 0;
            if(a.equals("0")){
                temp = 0;
            }
            else if(a.equals("1/4")){
                temp = .24;
            }
            else if(a.equals("1/16")){
                temp = .0625;
            }
            else if(a.equals("1/8")) {
                temp = .125;
            }
            else if(a.equals("1/3")){
                temp = .333;
            }
            else if(a.equals("1/2")){
                temp = .5;
            }
            else if(a.equals("2/3")){
                temp = .666;
            }
            else {
                temp = .75;
            }
            amount = temp + b;
            check = false;
        }

        public Ingredient(){
            this.name = null;
            this.type= null;
            this.measurment = null;
            this.amount = 0.0;
            this.check = false;
        }
        public void setcheck(boolean a){this.check = a;}

    public boolean getcheck(){return this.check;}

        public String getName(){
            return this.name;
        }

        public String getType(){
            return this.type;
        }

        public double getAmount(){
            return this.amount;
        }

        public String getMeasurment() { return this.measurment; }

        public String  getStringIngredient(){
            String answer = "";
            answer = amount + " "+ measurment +" "+ name;
            return answer;
        }

        public void setName(String n){
            name = n;
        }

        public void setType(String t){
            type = t;
        }

        public void setAmount(double a){
            amount = a;
        }
        public void setMeasurement(String m){
            measurment = m;
        }
        public double toTsp(){
            double tsp = 0;
            String m = this.getMeasurment();
            if(m.equals("Tsp")){
                tsp = this.getAmount();
            }
            else if(m.equals("Tbsp")){
                tsp = 3 * this.getAmount();
            }
            else if(m.equals("Cup")){
                tsp = 48.6922 * this.getAmount();
            }
            else if(m.equals("Item")){
                tsp = this.getAmount();
            }
            else if(m.equals("Slice")){
                tsp = this.getAmount();
            }
            return tsp;
        }
    }