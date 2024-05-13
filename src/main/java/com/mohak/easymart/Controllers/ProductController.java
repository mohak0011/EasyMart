package com.mohak.easymart.Controllers;

import com.mohak.easymart.Models.Product;
import com.mohak.easymart.Services.FakeStoreProductService;
import com.mohak.easymart.Services.ProductService;
import com.mohak.easymart.Utilities.ProductRequestDto;
import com.mohak.easymart.Utilities.ProductResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private FakeStoreProductService productService;

    public  ProductController(FakeStoreProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto GetProductDetails(@PathVariable ("id" ) int productid)
    {
         return productService.GetProduct(productid);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }


    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        return productService.AddProduct(

                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/products/{id}")
    public ProductResponseDto DeleteProduct(@PathVariable ("id" ) int productid)
    {
        return productService.DeleteProduct(productid);
    }

    @GetMapping("/products/category/{category}")
    public List<ProductResponseDto> GetCategory(@PathVariable("category") String category) {
        return productService.GetCategory(category);
    }

    @GetMapping("/products/categories")
    public List<String> getCategories() {
        return productService.GetCategories();
    }




}
