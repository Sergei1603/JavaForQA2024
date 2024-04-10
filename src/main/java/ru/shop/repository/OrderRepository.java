package ru.shop.repository;

import ru.shop.model.Order;
import ru.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    public void save(Order ord){
        orders.add(ord);
    }
    public List<Order> findAll(){
        return orders;
    }
}
