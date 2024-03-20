package entity;

public class Book {
    int id;
    int id_author;
    String title;
    int year_publication;
    double price;

    public Book(){
    }

    public Book(int id, int id_author, String title, int year_publication, double price) {
        this.id = id;
        this.id_author = id_author;
        this.title = title;
        this.year_publication = year_publication;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(int year_publication) {
        this.year_publication = year_publication;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", id_author=" + id_author +
                ", title='" + title + '\'' +
                ", year_publication=" + year_publication +
                ", price=" + price +
                '}';
    }
}

