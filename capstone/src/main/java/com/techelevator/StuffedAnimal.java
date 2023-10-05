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

    public StuffedAnimal(String slot, String name, double price, String animalType){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.animalType = animalType;
    }

    public String toString(){
        if(!isSoldOut) {
            return this.slot + " | " + this.name + " | " + this.price + " | " + this.amount;
        }
        else{
            return this.slot + " | " + this.name + " | " + this.price + " | SOLD OUT";
        }
    }

}
