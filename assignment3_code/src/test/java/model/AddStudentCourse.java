package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 *  Implement and test {Course.addStudentCourse } that respects the following:
 *
 *  <ul>
 *      <li> Get the number of courses a student is enrolled in </li>
 *      <li> A student cannot be enrolled in more than 4 courses</li>
 *      <li>Throws an exception if a student is already enrolled in a course</li>
 *      <li>Get the enrollments of the course</li>
 *      <li>A course cannot have more than 100 students</li>
 *      <li>If the above constraints are met then enroll a student in a course i.e. add a new student to the course and return true. If not  then return false</li>
 *  </ul>
 *
 * NOTE: You are ecpected to verify that the constraints to add a new student to a course are met.
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with setUp method.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class AddStudentCourse {
    Student student;
    Course course;

    /*
    Because of the use of nested classes to organise tests, @BeforeAll is
    used in both the outer (AddStudentCourse) and inner classes rather than
    @BeforeEach, which gets called every time a test in the inner class gets
    executed.
     */
	@BeforeAll
    public void setUp() {
        course = new Course("SEF");
        student = new Student("Myeonghoon Sun", 3774430);
    }

    @DisplayName("Grouped tests checking the constraint placed on the number" +
            " of courses a student can enrol in")
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class NumOfCoursesEnrolled {
        /*
         @TestInstance(Lifecycle.PER_CLASS allows the @BeforeAll annotation
         to be used within a nested class. The inner @BeforeAll ensures that
         course and student variables are reset to the same condition created
          when setUp() was called in the outer class.
         */
        @BeforeAll
        public void nestedSetUp() {
            course.reset();
            student.reset();
        }

        // a method to feed parameters to the test below
        private static Stream<Arguments> courses() {
            return Stream.of(
                    arguments(new Course("APT")),
                    arguments(new Course("AA")),
                    arguments(new Course("PDS")),
                    arguments(new Course("ML"))
            );
        }

        @Order(1)
        @ParameterizedTest()
        @MethodSource("courses")
        @DisplayName("Returns true as the student adds upto the maximum " +
                "number of courses from none")
        // Have the student enrol in 4 courses
        void addStudentCourse_True_IfStudentCanEnrol(Course currCourse) {
            assertTrue(currCourse.addStudentCourse(student));
        }

        @Order(2)
        @Test
        @DisplayName("Returns false if a student being added has already " +
                "enrolled in the maximum number of courses")
        void addStudentCourse_False_IfStudentEnrolledInMaxNumOfCourses() {
            /*
             * A student who has already enrolled in the maximum number of courses
             * tries to enrol in another course
             */
            assertFalse(course.addStudentCourse(student));
        }
    }

    @DisplayName("Grouped tests checking the constraint placed on the student" +
            " unable to enrol in the same course")
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class EnrolledInTheSameCourse {
        @BeforeAll
        public void nestedSetUp() {
            course.reset();
            student.reset();
        }

        @Order(1)
        @Test
        @DisplayName("Returns true as the student adds a course for the " +
                "first time")
        // Add a student to a course for the first time
        void addStudentCourse_True_IfStudentEnrolsForTheFirstTime() {
            assertTrue(course.addStudentCourse(student));
        }

        @Order(2)
        @Test
        @DisplayName("Throws IllegalArgumentException if a student being added is" +
                " already enrolled in the same course")
        // Add the student to the same course
        void addStudentCourse_ThrowsException_IfStudentAlreadyEnrolled() {

            Throwable exception = assertThrows(IllegalStudentEnrollException.class,
                    ()->course.addStudentCourse(student));
            assertEquals("The student has already " +
                    "enrolled in the course", exception.getMessage());
        }
    }

    @DisplayName("Grouped tests checking the constraint placed on the number " +
            "of students a course can admit")
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class EnrolledInTheFullCourse {
        @BeforeAll
        public void nestedSetUp() {
            course.reset();
            student.reset();
        }

        @Order(1)
        @ParameterizedTest
        // Read a csv file to generate mock-up data of students to pass as
        // arguments
        @CsvFileSource(resources = "/studentinfo")
        @DisplayName("Returns true as the course adds students upto the " +
                "maximum capacity")
        // Add 100 students to a course
        void addStudentCourse_True_IfCourseNotFull(String studentNumber,
                                                   String name) {
            assertTrue(course.addStudentCourse(new Student(name,
                    Integer.parseInt(studentNumber))));
        }

        @Order(2)
        @Test
        @DisplayName("Returns false when the full course adds a student")
        // Add a student to the full course
        void addStudentCourse_False_IfCourseFull() {
            assertFalse(course.addStudentCourse(student));
        }
    }
}