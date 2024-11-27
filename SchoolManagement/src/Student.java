public class Student {
    private int id;
    private String name;
    private int deptId;
    private int courseId;

    public Student(int id, String name, int deptId, int courseId) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
