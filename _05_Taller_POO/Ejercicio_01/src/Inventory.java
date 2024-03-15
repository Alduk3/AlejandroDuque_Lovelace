import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;

    public Inventory(){
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public boolean deleteProduct(int id){
        return products.removeIf(product -> product.getId() == id);
    }

    public void listProducts(){
        for (Product product: products){
            System.out.println("ID: " + product.getId() +
                    " Name: " + product.getName()+
                    " Price: " + product.getPrice());
        }
    }
}
