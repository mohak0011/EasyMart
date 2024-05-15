package com.mohak.easymart.Utilities;

import com.mohak.easymart.Models.Category;
import com.mohak.easymart.Models.Product;
import lombok.Getter;
import lombok.Setter;

import javax.xml.catalog.Catalog;

@Getter
@Setter

public class FakeStoreDto {

    private int id;
    private String Title;
    private String Description;
    private  Double Price;
    private  String Image;
    private  String category;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(id);
        product.setTitle(Title);
        product.setDescription(Description);
        product.setPrice(Price);
        product.setImageUrl(Image);
        Category categoryobj = new Category();
        categoryobj.setTitle(category);
        product.setCategory(categoryobj);

        return  product;

    }
}
