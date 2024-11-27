import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements CRUDOperations<Course> {

    @Override
    public boolean add(Course course) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Course (course_name, dept_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getDeptId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Course get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Course WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("dept_id"),
                        rs.getInt("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Course";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("dept_id"),
                        rs.getInt("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public boolean update(Course course) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Course SET course_name = ?, dept_id = ? WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getDeptId());
            stmt.setInt(3, course.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Course WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Course> getCoursesByTeacher(int teacherId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE course_id IN (SELECT course_id FROM teacher WHERE teacher_id = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(rs.getInt("course_id"), rs.getString("course_name"), rs.getInt("dept_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}
