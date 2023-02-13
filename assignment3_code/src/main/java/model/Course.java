package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project inside the company.
 */
public class Course {
    /**
     * Name the course
     */
    private String name;

    /**
     * Start date of the course
     */
    private Date startDate;

    /**
     * End date of the course
     */
    private Date dueDate;

    /**
     * Estimated duration of the course in months
     */
    private int estimatedDuration;

    public static final int maxNumOfStudents = 100;

    public Course(String name) {
        this.name = name;
    }

    /**
     * Students allocated to the course
     */
    private List<Student> enrolled = new ArrayList<Student>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public List<Student> getEnrollments() {
        return enrolled;
    }

    public void setEnrollments(List<Student> students) {enrolled = students;}

    
    public boolean removeEnrolledStudent(Student student) {
    
    	return false;
    	   
    }


    /**
     * Verifies the course has valid dates.
     * @return true if dates are valid, false otherwise.
     */
    public boolean isValidDates(){
       return false;
    }

    /*
    The method below was necessary to implement so that the objects created
    for the unit-tests are recycled, rather than destroyed and re-created
    with the same arguments inefficiently.
     */
    public void reset() {
        enrolled.clear();
    }

  

    /**
     * Add a new student to the course
     * @param student: to enroll  to student in a course
     * @return true if the student is successfully enrolled, false otherwise
     */

    /*
     Because an exception should be caught by jUnit tests, a try and
     catch block is removed. However, once unit-tests are complete and the
     code is ready to be shipped to the production stage, add the try and
     catch block either inside addStudentCourse or the scope of its caller so
     that the exception is caught in the program.
     */
    public boolean addStudentCourse(Student student) throws IllegalStudentEnrollException {
        // a boolean to check whether a student can be enrolled
        boolean canBeEnrolled = true;
        // a boolean to return after a student is added or not
        boolean isEnrolled = true;
        // Check if a student can enrol in another course
        if (student.getNumberOfCourses() == Student.maxNumOfCourses)
            canBeEnrolled = false;

        // Check if a student is already enrolled in the course
        if (enrolled.stream().anyMatch(s->s.getName().equals(student.getName()))) {
            /*
             When a try and catch block is incorporated after unit-tests are
             removed, make sure to add: canBeEnrolled = false; in its catch
             block.
             */
            throw new IllegalStudentEnrollException("The student has already " +
                    "enrolled in the course");
        }

        // Check if a course is filled to the maximum capacity
        if (enrolled.size() == Course.maxNumOfStudents)
            canBeEnrolled = false;

        // If a student cannot be enrolled
        if (!canBeEnrolled) {
            isEnrolled = false;
        } else {
            enrolled.add(student);
            student.incrementNumberOfCourses();
        }

        return isEnrolled;
    }




}
