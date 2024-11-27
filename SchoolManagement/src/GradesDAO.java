import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesDAO {

    public List<Grades> getGradesByStudentId(int studentId) {
        List<Grades> grades = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Grades WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                grades.add(new Grades(
                        rs.getInt("grade_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getInt("teacher_id"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    // Method to add grades by teacher
    public boolean addGrade(Grades grade) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Grades (student_id, course_id, teacher_id, grade) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, grade.getStudentId());
            stmt.setInt(2, grade.getCourseId());
            stmt.setInt(3, grade.getTeacherId());
            stmt.setString(4, grade.getGrade());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addOrUpdateGrade(Grades grade) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if the grade already exists for this student and course
            String checkQuery = "SELECT * FROM Grades WHERE student_id = ? AND course_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setInt(1, grade.getStudentId());
            checkStmt.setInt(2, grade.getCourseId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Update existing grade
                String updateQuery = "UPDATE Grades SET grade = ? WHERE student_id = ? AND course_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, grade.getGrade());
                updateStmt.setInt(2, grade.getStudentId());
                updateStmt.setInt(3, grade.getCourseId());
                return updateStmt.executeUpdate() > 0;
            } else {
                // Add new grade
                String insertQuery = "INSERT INTO Grades (student_id, course_id, teacher_id, grade) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setInt(1, grade.getStudentId());
                insertStmt.setInt(2, grade.getCourseId());
                insertStmt.setInt(3, grade.getTeacherId());
                insertStmt.setString(4, grade.getGrade());
                return insertStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
