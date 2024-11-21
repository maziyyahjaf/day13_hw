package sg.edu.nus.iss.Day13_HW.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.Day13_HW.model.ProductInfo;
import sg.edu.nus.iss.Day13_HW.service.ProductInfoService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductInfoService productInfoService;

    public ProductController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @GetMapping("")
    public String showProducts(Model model) {
        var products = productInfoService.getProductData();
        model.addAttribute("products", products);

        return "productlist";
    }

    @GetMapping("/{id}")
    public String buyProduct(@PathVariable String id, Model model) {

        ProductInfo product = productInfoService.getProductById(id);
        // get the product -> need to use th buy product logic
        if (!productInfoService.buyProduct(product)) {
            return "outofstock";
        }

        ProductInfo updatedProductSold = productInfoService.successBuyProduct(product);
        productInfoService.updateProduct(updatedProductSold);

        // if buy product successfull -> will update product info
        // if false redirect to another page?

        return "redirect:/products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        
        model.addAttribute("product", new ProductInfo());
        
        return "addproduct";
    }

    @PostMapping("/add")
    public String postAddProduct(@Valid @ModelAttribute("product") ProductInfo product, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "addproduct";
        }
        
        ProductInfo p = new ProductInfo(product.getItem(), product.getQuantity(), product.getPicture());
     
        productInfoService.addProduct(p);
        return "redirect:/products";
    }

    @GetMapping("/save")
    public String saveProductList() {
        productInfoService.writeToFile();
        return "savesuccess";
    }

 
    
    
}
