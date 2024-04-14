package ru.shop.model;

import java.util.Objects;
import java.util.UUID;

public class Order {
    private String id;
    private String customerId;
    private String productId;
    private long count;
    private long amount;
    private Product product;

    public Product getProduct() {
        return product;
    }



    public Order(Customer customer, Product product, long count) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customer.getId();
        this.productId = product.getId();
        this.count = count;
        this.amount = count * product.getCost();
        this.product = product;
    }
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return count == order.count && amount == order.amount && Objects.equals(id, order.id) && Objects.equals(customerId, order.customerId) && Objects.equals(productId, order.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, productId, count, amount);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getAmount() {
        return amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
