public class TemporaryEmployee extends Employee{
    private String contractEndDate;

    public TemporaryEmployee(String name, int age, int idEmployee, double salary, String contractEndDate) {
        super(name, age, idEmployee, salary);
        this.contractEndDate = contractEndDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }
}
