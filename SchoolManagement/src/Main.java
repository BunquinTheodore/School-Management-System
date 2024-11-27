import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();
    private static final StudentDAO studentDAO = new StudentDAO();
    private static final TeacherDAO teacherDAO = new TeacherDAO();


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try{
            boolean running = true;
            while (running) {
                System.out.println("\n--- School Management System ---");
                System.out.println("1. Register as Student");
                System.out.println("2. Login as Student");
                System.out.println("3. Login as Teacher");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> registerStudent();
                    case 2 -> loginAsStudent();
                    case 3 -> loginAsTeacher(); // Call loginAsTeacher when chosen
                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice!");
                }
            }
            scanner.close();

        } catch (NumberFormatException e) {
            System.out.println("Invalid Input. Try again");
            run();
        }
    }

    private static void registerStudent() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        DatabaseConnection.showAllDepartments();
        System.out.print("Enter Department ID: ");
        int deptId = Integer.parseInt(scanner.nextLine());
        DatabaseConnection.showAllCourses();
        System.out.print("Enter Course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());

        // Register new student
        Student student = new Student(0, name, deptId, courseId);
        if (studentDAO.add(student)) {
            // Get the newly created student's ID
            int studentId = studentDAO.getAll().stream().reduce((first, second) -> second).orElse(null).getId();

            System.out.print("Choose a username: ");
            String username = scanner.nextLine();
            System.out.print("Choose a password: ");
            String password = scanner.nextLine();

            // Register user credentials
            User user = new User(0, username, password, "Student", studentId, null);
            if (userDAO.registerStudent(user)) {
                System.out.println("Registration successful! You can now login.");
            } else {
                System.out.println("Error during registration.");
            }
        } else {
            System.out.println("Error registering student.");
        }
    }

    private static void loginAsStudent() {
        System.out.print("Enter Student Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = userDAO.login(username, password);
        if (user != null && "Student".equals(user.getRole())) {
            StudentPanel studentPanel = new StudentPanel(user.getStudentId());
            studentPanel.showPanel();
        } else {
            System.out.println("Invalid student username or password!");
        }
    }

    private static void loginAsTeacher() {
        System.out.print("Enter Teacher Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        User user = userDAO.login(username, password);
        if (user != null && "Teacher".equals(user.getRole())) {
            TeacherPanel teacherPanel = new TeacherPanel(user.getTeacherId());
            teacherPanel.showPanel();
        } else {
            System.out.println("Invalid teacher username or password!");
        }
    }
}
