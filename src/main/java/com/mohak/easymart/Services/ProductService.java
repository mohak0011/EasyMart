package com.mohak.easymart.Services;

import com.mohak.easymart.Exceptions.ProductNotFoundException;
import com.mohak.easymart.Models.Product;
import com.mohak.easymart.Utilities.FakeStoreDto;
import com.mohak.easymart.Utilities.ProductRequestDto;
import com.mohak.easymart.Utilities.ProductResponseDto;

import java.util.List;

public interface ProductService {

     Product GetProduct(int productId) throws ProductNotFoundException;

     List<Product> getAllProducts();

     Product AddProduct(
            String title,
            String description,
            String imageurl,
            String category,
            double price

    );

      Product updateProduct(int productId, ProductRequestDto productRequestDto);

      Product DeleteProduct(int productId);

     List<String> GetCategories();

     List<Product> GetCategory(String category);





}
