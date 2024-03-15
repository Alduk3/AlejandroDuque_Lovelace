public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        Product product1 = new Product(1, "Pencil", 2000);
        SpecificProduct product2 = new SpecificProduct(2, "NoteBook", 8900, "Stationery","Ecological");

        inventory.addProduct(product1);
        inventory.addProduct(product2);

        inventory.listProducts();

        inventory.deleteProduct(1);

        inventory.listProducts();


    }
}