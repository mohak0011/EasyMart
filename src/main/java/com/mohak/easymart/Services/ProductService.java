package com.mohak.easymart.Services;

import com.mohak.easymart.Models.Product;
import com.mohak.easymart.Utilities.FakeStoreDto;
import com.mohak.easymart.Utilities.ProductRequestDto;
import com.mohak.easymart.Utilities.ProductResponseDto;

import java.util.List;

public interface ProductService {

     ProductResponseDto GetProduct(int productId);

     List<ProductResponseDto> getAllProducts();

     ProductResponseDto AddProduct(
            String title,
            String description,
            String imageurl,
            String category,
            double price

    );

      ProductResponseDto updateProduct(int productId, ProductRequestDto productRequestDto);

      ProductResponseDto DeleteProduct(int productId);

     List<String> GetCategories();

     List<ProductResponseDto> GetCategory(String category);





}
