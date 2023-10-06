package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {
	List <StuffedAnimal> productList = new ArrayList<>();
	ImportFile getList = new ImportFile();

	public VendingMachineCLI() {

	}

	public void run() {
		productList = getList.importList();
		mainMenu();
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
					getList.displayProducts();
					break;
				case "2":
					Purchase menu = new Purchase(productList);
					menu.purchaseMenu();
					break;
				case "3":   //TODO rename to case "3" after done with purchase menu
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
