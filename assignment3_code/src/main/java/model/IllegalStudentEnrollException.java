package model;

/**
 * Exception when a Student can not be added to a Course.
 */
public class IllegalStudentEnrollException extends RuntimeException {
	 public IllegalStudentEnrollException(String errorMessage) {
	        super(errorMessage);}
}
