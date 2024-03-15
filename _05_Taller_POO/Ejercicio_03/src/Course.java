import java.util.ArrayList;

public class Course {
    private String code;
    private String name;
    private ArrayList<Student> listStudents;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
        this.listStudents = new ArrayList<>();
    }

    public void registerStudent(Student student){
        listStudents.add(student);
    }

    public boolean unregisterStudent(int idStudent){
        return  listStudents.removeIf(student -> student.getId() == idStudent);
    }

    public void  listStudents(){
        System.out.println("Students enrolled in the course: " + name + ":");
        for (Student student : listStudents){
            System.out.println("Id: " + student.getId() + ", Name: " + student.getName());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
