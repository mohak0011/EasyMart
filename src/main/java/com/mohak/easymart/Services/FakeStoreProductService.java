package com.mohak.easymart.Services;

import com.mohak.easymart.Exceptions.ProductNotFoundException;
import com.mohak.easymart.Models.Product;
import com.mohak.easymart.Utilities.FakeStoreDto;
import com.mohak.easymart.Utilities.ProductRequestDto;
import com.mohak.easymart.Utilities.ProductResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public  FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product GetProduct(int productId) throws ProductNotFoundException{

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(

                "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
        );

        if(fakeStoreDto==null){
            throw new ProductNotFoundException("Product with id " + " "+ productId + " " +"Not Found");
        }

         return  fakeStoreDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreDto[] fakeStoreDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreDto[].class
        );


        List<Product> products = new ArrayList<>();

        for (FakeStoreDto fakeStoreDto : fakeStoreDtos) {
            products.add(fakeStoreDto.toProduct());
        }
        return products;
    }

    @Override
    public  Product AddProduct(
            String title,
            String description,
            String imageurl,
            String category,
            double price
    ) {

        FakeStoreDto fakeStoreDto = new FakeStoreDto();
        fakeStoreDto.setTitle(title);
        fakeStoreDto.setDescription(description);
        fakeStoreDto.setImage(imageurl);
        fakeStoreDto.setCategory(category);
        fakeStoreDto.setPrice(price);

        FakeStoreDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                fakeStoreDto,
                FakeStoreDto.class
        );

        return fakeStoreDto.toProduct();

    }

    public Product updateProduct(int productId, ProductRequestDto productRequestDto) {
        try {
            FakeStoreDto fakeStoreDto = restTemplate.getForObject(
                    "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
            );
            return GetProduct(productId);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public  Product DeleteProduct(int productId) {

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(

                "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
        );
        return  fakeStoreDto.toProduct();

    }

    public List<Product> GetCategory(String category) {

      FakeStoreDto[] fakeStoreDtos = restTemplate.getForObject(

              "https://fakestoreapi.com/products/category/"+category, FakeStoreDto[].class
      );
        if (fakeStoreDtos!=null) {
            List<Product> productResponseDtos = new ArrayList<>();
            for (FakeStoreDto fakeStoreDto : fakeStoreDtos) {
                productResponseDtos.add(fakeStoreDto.toProduct());
            }
            return productResponseDtos;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> GetCategories() {
        return restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                List.class
        );
    }



}
