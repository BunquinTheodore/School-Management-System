import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements CRUDOperations<Subject> {
    @Override
    public boolean add(Subject subject) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Subject (subject_name, dept_id, teacher_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getDeptId());
            stmt.setInt(3, subject.getTeacherId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Subject get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Subject WHERE subject_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_name"),
                        rs.getInt("dept_id"),
                        rs.getInt("teacher_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Subject";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_name"),
                        rs.getInt("dept_id"),
                        rs.getInt("teacher_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public boolean update(Subject subject) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Subject SET subject_name = ?, dept_id = ?, teacher_id = ? WHERE subject_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getDeptId());
            stmt.setInt(3, subject.getTeacherId());
            stmt.setInt(4, subject.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Subject WHERE subject_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
