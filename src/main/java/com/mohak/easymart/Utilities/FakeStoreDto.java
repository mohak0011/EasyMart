package com.mohak.easymart.Utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreDto {

    private int id;
    private String Title;
    private String Description;
    private  Double Price;
    private  String Image;
    private  String category;

    public  ProductResponseDto toProductResponseDto()
    {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(id);
        productResponseDto.setTitle(Title);
        productResponseDto.setDescription(Description);
        productResponseDto.setPrice(Price);
        productResponseDto.setImage(Image);
        productResponseDto.setCategory(category);

        return  productResponseDto;

    }
}
