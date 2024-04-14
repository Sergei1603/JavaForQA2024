package ru.shop.model;

import ru.shop.model.ProductType;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private long cost;
    private ProductType productType;

    public Product(String id, String name, long cost, ProductType productType) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.productType = productType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return cost == product.cost && Objects.equals(id, product.id) && Objects.equals(name, product.name) && productType == product.productType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost, productType);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", productType=" + productType +
                '}';
    }
}
