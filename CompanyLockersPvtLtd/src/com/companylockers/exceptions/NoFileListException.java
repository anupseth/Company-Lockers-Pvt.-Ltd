package com.companylockers.exceptions;

/**
 * This custom exception class will be used to give user more application specific exception
 * @author Anupkumar Seth
 * @version 1.0
 * @since 2021-08-08
 *
 */
public class NoFileListException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoFileListException(String message) {
		super(message);
	}

}
