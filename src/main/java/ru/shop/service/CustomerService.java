package ru.shop.service;

import ru.shop.model.Customer;
import ru.shop.model.Product;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.IRepository;
import ru.shop.repository.ProductRepository;

import java.util.List;

public class CustomerService implements IService<Customer> {
    private final IRepository<Customer> repository;

    public CustomerService(IRepository<Customer> rep){
        repository = rep;
    }

    public void save(Customer customer){
        repository.save(customer);
    }

    public List<Customer> findAll(){
        return repository.findAll();
    }
}
