import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements CRUDOperations<Teacher> {
    @Override
    public boolean add(Teacher teacher) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Teacher (teacher_name, dept_id, salary, course_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, teacher.getName());
            stmt.setInt(2, teacher.getDeptId());
            stmt.setDouble(3, teacher.getSalary());
            stmt.setInt(4, teacher.getCourse_id());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Teacher get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Teacher WHERE teacher_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name"), rs.getInt("dept_id"), rs.getDouble("salary"), rs.getInt("course_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Teacher";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teachers.add(new Teacher(rs.getInt("teacher_id"), rs.getString("teacher_name"), rs.getInt("dept_id"), rs.getDouble("salary"), rs.getInt("course_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public boolean update(Teacher teacher) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Teacher SET teacher_name = ?, dept_id = ?, salary = ? WHERE teacher_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, teacher.getName());
            stmt.setInt(2, teacher.getDeptId());
            stmt.setDouble(3, teacher.getSalary());
            stmt.setInt(4, teacher.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Teacher WHERE teacher_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean assignCourseToTeacher(int teacherId, int courseId) {
        String sql = "UPDATE teacher SET course_id = ? WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            stmt.setInt(2, teacherId);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
