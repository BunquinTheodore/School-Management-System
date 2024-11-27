public class Teacher {
    private int id;
    private final String name;
    private final int deptId;
    private final double salary;
    private final int course_id;

    public Teacher(int id, String name, int deptId, double salary, int course_id) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.salary = salary;
        this.course_id = course_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getDeptId() {
        return deptId;
    }

    public double getSalary() {
        return salary;
    }
}
