package com.techelevator;

public class StuffedAnimal {
    private String slot;
    private String name;
    private double price;
    private String animalType;
    private int amount = 5;
    private boolean isSoldOut;

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public boolean isSoldOut(){
        if(this.amount <= 0){
            return true;
        }
        return false;
    }

    public void sellOne(){
        this.amount--;
    }

    public int getAmount(){
        return this.amount;
    }

    public String getMessage(){
        if(animalType.equalsIgnoreCase("pony")){
            return "Neigh, Neigh, Yay!";
        }
        else if(animalType.equalsIgnoreCase("cat")){
            return "Meow, Meow, Meow!";
        }
        else if(animalType.equalsIgnoreCase("penguin")){
            return "Squawk, Squawk, Whee!";
        }
        else if(animalType.equalsIgnoreCase("duck")){
            return "Quack, Quack, Splash!";
        }
        return "";
    }

    public StuffedAnimal(String slot, String name, double price, String animalType){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.animalType = animalType;
    }

    public String toString(){
            return this.slot + " | " + this.name + " | " + this.price + " | " + this.amount;
    }

}
