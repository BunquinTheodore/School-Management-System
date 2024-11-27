public class User {
    private int userId;
    private String username;
    private String password;
    private String role; // 'Student' or 'Teacher'
    private Integer studentId; // Nullable, only applicable for students
    private Integer teacherId; // Nullable, only applicable for teachers

    public User(int userId, String username, String password, String role, Integer studentId, Integer teacherId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
