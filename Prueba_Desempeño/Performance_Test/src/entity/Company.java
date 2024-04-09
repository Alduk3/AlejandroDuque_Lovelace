package entity;

public class Company {

    private int id_company;
    private String name;
    private String sector;
    private String location;
    private String contact;

    public Company() {
    }

    public Company(int id_company, String name, String sector, String location, String contact) {
        this.id_company = id_company;
        this.name = name;
        this.sector = sector;
        this.location = location;
        this.contact = contact;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id_company=" + id_company +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", location='" + location + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }

    public String toStringPersonalise(){
        return "Company: " + name + ", location: " + location ;
    }
}
