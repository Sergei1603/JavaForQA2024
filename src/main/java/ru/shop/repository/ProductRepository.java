package ru.shop.repository;

import ru.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();

    public void save(Product prod){
        products.add(prod);
    }
    public List<Product> findAll(){
        return products;
    }
}
