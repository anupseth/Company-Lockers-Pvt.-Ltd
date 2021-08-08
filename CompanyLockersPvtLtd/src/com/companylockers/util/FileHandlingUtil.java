package com.companylockers.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	 * @param String
	 * @return List<String>
	 * @throws NoFileListException
	 */
	public static List<String> getSortedFileName(String path){
		
		List<String> fileNameList = null;
		try {
		
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();

			//Check if the path is valid and is not null.
			if(listOfFiles != null) {
				
				fileNameList = new ArrayList<String>();
				
				//Iterate over files array and get files names.
				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	fileNameList.add(file.getName());
				    }
				}
				
				// Sort the files list in acending order.
				Collections.sort(fileNameList);
			}
		
			
		}catch(Exception ex) {
			//Catch any exception and throw custom exception
			throw new NoFileListException(ex.getMessage());
		}
		
		return fileNameList;
	}

}
