package entity;

public class Airplane {
    private int id_airplane;
    private String model;
    private int capacity;

    public Airplane() {
    }

    public Airplane(int id_airplane, String model, int capacity) {
        this.id_airplane = id_airplane;
        this.model = model;
        this.capacity = capacity;
    }

    public int getId_airplane() {
        return id_airplane;
    }

    public void setId_airplane(int id_airplane) {
        this.id_airplane = id_airplane;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane: " +
                "Id: " + id_airplane + " " +
                model  + " " +
                "Capacity: " + capacity;
    }
}
