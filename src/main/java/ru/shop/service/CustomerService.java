package ru.shop.service;

import ru.shop.model.Customer;
import ru.shop.model.Product;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.ProductRepository;

import java.util.List;

public class CustomerService {
    private CustomerRepository repository;

    CustomerService(CustomerRepository rep){
        repository = rep;
    }

    public void save(Customer cust){
        repository.save(cust);
    }

    public List<Customer> findAll(){
        return repository.findAll();
    }
}
