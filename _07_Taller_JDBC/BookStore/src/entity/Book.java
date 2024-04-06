package entity;

public class Book {
    int id_book;
    int id_author;
    String title;
    int year_publication;
    double price;

    public Book(){
    }

    public Book(int id_book, int id_author, String title, int year_publication, double price) {
        this.id_book = id_book;
        this.id_author = id_author;
        this.title = title;
        this.year_publication = year_publication;
        this.price = price;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
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
                "id_book=" + id_book +
                ", id_author=" + id_author +
                ", title='" + title + '\'' +
                ", year_publication=" + year_publication +
                ", price=" + price +
                '}';
    }
}

