package sg.edu.nus.iss.Day13_HW.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Day13_HW.model.ProductInfo;
import sg.edu.nus.iss.Day13_HW.repo.ProductInfoRepo;

@Service
public class ProductInfoService {
    
    private final ProductInfoRepo productInfoRepo;

    public ProductInfoService(ProductInfoRepo productInfoRepo) {
        this.productInfoRepo = productInfoRepo;
    }

    public List<ProductInfo> getProductData() {
        return productInfoRepo.getProductData();
    }

    public ProductInfo getProductById(String productId) {
        return productInfoRepo.getProductById(productId);
    }

    public Boolean updateProduct(ProductInfo productInfo) {
        return productInfoRepo.updateProduct(productInfo);
    }

    public Boolean buyProduct(ProductInfo productInfo) {
        return productInfoRepo.buyProduct(productInfo);
    }

    public ProductInfo successBuyProduct(ProductInfo productInfo) {
        return productInfoRepo.successBuyProduct(productInfo);
    }

    public List<ProductInfo> addProduct(ProductInfo productInfo) {
        return productInfoRepo.addProduct(productInfo);
    }

    public void writeToFile() {
        productInfoRepo.writeToFile();
    }


    

    


}
