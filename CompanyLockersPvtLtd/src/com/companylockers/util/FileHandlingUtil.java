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

		FileWriter myWriter = null;

		try {

			File myObj = new File(path + "/" + fileName);

			// Check if file already exist. if not then create new file.
			if (!myObj.createNewFile()) {
				System.out.println("File already exists.");
			} else {
				myWriter = new FileWriter(myObj);
				
				//Read each line and write it to file
				for (String str : content) {
					myWriter.write(str);
					myWriter.write("\n");
				}
				return true;
			}

		} catch (Exception ex) {
			throw new FileCreationException(ex.getMessage());
		} finally {

			//Close the writer 
			if (myWriter != null) {
				try {
					myWriter.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		return false;
	}

}
