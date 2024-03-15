
public class Main {
    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();

        Course course = new Course("CS101", "Introduction to programming");
        Course course1 = new Course("CS102", "Data Structure");

        Student student =new Student(1, "Alejandro","alejandro@gmail.com");
        Student student1 =new Student(2, "Nicole","nicole@gmail.com");

        course.registerStudent(student);
        course1.registerStudent(student1);

        courseManagement.addCourse(course);
        courseManagement.addCourse(course1);

        courseManagement.listStudentsByCourse("CS101");
        courseManagement.listStudentsByCourse("CS102");
    }
}