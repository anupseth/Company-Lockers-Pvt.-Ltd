package com.companylockers.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.companylockers.exceptions.FileCreationException;
import com.companylockers.exceptions.NoFileListException;

/**
 * 
 * @author Anupkumar Seth
 * @version 1.0
 * @since 2021-08-08
 *
 */
public class FileHandlingUtil {

	/**
	 * Gets all the files names in sorted order from given path.
	 * 
	 * @param String
	 * @return List<String>
	 * @throws NoFileListException
	 */
	public static List<String> getSortedFileName(String path) throws NoFileListException {

		List<String> fileNameList = null;
		try {

			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();

			// Check if the path is valid and is not null.
			if (listOfFiles != null) {

				fileNameList = new ArrayList<String>();

				// Iterate over files array and get files names.
				for (File file : listOfFiles) {
					if (file.isFile()) {
						fileNameList.add(file.getName());
					}
				}

				// Sort the files list in acending order.
				Collections.sort(fileNameList);
			}

		} catch (Exception ex) {
			// Catch any exception and throw custom exception
			throw new NoFileListException(ex.getMessage());
		}

		return fileNameList;
	}

	
	/**
	 * 
	 * @param path path of the root directory
	 * @param fileName name of the file to be created
	 * @param content List of string containing the content to be written to file. 
	 * @return boolean Value indicating whether file is created or not.
	 */
	public static boolean addFile(String path, String fileName, List<String> content) throws FileCreationException {

		FileWriter fileWritter = null;

		try {

			File myFile = new File(path + "/" + fileName);

			// Check if file already exist. if not then create new file.
			if (!myFile.createNewFile()) {
				System.out.println("File already exists.");
			} else {
				fileWritter = new FileWriter(myFile);
				
				//Read each line and write it to file
				for (String str : content) {
					fileWritter.write(str);
					fileWritter.write("\n");
				}
				return true;
			}

		} catch (Exception ex) {
			throw new FileCreationException(ex.getMessage());
		} finally {

			//Close the writer 
			if (fileWritter != null) {
				try {
					fileWritter.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		return false;
	}

	
	/**
	 * Deletes the file with given name and at specified path 
	 * @param path string value indicating path of the file
	 * @param fileName type string value indicating filename
	 */
	public static void deleteFile(String path, String fileName) {
		
		
		try {
			
			File myFile = new File(path + "/" + fileName);

			//Check if file exist at path
			if (myFile.exists()) {

				//Delete the file
				if (myFile.delete()) {
					System.out.println(fileName + "  file Deleted Successfully");
				} else {
					System.out.println("Could not Deleted file  " + fileName);
				}
			} else {
				System.out.println("FILE NOT FOUND");
			}

		}catch(Exception ex) {
			
			System.out.println("Some exception occured. See below error message...");
			System.out.println(ex.getMessage());
		}
	}
	
	
	/**
	 * Searches for the files in given path parameter
	 * @param path path string value indicating path of the file
	 * @param fileName type string value indicating filename
	 * @return
	 */
	public static boolean searchFile(String path, String fileName) {
		
		try {
			
			File myFile = new File(path + "/" + fileName);

			//Check if file exist at path
			if (myFile.exists()) {
				return true;
			}
			
			
		}catch(Exception ex) {
			
			System.out.println("Some exception occured. See below error message...");
			System.out.println(ex.getMessage());
		}
		
		return false;
	}
}
