package model;


import java.util.HashSet;
import java.util.Set;

/**
 * Class to hold information about a student
 */
public class Student {

    private final String name;
    private final int studentNumber;
    private double marks;
    private String department;
    private int numberOfCourses = 0;

    public static final int maxNumOfCourses = 4;

    /**
     * Create a new student object.
     * @param name: Name of the student
     * @param studentNumber:  Unqiue id of the student.
     */
    public Student(String name, int studentNumber) {
        this.name = name;
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return studentNumber;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public double getMarks() {
        return marks;
    }

    public String getDepartment() {
        return department;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    /*
     The method below was necessary to implement to increment the number of
     courses a student is enrolled in. The only way numberOfCourses can be
     incremented via the method below is through Course.addStudentCourse(),
     which has proper checks to ensure numberOfCourses never goes beyond
     the maximum number of courses.
     */
    public void incrementNumberOfCourses() {
        numberOfCourses += 1;
    }

    /*
    The method below was necessary to implement so that the objects created
    for the unit-tests are recycled, rather than destroyed and re-created
    with the same arguments inefficiently.
     */
    public void reset() {
        numberOfCourses = 0;
    }



    

}
