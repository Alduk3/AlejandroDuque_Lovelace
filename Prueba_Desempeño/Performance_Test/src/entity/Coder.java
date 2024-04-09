package entity;

public class Coder {
    private int id_coder;
    private String name;
    private String lastname;
    private String document;
    private int cohort;
    private String cv;
    private String clan;

    public Coder() {
    }

    public Coder(int id_coder, String name, String lastname, String document, int cohort, String cv, String clan) {
        this.id_coder = id_coder;
        this.name = name;
        this.lastname = lastname;
        this.document = document;
        this.cohort = cohort;
        this.cv = cv;
        this.clan = clan;
    }

    public int getId_coder() {
        return id_coder;
    }

    public void setId_coder(int id_coder) {
        this.id_coder = id_coder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getCohort() {
        return cohort;
    }

    public void setCohort(int cohort) {
        this.cohort = cohort;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    @Override
    public String toString() {
        return "Coder{" +
                "id_coder=" + id_coder +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", document='" + document + '\'' +
                ", cohort=" + cohort +
                ", cv='" + cv + '\'' +
                ", clan='" + clan + '\'' +
                '}';
    }

    public String toStringPersonalise(){
        return "Coder: " + name +
                ", lastname: " + lastname  +
                ", document: " + document  +
                ", cv:" + cv  ;
    }
}
