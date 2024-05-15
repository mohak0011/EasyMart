package com.mohak.easymart.Controllers;

import com.mohak.easymart.Exceptions.ProductNotFoundException;
import com.mohak.easymart.Models.Product;
import com.mohak.easymart.Services.FakeStoreProductService;
import com.mohak.easymart.Services.ProductService;
import com.mohak.easymart.Utilities.ErrorDto;
import com.mohak.easymart.Utilities.ProductRequestDto;
import com.mohak.easymart.Utilities.ProductResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private FakeStoreProductService productService;
    private ModelMapper modelMapper;

    public  ProductController(FakeStoreProductService productService, ModelMapper modelMapper)
    {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> GetProductDetails(@PathVariable ("id" ) int productid) throws ProductNotFoundException
    {
         Product product =  productService.GetProduct(productid);
         return new ResponseEntity<>( convertToProductResponseDto(product),
                 HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products =  productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products)
        {
            productResponseDtos.add(convertToProductResponseDto(product));
        }
        return  new ResponseEntity<>( productResponseDtos, HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        Product product =  productService.AddProduct(

                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
        return new ResponseEntity<>( convertToProductResponseDto(product),HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody ProductRequestDto productRequestDto) {
        return  new ResponseEntity<>(productService.updateProduct(id, productRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> DeleteProduct(@PathVariable ("id" ) int productid)
    {
        return new ResponseEntity<>(productService.DeleteProduct(productid),HttpStatus.OK);
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> GetCategory(@PathVariable("category") String category) {
        return new ResponseEntity<>( productService.GetCategory(category), HttpStatus.OK);
    }

    @GetMapping("/products/categories")
    public ResponseEntity< List<String>> getCategories()
    {
        return new ResponseEntity<>(productService.GetCategories(), HttpStatus.OK);
    }

private  ProductResponseDto convertToProductResponseDto(Product product) {

        String categoryTitle = product.getCategory().getTitle();
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);
        productResponseDto.setCategory(categoryTitle);
        return productResponseDto;
}




}
