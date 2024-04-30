package ru.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
// Сущности храниться в БД
@Entity
// Задаём таблицы для хранения
@Table(name = "customer")
public class Customer {
    @Id
    UUID id;
    String name;
    String phone;
    long age;
}
