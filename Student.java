public class Student {
    private String name;
    private int id;
    private double marks;
    private String email;

    public Student(String name, int id, double marks, String email) {
        this.name = name;
        this.id = id;
        this.marks = marks;
        this.email = email;
    }

    // Getters
    public String getName() { return name; }
    public int getId() { return id; }
    public double getMarks() { return marks; }
    public String getEmail() { return email; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Marks: " + marks + " | Email: " + email;
    }
}
