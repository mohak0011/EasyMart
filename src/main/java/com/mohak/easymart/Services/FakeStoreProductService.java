package com.mohak.easymart.Services;

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
    public ProductResponseDto GetProduct(int productId) {

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(

                "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
        );
         return  fakeStoreDto.toProductResponseDto();
    }

    public List<ProductResponseDto> getAllProducts() {
        ProductResponseDto[] products = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                ProductResponseDto[].class
        );
        return Arrays.asList(products);
    }

    @Override
    public  ProductResponseDto AddProduct(
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

        return fakeStoreDto.toProductResponseDto();

    }

    public ProductResponseDto updateProduct(int productId, ProductRequestDto productRequestDto) {

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(

                "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
        );

        return GetProduct(productId);
    }


    @Override
    public  ProductResponseDto DeleteProduct(int productId) {

        FakeStoreDto fakeStoreDto = restTemplate.getForObject(

                "https://fakestoreapi.com/products/" + productId, FakeStoreDto.class
        );
        return  fakeStoreDto.toProductResponseDto();

    }

    public List<ProductResponseDto> GetCategory(String category) {

      FakeStoreDto[] fakeStoreDtos = restTemplate.getForObject(

              "https://fakestoreapi.com/products/category/"+category, FakeStoreDto[].class
      );
        if (fakeStoreDtos!=null) {
            List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for (FakeStoreDto fakeStoreDto : fakeStoreDtos) {
                productResponseDtos.add(fakeStoreDto.toProductResponseDto());
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
