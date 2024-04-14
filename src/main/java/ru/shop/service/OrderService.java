package ru.shop.service;

import ru.shop.BadOrderCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.IRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final IRepository<Order> repository;

    public OrderService(IRepository<Order> repository) {
        this.repository = repository;
    }

    public void add(Customer customer, Product product, int count) throws BadOrderCountException {
        if (count > 0){
            repository.save(new Order(customer, product, count));
        }
        else{
            throw new BadOrderCountException("Количество не может быть меньше 0");
        }
    }

    public List<Order> findAll(){
        return repository.findAll();
    }

    public List<Order> findByCustomer(Customer customer){
        List<Order> prod = new ArrayList<>();
        for(Order item: repository.findAll()){
            if(item.getCustomerId().equals(customer.getId())){
                prod.add(item);
            }
        }
        return prod;
    }

    public long getTotalCustomerAmount(Customer customer){
        long totalAmount = 0;
        for(Order item: repository.findAll()){
            if(item.getCustomerId().equals(customer.getId())){
                totalAmount += item.getAmount();
            }
        }
        return totalAmount;
    }

    public List<Order> findByProducttype(ProductType productType){
        List<Order> prod = new ArrayList<>();
        List<Order> tmp = repository.findAll();
        for(Order item: tmp){
            Product p = item.getProduct();
            if(p.getProductType().equals(productType)){
                prod.add(item);
            }
        }
        return prod;
    }
}
