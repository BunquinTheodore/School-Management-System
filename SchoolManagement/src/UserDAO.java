import java.sql.*;

public class UserDAO {

    public boolean registerStudent(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Users (username, password, role, student_id) VALUES (?, ?, 'Student', ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // In production, use a hashed password
            stmt.setInt(3, user.getStudentId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addTeacherUser(User user) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Users (username, password, role, teacher_id) VALUES (?, ?, 'Teacher', ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // In production, use a hashed password
            stmt.setInt(3, user.getTeacherId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User login(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password); // In production, compare hashed password
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                Integer studentId = (rs.getObject("student_id") != null) ? rs.getInt("student_id") : null;
                Integer teacherId = (rs.getObject("teacher_id") != null) ? rs.getInt("teacher_id") : null;

                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        role,
                        studentId,
                        teacherId
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
