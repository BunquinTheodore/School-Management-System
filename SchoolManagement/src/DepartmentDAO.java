import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements CRUDOperations<Department> {

    @Override
    public boolean add(Department department) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Department (dept_name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, department.getName());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Department get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Department WHERE dept_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Department(
                        rs.getInt("dept_id"),
                        rs.getString("dept_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Department";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                departments.add(new Department(
                        rs.getInt("dept_id"),
                        rs.getString("dept_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public boolean update(Department department) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Department SET dept_name = ? WHERE dept_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, department.getName());
            stmt.setInt(2, department.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Department WHERE dept_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
