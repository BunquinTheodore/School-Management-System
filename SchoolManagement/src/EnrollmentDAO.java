import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO implements CRUDOperations<Enrollment> {
    @Override
    public boolean add(Enrollment enrollment) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Enrollment (student_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Enrollment get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Enrollment WHERE enrollment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Enrollment> getAll() {
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Enrollment";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enrollment_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        // Enrollment typically doesn't have updates; could be left unimplemented or customized
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Enrollment WHERE enrollment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteByStudentAndCourse(int studentId, int courseId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Enrollment WHERE student_id = ? AND course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
