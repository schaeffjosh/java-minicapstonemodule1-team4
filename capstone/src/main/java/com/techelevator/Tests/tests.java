package com.techelevator.Tests;

import com.techelevator.ImportFile;
import com.techelevator.Purchase;
import com.techelevator.StuffedAnimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class tests {
    private List<StuffedAnimal> stuffedAnimalList;
    @Before
    public void createList() {
        stuffedAnimalList = new ArrayList<>();
        StuffedAnimal penguin = new StuffedAnimal("B1", "Emperor Penguin", 2.80, "Penguin");
        StuffedAnimal cat = new StuffedAnimal("C1", "Black Cat", 2.25, "Cat");
        StuffedAnimal pony = new StuffedAnimal("D2", "Pegasus Pony", 1.85, "Pony");
        stuffedAnimalList.add(penguin);
        stuffedAnimalList.add(cat);
        stuffedAnimalList.add(pony);
    }
    @Test
    public void importList(){
        ImportFile getList = new ImportFile();
        stuffedAnimalList = getList.importList();
        Assert.assertEquals(stuffedAnimalList.get(0).toString(), "A1 | Yellow Duck | 0.9 | 5");
    }
    @Test
    public void clearOutItems(){
        Purchase buyOut = new Purchase(stuffedAnimalList);
        buyOut.feedMoney(50.00);
        for(int i = 0; i < 5; i++){
            buyOut.dispenseProduct("B1");
            buyOut.dispenseProduct("C1");
        }
        Assert.assertEquals("B1 | Emperor Penguin | 2.8 | SOLD OUT", buyOut.dispenseProduct("B1"));
        Assert.assertEquals("C1 | Black Cat | 2.25 | SOLD OUT", buyOut.dispenseProduct("C1"));
    }
    @Test
    public void notAValidSlotNumber(){
        Purchase buyOut = new Purchase(stuffedAnimalList);
        buyOut.feedMoney(50.00);

        Assert.assertEquals("Please enter a valid slot number", buyOut.dispenseProduct("X4"));
        Assert.assertEquals("Please enter a valid slot number", buyOut.dispenseProduct("G7"));
    }
    @Test
    public void noProductsInList(){
        List<StuffedAnimal> empty = new ArrayList<StuffedAnimal>();
        Purchase buyOut = new Purchase(empty);
        buyOut.feedMoney(50.00);

        Assert.assertEquals("No products to dispense", buyOut.selectProduct());
    }
    @Test
    public void notEnoughFunds(){
        Purchase buyOut = new Purchase(stuffedAnimalList);

        Assert.assertEquals("Not enough funds. Please insert money to continue", buyOut.dispenseProduct("B1"));
        Assert.assertEquals("Not enough funds. Please insert money to continue", buyOut.dispenseProduct("C1"));
    }
    @Test
    public void stuffedAnimalToString(){
        StuffedAnimal penguin = new StuffedAnimal("B1", "Emperor Penguin", 2.80, "Penguin");
        StuffedAnimal cat = new StuffedAnimal("C1", "Black Cat", 2.25, "Cat");
        Assert.assertEquals("B1 | Emperor Penguin | 2.8 | 5", penguin.toString());
        Assert.assertEquals("C1 | Black Cat | 2.25 | 5", cat.toString());
    }
    @Test
    public void feedMoney(){
      Purchase feed = new Purchase(stuffedAnimalList);
      double feedMoney = 50.00;
      double returnMoney = feed.feedMoney(50.00);
      Assert.assertEquals(feedMoney,returnMoney, 0.01);
    }
    @Test
    public void feedZeroMoney(){
        Purchase feed = new Purchase(stuffedAnimalList);
        Assert.assertEquals(0.00,feed.feedMoney(0.00), 0.01);
    }
    @Test
    public void feedMoreThan50Money(){
        Purchase feed = new Purchase(stuffedAnimalList);
        Assert.assertEquals(0.00,feed.feedMoney(51.00), 0.01);
    }
    @Test
    public void feedNotAnInt(){
        Purchase feed = new Purchase(stuffedAnimalList);
        Assert.assertEquals(0.00,feed.feedMoney(5.99), 0.01);
    }
    @Test
    public void finishTransaction(){
        int[] value = new int[]{12, 1, 1};
        Purchase finish = new Purchase(stuffedAnimalList);
        finish.feedMoney(5);
        finish.dispenseProduct("D2");
        Assert.assertArrayEquals(value, finish.finishTransaction());
    }
}
