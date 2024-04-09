package entity;

public class Hiring {
    private int id_hiring;
    private int id_vacant;
    private int id_coder;
    private String application_date;
    private boolean status;
    private Double salary;

    public Hiring() {
    }

    public Hiring(int id_hiring, int id_vacant, int id_coder, String application_date, boolean status, Double salary) {
        this.id_hiring = id_hiring;
        this.id_vacant = id_vacant;
        this.id_coder = id_coder;
        this.application_date = application_date;
        this.status = status;
        this.salary = salary;
    }

    public int getId_hiring() {
        return id_hiring;
    }

    public void setId_hiring(int id_hiring) {
        this.id_hiring = id_hiring;
    }

    public int getId_vacant() {
        return id_vacant;
    }

    public void setId_vacant(int id_vacant) {
        this.id_vacant = id_vacant;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public String getApplication_date() {
        return application_date;
    }

    public void setApplication_date(String application_date) {
        this.application_date = application_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Hiring{" +
                "id_hiring=" + id_hiring +
                ", id_vacant=" + id_vacant +
                ", id_coder=" + id_coder +
                ", application_date='" + application_date + '\'' +
                ", status=" + status +
                ", salary=" + salary +
                '}';
    }
}
