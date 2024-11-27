public class Subject {
    private int id;
    private String name;
    private int deptId;
    private int teacherId;

    public Subject(int id, String name, int deptId, int teacherId) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.teacherId = teacherId;
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

    public int getTeacherId() {
        return teacherId;
    }
}
