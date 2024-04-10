package ru.shop.service;

import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductRepository repository;

    ProductService(ProductRepository rep){
        repository = rep;
    }

    public void save(Product prod){
        repository.save(prod);
    }

    public List<Product> findAll(){
        return repository.findAll();
    }
    public List<Product> findByProductType(ProductType productType){
        List<Product> prod = new ArrayList<>();
        for(Product item: repository.findAll()){
            if(item.getProductType().equals(productType)){
                prod.add(item);
            }
        }
        return prod;
    }
}
