package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Purchase {
    private File log = new File("C:/Users/Student/workspace/java-minicapstonemodule1-team4/capstone/Log.txt");
    Scanner userInput = new Scanner(System.in);
    private double currentMoney = 0.00;
    private List<StuffedAnimal> productList = new ArrayList<StuffedAnimal>();


    public void purchaseMenu(){
        boolean stillInMenu = true;
        while(stillInMenu){
            System.out.println("Purchase Menu");
            System.out.printf("Current Money Provided: %s", currentMoney);
            System.out.println("\n(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\nSelect an Option");
            String subChoice = userInput.nextLine();

            switch (subChoice){
                case "1":
                    System.out.println("Enter an amount to feed: $");
                    double amount = Double.parseDouble(userInput.nextLine());
                    feedMoney(amount);
                    break;
                case "2":
                        System.out.println(selectProduct());
                        break;

                case "3":
                    finishTransaction();
                    stillInMenu = false;

            }


        }
    }

    public double feedMoney (double amountToFeed){
        logTransaction("FEED MONEY", amountToFeed);
        currentMoney += amountToFeed;
        return currentMoney;
    }

    public String selectProduct (){
        ImportFile getProducts = new ImportFile();
        productList = getProducts.importList();
        getProducts.displayProducts();

        String slot = userInput.nextLine();
            for (StuffedAnimal product : productList) {
                if(product.getSlot().equalsIgnoreCase(slot)) {
                if (slot.equalsIgnoreCase(product.getSlot()) && !product.isSoldOut() && currentMoney >= product.getPrice()) {
                    currentMoney -= product.getPrice();
                    product.sellOne();
                    logTransaction(product.getName()+ " " + product.getSlot(), product.getPrice());
                    return product.getName() + ", " + product.getPrice() + ", " + this.currentMoney + "\n" + product.getMessage();
                } else if (currentMoney < product.getPrice()){
                    System.out.println("Not enough funds. Please insert money to continue.");
                } else {
                    System.out.println("Item is sold out.");
                }
            }
        }

        return "Please enter a valid slot number";
    }

    public void  finishTransaction(){
        logTransaction("GIVE CHANGE", currentMoney);
        final double QUARTER = 0.25;
        final double DIME = 0.1;
        final double NICKEL = 0.05;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        while(currentMoney != 0.00){
            if(currentMoney >= QUARTER){
                quarterCount++;
                currentMoney -= QUARTER;
            } else if(currentMoney >= DIME){
                dimeCount++;
                currentMoney -= DIME;
            } else if(currentMoney >= NICKEL){
                nickelCount++;
                currentMoney -= NICKEL;
            }
            else {
                currentMoney = .00;

            }
        }
        System.out.printf("Your change is: %s quarters, %s dimes, and %s nickels.", quarterCount,dimeCount,nickelCount);
    }

    private void logTransaction(String action, double moneyToChange) {
        LocalDateTime date = LocalDateTime.now();

        if (!log.exists()) {
            System.out.println("File not found.");
            System.exit(1);
        } else if (!log.isFile()) {
            System.out.println("Is not a file.");
            System.exit(1);
        }

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            if(action.equals("FEED MONEY")){
                writer.println(date + " " + action + ": " + "$" + moneyToChange + " $" + currentMoney);
            } else if(action.equals("GIVE CHANGE")){
                writer.println(date + " " + action + ": " + "$" + moneyToChange + " $" + (0.00));
            } else {
                writer.println(date + " " + action + ": " + "$" + moneyToChange + " $" + currentMoney);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
