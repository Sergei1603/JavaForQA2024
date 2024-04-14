package ru.shop.service;

import ru.shop.model.Product;

import java.util.List;

public interface IService<T> {
    public void save(T t);
    public List<T> findAll();
}
