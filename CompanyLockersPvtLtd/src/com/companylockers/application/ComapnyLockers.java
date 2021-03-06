package com.companylockers.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.companylockers.exceptions.FileCreationException;
import com.companylockers.exceptions.NoFileListException;
import com.companylockers.util.FileHandlingUtil;

/**
 * 
 * @author Anupkumar Seth
 * @version 1.0
 * @since 2021-08-08
 *
 */
public class ComapnyLockers {

	public static void main(String[] args) {

		final Scanner sc = new Scanner(System.in);
		String option = "";
		boolean closeApplication = false;
		final String path = "./Files";

		try {

			// Display welcome header with company name and Developer name
			getWelcomeHeader();

			do {

				try {
					// Display the main menu for the app
					displayMainMenuOptions();

					if (sc.hasNext()) {
						option = sc.nextLine().trim();
					}

					// Perform operation based on inputs received.
					switch (Integer.parseInt(option)) {
					case 1:
						getFileNames(path);
						break;

					case 2:
						performCoreOperations(sc, path);
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
				if (closeApplication)
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
	private static void displayMainMenuOptions() {
		System.out.println("----------------MAIN MENU--------------------");
		System.out.println("1. File names in ascending order");
		System.out.println("2. Business-level operations");
		System.out.println("3. Exit Appliaction");
		System.out.println("Please entetr input (1,2, or 3): ");
	}

	/**
	 * Perform the core operations of the application 1. Add file to application 2.
	 * Delete file from application 3. Search file in application 4. Return to
	 * previous menu options
	 * 
	 * @param path
	 * @param Scanner
	 */
	private static void performCoreOperations(Scanner sc, String path) {

		boolean goBackToPreviousMenu = false;
		String option = "";
		boolean avoidMenuPrint = false;

		do {

			try {

				// This condition avoids re-printing of menu incase scanner is taking buffer
				// inputs.
				if (!avoidMenuPrint)

					// Display the sub menu for core operations.
					displaySubMenuOptions();

				if (sc.hasNext()) {

					option = sc.nextLine().trim();

					if (option.isEmpty()) {
						avoidMenuPrint = true;
						continue;
					} else {
						avoidMenuPrint = false;
					}
				}

				// Perform core operations.
				switch (Integer.parseInt(option)) {

				case 1:
					addFile(sc, path);
					break;
				case 2:
					deleteFile(sc, path);
					break;
				case 3:
					searchFile(sc, path);
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
			} catch (Exception ex) {
				System.out.println("Some error occured while trying to Create file. Refer to error message below...");
				System.out.println(ex.getMessage());
			}

		} while (true);

	}

	/**
	 * Searches for the file at given path
	 * @param sc 
	 * @param path 
	 */
	private static void searchFile(Scanner sc, String path) {
		
		System.out.println("Pleae enter the file name to be deleted: ");
		String fileName = sc.nextLine();
		if(FileHandlingUtil.searchFile(path, fileName)) {
			System.out.println(fileName + " File was found ");
		}else {
			System.out.println(fileName + " File was not found ");
		}
	}

	/**
	 * Deletes the file from the given path
	 * @param sc 
	 * @param path string value indicating path of the file
	 */
	private static void deleteFile(Scanner sc, String path) {
		
		try {
		
			System.out.println("Pleae enter the file name to be deleted: ");
			String fileName = sc.nextLine();
			FileHandlingUtil.deleteFile(path, fileName);
			
		}catch(Exception ex) {
			System.out.println("Some error occured while trying to Create file. Refer to error message below...");
			System.out.println(ex.getMessage());
		}
		
		

	}

	/**
	 * Utility method to add file to the given path
	 * 
	 * @param sc
	 * @param path
	 */
	private static void addFile(Scanner sc, String path) {

		List<String> contentList = new ArrayList<String>();
		try {

			//Read file name
			System.out.println("Please enter name of the file to be created:  ");
			String fileName = sc.nextLine();

			
			System.out.println(" Pleae enter content of the file:  -----> USE :WQ TO INDICATE EOF <------");

			// Reading contents of the file unit EOF is entered by user.
			while (sc.hasNext()) {

				String str = sc.nextLine();

				if (str.equalsIgnoreCase(":wq")) {
					break;
				}

				contentList.add(str);
			}

			//calling utility method to add the fiven file and content.
			if (FileHandlingUtil.addFile(path, fileName, contentList)) {
				System.out.println("File Created successfully");
			}else {
				System.out.println("File Creation was not successfully!");
			}

		} catch (FileCreationException ex) {
			System.out.println("Some error occured while trying to Create file. Refer to error message below...");
			System.out.println(ex.getMessage());
		}

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

	/**
	 * Gets all the File names from the root directory
	 * 
	 * @param path root folder location
	 */
	private static void getFileNames(String path) {

		try {
			List<String> sortedFileName = FileHandlingUtil.getSortedFileName(path);

			if (sortedFileName == null) {
				System.out.println("Path doesnot exits!");
			} else if (sortedFileName.isEmpty()) {
				System.out.println("Root folder is empty!");
			} else {
				System.out.println("'''''' Files List ''''''!");
				sortedFileName.forEach(System.out::println);
			}

		} catch (NoFileListException ex) {
			System.out.println("Some error occured while trying to get files. Refer to error message below...");
			System.out.println(ex.getMessage());
		}
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
