public class Grades {
    private int gradeId;
    private int studentId;
    private int courseId;
    private int teacherId;
    private String grade;

    public Grades(int gradeId, int studentId, int courseId, int teacherId, String grade) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.grade = grade;
    }

    // Getters and Setters
    public int getGradeId() {
        return gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
