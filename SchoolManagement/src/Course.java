public class Course {
    private int id;
    private String name;
    private int deptId;
    private double price; // New field for price

    public Course(int id, String name, int deptId, double price) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.price = price;
    }

    public Course(int id, String name, int deptId) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDeptId() {
        return deptId;
    }

    public double getPrice() {
        return price; // Getter for price
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
