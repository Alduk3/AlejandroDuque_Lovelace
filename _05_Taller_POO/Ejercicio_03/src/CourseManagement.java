import java.util.ArrayList;

public class CourseManagement {
    private ArrayList<Course> courses;

    public CourseManagement(){
        courses = new ArrayList<>();
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public Course searchByCode(String code){
        for (Course course : courses){
            if (course.getCode().equals(code)){
                return course;
            }
        }
        return null;
    }

    public void listStudentsByCourse(String code){
        Course course = searchByCode(code);

        if (course != null){
            course.listStudents();
        } else {
            System.out.println("Course not found.");
        }
    }

}
