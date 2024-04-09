package entity;

public class Vacant {
    private int id_vacant;
    private int id_company;
    private String title;
    private String description;
    private String duration;
    private boolean status;
    private String technology;

    public Vacant() {
    }

    public Vacant(int id_vacant, int id_company, String title, String description, String duration, boolean status, String technology) {
        this.id_vacant = id_vacant;
        this.id_company = id_company;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.technology = technology;
    }

    public int getId_vacant() {
        return id_vacant;
    }

    public void setId_vacant(int id_vacant) {
        this.id_vacant = id_vacant;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "Vacant{" +
                "id_vacant=" + id_vacant +
                ", id_company=" + id_company +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", status=" + status +
                ", technology='" + technology + '\'' +
                '}';
    }

    public String toStringPersonalise(){
        return "Vacant: "+ title + ", description=" + description ;
    }
}
