package com.mohak.easymart.Models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private int id;
    private String Title;
    private String Description;
    private  Double Price;
    private  String ImageUrl;
    private Category category;



}
