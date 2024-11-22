package sg.edu.nus.iss.Day13_HW.model;

import java.util.UUID;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductInfo {

    private String id;

    @NotEmpty(message = "Item description cannot be empty")
    @Size(min = 5, max = 200, message = "Item description must be between 6 to 200 characters")
    private String item;

    private Integer sold;

    private Integer quantity;

    // if it was @NotNull error message won't appear
    @NotEmpty(message = "Please input a url for the item picture")
    private String picture;

    
    public ProductInfo() {
    }

    public ProductInfo(String id, String item, Integer sold, Integer quantity, String picture) {
        this.id = id;
        this.item = item;
        this.sold = sold;
        this.quantity = quantity;
        this.picture = picture;
    }

    public ProductInfo(String item, Integer quantity, String picture) {
        this.id = UUID.randomUUID().toString();
        this.item = item;
        this.sold = 0;
        this.quantity = quantity;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return id + ", " + item + ", " + sold + ", " + quantity + ", " + picture;
    }

    

    

    



    
}
