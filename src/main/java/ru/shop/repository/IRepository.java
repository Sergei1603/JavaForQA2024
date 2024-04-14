package ru.shop.repository;

import ru.shop.model.Customer;
import ru.shop.model.Order;

import java.util.List;

public interface IRepository<T> {
    public void save(T t);
    public List<T> findAll();
}
