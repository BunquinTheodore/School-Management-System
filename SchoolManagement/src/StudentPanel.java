import java.util.List;
import java.util.Scanner;

public class StudentPanel {
    private static final Scanner scanner = new Scanner(System.in);
    private final int studentId;
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private final CourseDAO courseDAO = new CourseDAO(); // Use CourseDAO to fetch available courses
    private final PaymentDAO paymentDAO = new PaymentDAO();
    private final GradesDAO gradesDAO = new GradesDAO(); // GradesDAO to fetch grades

    public StudentPanel(int studentId) {
        this.studentId = studentId;
    }

    public void showPanel() {
        try {
            boolean running = true;
            while (running) {
                System.out.println("\n--- Student Panel ---");
                System.out.println("1. View Enrolled Courses");
                System.out.println("2. Pay Tuition");
                System.out.println("3. Enroll in a New Course");
                System.out.println("4. Drop a Course"); // New option to drop a course
                System.out.println("5. View Grades (requires full payment)");
                System.out.println("0. Logout");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> viewEnrolledCourses();
                    case 2 -> payTuition();
                    case 3 -> enrollInNewCourse();
                    case 4 -> dropCourse(); // Call dropCourse function
                    case 5 -> viewGrades(); // Call viewGrades function
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input.");
            showPanel();
        }

    }

    private void viewEnrolledCourses() {
        List<Enrollment> enrollments = enrollmentDAO.getAll();
        System.out.println("\n--- Enrolled Courses ---");
        boolean hasEnrolledCourses = false;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                hasEnrolledCourses = true;
                System.out.println("Course ID: " + enrollment.getCourseId() +
                        ", Enrollment Date: " + enrollment.getEnrollmentDate());
            }
        }

        if (!hasEnrolledCourses) {
            System.out.println("No enrolled courses.");
        }
    }

    private void payTuition() {
        System.out.println("\n--- Tuition Payment ---");

        // Calculate total tuition cost based on enrolled courses
        double totalCost = calculateTotalTuitionCost();
        System.out.println("Total Tuition Cost: $" + totalCost);

        System.out.print("Enter Payment Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Payment payment = new Payment(0, studentId, amount, null, "Completed");
        if (paymentDAO.add(payment)) {
            System.out.println("Payment successful.");
        } else {
            System.out.println("Payment failed.");
        }
    }

    private void enrollInNewCourse() {
        System.out.println("\n--- Available Courses ---");
        List<Course> courses = courseDAO.getAll();

        if (courses.isEmpty()) {
            System.out.println("No courses available to enroll.");
            return;
        }

        // Display available courses
        for (Course course : courses) {
            System.out.println("ID: " + course.getId() + ", Name: " + course.getName() + ", Dept ID: " + course.getDeptId() + ", Price: $" + course.getPrice());
        }

        System.out.print("Enter the Course ID to enroll: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        // Check if the student is already enrolled in the selected course
        if (isAlreadyEnrolled(courseId)) {
            System.out.println("You are already enrolled in this course.");
            return;
        }

        // Enroll the student in the selected course
        Enrollment enrollment = new Enrollment(0, studentId, courseId, null);
        if (enrollmentDAO.add(enrollment)) {
            System.out.println("Successfully enrolled in the course.");
        } else {
            System.out.println("Enrollment failed.");
        }
    }

    private void dropCourse() {
        System.out.println("\n--- Drop a Course ---");
        viewEnrolledCourses();

        System.out.print("Enter the Course ID to drop: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        // Check if the student is enrolled in the selected course
        if (!isAlreadyEnrolled(courseId)) {
            System.out.println("You are not enrolled in this course.");
            return;
        }

        // Drop the course
        if (enrollmentDAO.deleteByStudentAndCourse(studentId, courseId)) {
            System.out.println("Successfully dropped the course.");
        } else {
            System.out.println("Failed to drop the course.");
        }
    }

    private void viewGrades() {
        if (!isTuitionFullyPaid()) {
            System.out.println("You need to fully pay the tuition to view your grades.");
            return;
        }

        List<Grades> grades = gradesDAO.getGradesByStudentId(studentId);
        System.out.println("\n--- Your Grades ---");
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            for (Grades grade : grades) {
                System.out.println("Course ID: " + grade.getCourseId() + ", Grade: " + grade.getGrade());
            }
        }
    }

    // Helper methods
    private boolean isAlreadyEnrolled(int courseId) {
        List<Enrollment> enrollments = enrollmentDAO.getAll();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId && enrollment.getCourseId() == courseId) {
                return true;
            }
        }
        return false;
    }

    private double calculateTotalTuitionCost() {
        List<Enrollment> enrollments = enrollmentDAO.getAll();
        double totalCost = 0.0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                Course course = courseDAO.get(enrollment.getCourseId());
                if (course != null) {
                    totalCost += course.getPrice();
                }
            }
        }
        return totalCost;
    }

    private boolean isTuitionFullyPaid() {
        double totalCost = calculateTotalTuitionCost();
        double totalPaid = paymentDAO.getTotalPaidByStudentId(studentId);
        return totalPaid >= totalCost;
    }
}
