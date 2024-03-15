import java.util.ArrayList;

public class EmployeeManagement {
    private ArrayList<Employee> employees;

    public EmployeeManagement(){
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public boolean delEmployee(int idEmployee){
        return employees.removeIf(employee -> employee.getIdEmployee() == idEmployee);
    }

    public void listEmployee(){
        for (Employee employee : employees){
            System.out.println("Id Employee: " + employee.getIdEmployee() +
                    " Name: " + employee.getName() +
                    " Salary: " + employee.getSalary());
        }
    }
}
