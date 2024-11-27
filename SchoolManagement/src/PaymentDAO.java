import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements CRUDOperations<Payment> {
    @Override
    public boolean add(Payment payment) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Payment (student_id, amount_paid, payment_status) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, payment.getStudentId());
            stmt.setDouble(2, payment.getAmountPaid());
            stmt.setString(3, payment.getPaymentStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getTotalPaidByStudentId(int studentId) {
        double totalPaid = 0.0;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT SUM(amount_paid) AS total_paid FROM Payment WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalPaid = rs.getDouble("total_paid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPaid;
    }

    @Override
    public Payment get(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Payment WHERE payment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("student_id"),
                        rs.getDouble("amount_paid"),
                        rs.getDate("payment_date"),
                        rs.getString("payment_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Payment";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                payments.add(new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("student_id"),
                        rs.getDouble("amount_paid"),
                        rs.getDate("payment_date"),
                        rs.getString("payment_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public boolean update(Payment payment) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE Payment SET amount_paid = ?, payment_status = ? WHERE payment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, payment.getAmountPaid());
            stmt.setString(2, payment.getPaymentStatus());
            stmt.setInt(3, payment.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Payment WHERE payment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
