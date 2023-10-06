package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class VendingMachineCLI {
	List <StuffedAnimal> productList = new ArrayList<>();
	ImportFile getList = new ImportFile();

	public VendingMachineCLI() {

	}

	public void run()  {
		productList = getList.importList();
		mainMenu();
	}

	public void mainMenu()  {
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
				case "4":
					generateSalesReport();
				break;
			}
		}
	}

	private void generateSalesReport()  {
		LocalDate date = LocalDate.now();
		DateFormat formatDate = new SimpleDateFormat("hh-mm-ss");
		String dateString = formatDate.format(new Date());
		String name = date + "_" + dateString + "_Report.txt";
		File directory = new File("C://Users//Student//workspace//java-minicapstonemodule1-team4//capstone//src//test");
		File report = new File(directory, name);

		try(PrintWriter writer = new PrintWriter(report)) {
			report.createNewFile();
			double totalSales = 0.00;
			for (StuffedAnimal product : productList){
				totalSales += product.getPrice()*abs(product.getAmount() - 5);
				writer.println(product.getName() + " | " + abs(product.getAmount() - 5));

			}
			writer.println("TOTAL SALES: $" + totalSales);
		}
		catch(IOException ex){
			System.out.println("No directory found");
		}

	}

	public static void main(String[] args)  {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}
}
