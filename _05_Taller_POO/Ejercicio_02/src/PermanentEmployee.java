public class PermanentEmployee extends Employee{
    private int yearsService;

    public PermanentEmployee(String name, int age, int idEmployee, double salary, int yearsService) {
        super(name, age, idEmployee, salary);
        this.yearsService = yearsService;
    }

    public int getYearsService() {
        return yearsService;
    }

    public void setYearsService(int yearsService) {
        this.yearsService = yearsService;
    }
}
