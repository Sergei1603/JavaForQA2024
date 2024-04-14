package ru.shop;

import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.IRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;
import ru.shop.service.CustomerService;
import ru.shop.service.OrderService;
import ru.shop.service.ProductService;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        IRepository<Order> orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);

        IRepository<Customer> customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository);
        Customer customer1 = new Customer("1235", "asd", "235", 30);
        Customer customer2 = new Customer("658", "qye", "795", 25);
        Customer customer3 = new Customer("795", "pcvv", "cvkv", 40);

        customerService.save(customer1);
        customerService.save(customer2);
        customerService.save(customer3);

        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        Product product1 = new Product("5621", "name1", 13554, ProductType.GOOD);
        Product product2 = new Product("9762", "name2", 1745, ProductType.GOOD);
        Product product3 = new Product("7951", "name3", 1757, ProductType.SERVICE);
        Product product4 = new Product("8621", "name4", 3597, ProductType.SERVICE);
        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);

        try{
            orderService.add(customer1, product1, 1);
            orderService.add(customer2, product2, 2);
            orderService.add(customer3, product4, 10);
            orderService.add(customer2, product3, 4);
            orderService.add(customer3, product4, 3);
            orderService.add(customer1, product2, -1);

        }
        catch (BadOrderCountException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Всего заказчиков: " + customerService.findAll().size());
        System.out.println("Всего заказов: " + orderService.findAll().size());
        System.out.println("Заказов типа GOOD: " + orderService.findByProducttype(ProductType.GOOD).size());
        System.out.println("Заказов типа SERVICE: " + orderService.findByProducttype(ProductType.SERVICE).size());

        Map<String, Integer> orderByCustomers = new HashMap<>();

        for(Order order: orderService.findAll()){
            var item = orderByCustomers.get(order.getCustomerId());
            if(item == null){
                orderByCustomers.put(order.getCustomerId(), 1);
            }
            else{
                orderByCustomers.put(order.getCustomerId(), item + 1);
            }
        }
        System.out.println("Количество заказов по заказчикам " + orderByCustomers);

        Map<String, Long> AmountByCustomers = new HashMap<>();

        for(Order order: orderService.findAll()){
            var item = AmountByCustomers.get(order.getCustomerId());
            if(item == null){
                AmountByCustomers.put(order.getCustomerId(), order.getAmount());
            }
            else{
                AmountByCustomers.put(order.getCustomerId(), item + order.getAmount());
            }
        }
        System.out.println("Сумма для оплаты по заказчикам: " + AmountByCustomers);

    }

}