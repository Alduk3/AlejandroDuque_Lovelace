
public class Main {
    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();

        Employee employee = new PermanentEmployee("Alejo Duque", 21, 1, 3500, 5);
        Employee employee1 = new TemporaryEmployee("Nicole Rios", 17, 2, 4500, "17/3/2024");

        employeeManagement.addEmployee(employee);
        employeeManagement.addEmployee(employee1);

        employeeManagement.listEmployee();

        employeeManagement.delEmployee(2);

        employeeManagement.listEmployee();
    }
}