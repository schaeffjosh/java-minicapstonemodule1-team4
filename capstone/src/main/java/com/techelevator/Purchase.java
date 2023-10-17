package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Purchase {
    private File log = new File("C:/Users/Student/workspace/java-minicapstonemodule1-team4/capstone/Log.txt");
    Scanner userInput = new Scanner(System.in);
    private double currentMoney = 0.00;
    private List<StuffedAnimal> productList;


    public Purchase(List<StuffedAnimal> list) {
        this.productList = list;
    }




    public void purchaseMenu() {
        boolean stillInMenu = true;
        while (stillInMenu) {
            System.out.println("Purchase Menu");
            System.out.printf("Current Money Provided: %s", currentMoney);
            System.out.println("\n(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
            System.out.println("\nSelect an Option");
            String subChoice = userInput.nextLine();


            switch (subChoice) {
                case "1":
                    System.out.println("Enter an amount to feed: ");
                    String putIn = userInput.nextLine();
                    double amount = 0;
                    if( putIn.equalsIgnoreCase("")){
                        amount = 0;
                    }else {
                        amount = Double.parseDouble(putIn);
                    }
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


    public double feedMoney(double amountToFeed) {
        logTransaction("FEED MONEY", amountToFeed);
        if (amountToFeed <= 0) {
            System.out.println("You must feed more than $0.00");
        } else if (currentMoney + amountToFeed > 50) {
            System.out.println("You cannot add more than $50");
        }else if(amountToFeed != (int)amountToFeed ){
            System.out.println("Please enter full dollar");}
        else if (currentMoney + amountToFeed <= 50) {
            currentMoney += amountToFeed;
            return currentMoney;
        }return currentMoney;
    }

    public String selectProduct () {
        System.out.println("Item List");
        if(productList.size() > 0) {
            for (StuffedAnimal product : productList) {
                System.out.println(product.toString());
            }
            String slot = userInput.nextLine();
            return dispenseProduct(slot);
        } else {
            return "No products to dispense";
        }
    }


    public int[] finishTransaction () {
        logTransaction("GIVE CHANGE", currentMoney);
        final double QUARTER = 0.25;
        final double DIME = 0.1;
        final double NICKEL = 0.05;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;


        while (currentMoney != 0.00) {
            if (currentMoney >= QUARTER) {
                quarterCount++;
                currentMoney -= QUARTER;
            } else if (currentMoney >= DIME) {
                dimeCount++;
                currentMoney -= DIME;
            } else if (currentMoney >= NICKEL) {
                nickelCount++;
                currentMoney -= NICKEL;
            } else {
                currentMoney = 0.00;
            }
            currentMoney = Math.round(currentMoney * 100.0)/100.0;
        }
        System.out.printf("Your change is: %s quarters, %s dimes, and %s nickels\n", quarterCount, dimeCount, nickelCount);
        int[] returnArr = new int[]{quarterCount, dimeCount, nickelCount};
        return returnArr;
    }


    private void logTransaction (String action,double moneyToChange){
        LocalDate date = LocalDate.now();
        DateFormat formatDate = new SimpleDateFormat("hh:mm:ss aa");
        String dateString = formatDate.format(new Date());


        if (!log.exists()) {
            System.out.println("File not found");
            System.exit(1);
        } else if (!log.isFile()) {
            System.out.println("Is not a file");
            System.exit(1);
        }


        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            if (action.equals("FEED MONEY")) {
                writer.println(date + " " + dateString + " " + action + ": " + "$" + moneyToChange + " $" + (currentMoney + moneyToChange));
            } else if (action.equals("GIVE CHANGE")) {
                writer.println(date + " " + dateString + " " + action + ": " + "$" + moneyToChange + " $" + (currentMoney - moneyToChange));
            } else {
                writer.println(date + " " + dateString + " " + action + ": " + "$" + moneyToChange + " $" + currentMoney);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String dispenseProduct(String slot){
        for (StuffedAnimal product : productList) {
            if (product.getSlot().equalsIgnoreCase(slot)) {
                if (slot.equalsIgnoreCase(product.getSlot()) && !product.isSoldOut() && currentMoney >= product.getPrice()) {
                    currentMoney -= product.getPrice();
                    currentMoney = Math.round(currentMoney * 100.0) / 100.0;
                    product.sellOne();
                    logTransaction(product.getName() + " " + product.getSlot(), product.getPrice());
                    return product.getName() + ", " + product.getPrice() + ", " + currentMoney + "\n" + product.getMessage();
                } else if (currentMoney < product.getPrice()) {
                    return "Not enough funds. Please insert money to continue";
                } else {
                    return product.getSlot() + " | " + product.getName() + " | " + product.getPrice() + " | SOLD OUT";
                }
            }
        }

        return "Please enter a valid slot number";

    }
}
