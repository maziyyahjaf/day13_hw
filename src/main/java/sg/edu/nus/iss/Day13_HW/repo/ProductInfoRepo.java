package sg.edu.nus.iss.Day13_HW.repo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import sg.edu.nus.iss.Day13_HW.model.ProductInfo;

@Repository
public class ProductInfoRepo {

    @Value("${data.dir}")
    private String dataDir;

    List<ProductInfo> products;

    public ProductInfoRepo() {
        this.products = new ArrayList<>();
    }

    @PostConstruct
    private void loadInitialData() {
        // initialize the product list after properties are injected
        reloadProductDataFromFile();

    }

    // load products from the file
    public void reloadProductDataFromFile() {

        Path file = Paths.get(dataDir, "products.txt");

        List<ProductInfo> tempProducts = new ArrayList<>();

        if (Files.exists(file)) {
            try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                // skip the header line
                reader.readLine();
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    if (data.length >= 5) { // ensure enough data
                        String id = data[0].trim();
                        String item = data[1].trim();
                        Integer sold = Integer.parseInt(data[2].trim());
                        Integer quantity = Integer.parseInt(data[3].trim());
                        String picture = data[4].trim();

                        ProductInfo product = new ProductInfo(id, item, sold, quantity, picture);
                        tempProducts.add(product);

                    }

                }

            } catch (IOException e) {
                // change to logging
                System.out.print(e.getMessage());
            }
        } else {

            System.err.println("File not found: " + file.toString());

        }

        products = tempProducts; // update in-memory list

    }

    public List<ProductInfo> getProductData() {
        return products;

    }

    public ProductInfo getProductById(String productId) {
        ProductInfo p = products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .get();
        return p;

    }

    public Boolean buyProduct(ProductInfo product) {

        if (product.getSold() == product.getQuantity()) {
            return false;
        }

        return true;
    }

    public Boolean updateProduct(ProductInfo product) {

        List<ProductInfo> filteredProducts = products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .toList();

        if (filteredProducts.size() > 0) {
            products.remove(filteredProducts.getFirst());
            products.add(product);
        }

        return false;
    }

    public ProductInfo successBuyProduct(ProductInfo product) {

        // need to increase the number of sold
        return new ProductInfo(product.getId(), product.getItem(), product.getSold() + 1, product.getQuantity(),
                product.getPicture());
    }

    public List<ProductInfo> addProduct(ProductInfo product) {

        products.add(product);

        return products;
    }

    public void writeToFile() {

        Path file = Paths.get(dataDir, "products.txt");

        if(Files.exists(file)) {
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {

                writer.write("id" + ",");
                writer.write("item" + ",");
                writer.write("sold" + ",");
                writer.write("quantity" + ",");
                writer.write("picture" + "\n");

                for (ProductInfo product : products) {
                    // writer.write(product.getId() + ",");
                    // writer.write(product.getItem() + ",");
                    // writer.write(product.getSold() + ",");
                    // writer.write(product.getQuantity() + ",");
                    // writer.write(product.getPicture() + "\n");
                    writer.write(product.toString() + "\n");

                }

            } catch (IOException e) {
                System.out.println("Couldn't save product data to file" + e.getMessage());
            }

        } else {
            System.err.println("File not found: " + file.toString());

        }
    }

}
