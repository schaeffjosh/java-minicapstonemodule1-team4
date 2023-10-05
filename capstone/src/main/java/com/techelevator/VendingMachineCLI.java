package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//TODO: Purchase class
//TODO add purchase class to main menu
//TODO: Feed money
//TODO: Select Product
//TODO: Finish Transaction
//TODO: Log Transaction

public class VendingMachineCLI {
	List<StuffedAnimal> productList = new ArrayList<StuffedAnimal>();

	public VendingMachineCLI() {

	}

	public void run() {
		Scanner userInput = new Scanner(System.in);
		File vendingMachine = new File("C:/Users/Student/workspace/java-minicapstonemodule1-team4/capstone/vendingmachine.csv");

		if(!vendingMachine.exists()){
			System.out.println("File not found.");
			System.exit(1);
		}else if(!vendingMachine.isFile()){
			System.out.println("Is not a file.");
			System.exit(1);
		}

		try(Scanner reader = new Scanner(vendingMachine)){
			while(reader.hasNextLine()){
				String[] itemArr = reader.nextLine().split("\\|");
				StuffedAnimal product = new StuffedAnimal(itemArr[0], itemArr[1], Double.parseDouble(itemArr[2]), itemArr[3]);
				productList.add(product);
			}
		} catch(FileNotFoundException ex){
			System.out.println("File not found.");
		}
		mainMenu();
	}

	public void displayProducts(){
		System.out.println("Item List");
		for(StuffedAnimal product : productList){
			System.out.println(product.toString());
		}
	}



	public void mainMenu(){
		Scanner input = new Scanner(System.in); //scanner for user input
		while(true){   //print out options for what specific menu they want
			System.out.println("\nMain Menu");
			// print 1,2,3,4
			System.out.println("(1) Display Item List");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			System.out.println("\nSelect An Option Please");
			String choice = input.nextLine();

			switch (choice){    // switching
				case "1":
					displayProducts();
					break;
				// case "2"
				// Purchase menu
				//break;
				case "2":   //TODO rename to case "3" after done with purchase menu
					System.exit(0);
				//case "4";
				//salesReport
				//break
				default:
			}
		}
	}


	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
