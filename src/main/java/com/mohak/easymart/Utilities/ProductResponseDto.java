package com.mohak.easymart.Utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponseDto {

    private int id;
    private String Title;
    private String Description;
    private  Double Price;
    private  String Image;
    private  String category;
}
