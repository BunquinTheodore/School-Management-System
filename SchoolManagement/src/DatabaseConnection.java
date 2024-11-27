import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/schoolmanagement";
    private static final String USER = "root"; // Use your MySQL username
    private static final String PASSWORD = "#bunquin"; // Use your MySQL password
    private static final DepartmentDAO departmentDAO = new DepartmentDAO();
    private static final CourseDAO courseDAO = new CourseDAO();

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void showAllDepartments() {
        System.out.println("\n--- List of Departments ---");
        List<Department> departments = departmentDAO.getAll();
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
        } else {
            for (Department dept : departments) {
                System.out.println("ID: " + dept.getId() + ", Name: " + dept.getName());
            }
        }
    }

    public static void showAllCourses() {
        System.out.println("\n--- List of Courses ---");
        List<Course> courses = courseDAO.getAll();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : courses) {
                System.out.println("ID: " + course.getId() + ", Name: " + course.getName() + ", Dept ID: " + course.getDeptId());
            }
        }
    }
}
