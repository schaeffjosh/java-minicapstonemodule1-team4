package com.techelevator.Tests;

import com.techelevator.Purchase;
import com.techelevator.StuffedAnimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HappyPathtest {
    private List<StuffedAnimal> stuffedAnimalList;
    @Before
    public void createList() {

        stuffedAnimalList = new ArrayList<>();
        StuffedAnimal penguin = new StuffedAnimal("B1", "Emperor Penguin", 2.80, "Penguin");
        StuffedAnimal cat = new StuffedAnimal("C1", "Black Cat", 2.25, "Cat");
        stuffedAnimalList.add(penguin);
        stuffedAnimalList.add(cat);
    }
   // @Test
    // public void clearOutItems(){


     //   }





   // }
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


}
