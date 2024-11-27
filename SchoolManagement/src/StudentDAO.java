import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements CRUDOperations<Student> {
    @Override
    public boolean add(Student student) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Student (student_name, dept_id, course_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getDeptId());
            stmt.setInt(3, student.getCourseId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Student WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getInt("dept_id"),
                        rs.getInt("course_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Student";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getInt("dept_id"),
                        rs.getInt("course_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean update(Student student) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Student SET student_name = ?, dept_id = ?, course_id = ? WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getDeptId());
            stmt.setInt(3, student.getCourseId());
            stmt.setInt(4, student.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Student WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
