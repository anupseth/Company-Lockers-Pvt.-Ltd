package com.companylockers.application;

import java.util.Scanner;

/**
 * 
 * @author Anupkumar Seth
 * @version 1.0
 * @since 2021-08-08 
 *
 */
public class ComapnyLockers {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String option = "";
		boolean closeApplication = false;
		try {

			//Display welcome header with company name and Developer name
			getWelcomeHeader();

			do {

				try {
					//Display the main menu for the app
					displaySuperMenuOptions();

					if (sc.hasNext()) {
						option = sc.nextLine().trim();
					}

					//Perform operation based on inputs received.
					switch (Integer.parseInt(option)) {
					case 1:
						getFileNames();
						break;

					case 2:
						performCoreOperations(sc);
						break;
					case 3:
						closeApplication = true;
						break;
					default:
						System.out.println("Invalid menu option ");
					}

				} catch (NumberFormatException ex) {
					System.out.println("Error - Please enter Valid input !!! ");
				}
				
				// Exit the loop based on this condition, which will exit the application
				if(closeApplication)
					break;

			} while (true);

		} catch (Exception ex) {

			System.out.println("Something went wrong. Pleae Check the error description and contact the developer");
			System.out.println(ex.getMessage());
			System.out.println("Contact : Anupkumar Seth,  email: dev@abc.com");

		} finally {
			System.out.println("...........................Application closed........................");
			sc.close();
		}

	}

	/**
	 * Displays Main Menu options for application
	 */
	private static void displaySuperMenuOptions() {
		System.out.println("----------------MAIN MENU--------------------");
		System.out.println("1. File names in ascending order");
		System.out.println("2. Business-level operations");
		System.out.println("3. Exit Appliaction");
		System.out.println("Please entetr input (1,2, or 3): ");
	}

	/**
	 * Perform the core operations of the application
	 * 1. Add file to application
	 * 2. Delete file from application
	 * 3. Search file in application
	 * 4. Return to previous menu options
	 * @param Scanner
	 */
	private static void performCoreOperations(Scanner sc) {
		
		boolean goBackToPreviousMenu = false;
		String option = "";
		boolean avoidMenuPrint = false;

		do {

			try {

				// This condition avoids re-printing of menu incase scanner is taking buffer inputs.
				if(!avoidMenuPrint)
					
				// Display the sub menu for core operations.	
				displaySubMenuOptions();

				if (sc.hasNext()) {
					
					option = sc.nextLine().trim();
					
					if(option.isEmpty()) {
						avoidMenuPrint = true;
						continue;
					}else {
						avoidMenuPrint = false;
					}
				}

				
				//Perform core operations.
				switch (Integer.parseInt(option)) {

				case 1:
					addFileToApplication();
					break;
				case 2:
					deleteFileFromApplication();
					break;
				case 3:
					searchFileInApplication();
					break;
				case 4:
					goBackToPreviousMenu = true;
					break;
				default:
					System.out.println("Invalid menu option ");

				}

				// Go back to main menu
				if (goBackToPreviousMenu)
					break;

			} catch (NumberFormatException ex) {
				System.out.println("Error - Please enter Valid input !!! ");
			}

		} while (true);

	}

	
	private static void searchFileInApplication() {
		System.out.println("Searching files ...........");

	}

	private static void deleteFileFromApplication() {
		System.out.println("deleting files ...........");

	}

	private static void addFileToApplication() {
		System.out.println("Adding files ...........");

	}

	/**
	 * Prints core menu options for the application.
	 */
	private static void displaySubMenuOptions() {
		System.out.println("-----------------Business-level operations-------------------");
		System.out.println("1. Add file to application");
		System.out.println("2. Delete file from application");
		System.out.println("3. Search file in application");
		System.out.println("4. Return to previous menu options");
		System.out.println("Please entetr input (1,2, 3 or 4): ");
	}

	private static void getFileNames() {

	}

	/**
	 * Prints welcome header message for the application
	 */
	private static void getWelcomeHeader() {
		System.out.println("***************************************************");
		System.out.println("\tWelcome to Company Lockers Pvt. Ltd");
		System.out.println("\t   Developer: Anupkumar Seth");
		System.out.println("***************************************************");
	}

}
