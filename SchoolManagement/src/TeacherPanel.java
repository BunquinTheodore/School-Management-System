import java.util.List;
import java.util.Scanner;

public class TeacherPanel {
    private static final Scanner scanner = new Scanner(System.in);
    private final int teacherId;
    private final GradesDAO gradesDAO = new GradesDAO(); // Grades management
    private final TeacherDAO teacherDAO = new TeacherDAO(); // To view teacher salary
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO(); // To manage course enrollments
    private final StudentDAO studentDAO = new StudentDAO(); // To fetch student details
    private final CourseDAO courseDAO = new CourseDAO(); // To fetch course details

    public TeacherPanel(int teacherId) {
        this.teacherId = teacherId;
    }

    public void showPanel() {
        try{
            boolean running = true;
            while (running) {
                System.out.println("\n--- Teacher Panel ---");
                System.out.println("1. Assign Grades");
                System.out.println("2. View Salary");
                System.out.println("3. View Students in My Courses");
                System.out.println("4. Choose a Course to Teach");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> assignGrades();
                    case 2 -> viewSalary();
                    case 3 -> viewStudentsInCourses();
                    case 4 -> chooseCourseToTeach();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input.");
            showPanel();
        }


    }

    private void assignGrades() {
        System.out.println("\n--- Assign Grades ---");
        System.out.println("Fetching courses you teach...");

        // Retrieve all subjects assigned to the teacher
        List<Course> courses = getCoursesTaughtByTeacher();
        if (courses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }

        // Show courses taught by the teacher
        for (Course course : courses) {
            System.out.println("Course ID: " + course.getId() + ", Course Name: " + course.getName());
        }

        System.out.print("Enter the Course ID to assign grades: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        // Check if the teacher is authorized to assign grades to this course
        if (!isCourseTaughtByTeacher(courseId)) {
            System.out.println("You are not authorized to assign grades for this course.");
            return;
        }

        // Show students enrolled in the selected course
        List<Enrollment> enrollments = enrollmentDAO.getAll();
        System.out.println("\n--- Students Enrolled in Course ---");
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId() == courseId) {
                Student student = studentDAO.get(enrollment.getStudentId());
                System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName());
            }
        }

        System.out.print("Enter Student ID to assign a grade: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the grade (e.g., A, B+, C): ");
        String grade = scanner.nextLine();

        // Assign the grade
        Grades newGrade = new Grades(0, studentId, courseId, teacherId, grade);
        if (gradesDAO.addOrUpdateGrade(newGrade)) {
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Failed to assign grade.");
        }
    }

    private void viewSalary() {
        System.out.println("\n--- View Salary ---");

        // Retrieve the teacher's salary
        Teacher teacher = teacherDAO.get(teacherId);
        if (teacher != null) {
            System.out.println("Your Salary: $" + teacher.getSalary());
        } else {
            System.out.println("Unable to retrieve salary information.");
        }
    }

    private void viewStudentsInCourses() {
        System.out.println("\n--- View Students in My Courses ---");

        // Retrieve all courses taught by the teacher
        List<Course> courses = getCoursesTaughtByTeacher();
        if (courses.isEmpty()) {
            System.out.println("You are not teaching any courses.");
            return;
        }

        // Show students for each course
        for (Course course : courses) {
            System.out.println("\nSubject: " + course.getName() + " (ID: " + course.getId() + ")");
            List<Enrollment> enrollments = enrollmentDAO.getAll();
            boolean hasStudents = false;

            for (Enrollment enrollment : enrollments) {
                if (enrollment.getCourseId() == course.getId()) {
                    hasStudents = true;
                    Student student = studentDAO.get(enrollment.getStudentId());
                    System.out.println("  Student ID: " + student.getId() + ", Name: " + student.getName());
                }
            }

            if (!hasStudents) {
                System.out.println("No students enrolled in this course.");
            }
        }
    }

    private void chooseCourseToTeach() {
        System.out.println("\n--- Choose a Course to Teach ---");

        // Fetch all available courses
        List<Course> allCourses = courseDAO.getAll();
        if (allCourses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        // Display courses that the teacher can choose from
        System.out.println("Available Courses:");
        for (Course course : allCourses) {
            System.out.println("Course ID: " + course.getId() + ", Course Name: " + course.getName());
        }

        System.out.print("Enter the Course ID you want to teach: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        // Assign the teacher to the selected course
        if (teacherDAO.assignCourseToTeacher(teacherId, courseId)) {
            System.out.println("You are now assigned to teach the course: " + courseDAO.get(courseId).getName());
        } else {
            System.out.println("Failed to assign course.");
        }
    }

    // Helper method to get courses taught by the teacher
    private List<Course> getCoursesTaughtByTeacher() {
        return courseDAO.getCoursesByTeacher(teacherId);
    }

    // Helper method to check if a course is taught by the teacher
    private boolean isCourseTaughtByTeacher(int courseId) {
        List<Course> teacherCourses = getCoursesTaughtByTeacher();
        for (Course course : teacherCourses) {
            if (course.getId() == courseId) {
                return true;
            }
        }
        return false;
    }
}
