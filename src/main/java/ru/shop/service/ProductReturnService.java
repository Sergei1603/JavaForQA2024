package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.exception.BadProductReturnCountException;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductReturn;
import ru.shop.model.ProductType;
import ru.shop.repository.ProductRepository;
import ru.shop.repository.ProductReturnRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductReturnService {
    private final ProductReturnRepository repository;

    public void add(Order order, int count) {
        if(count > order.getCount()){
            throw new BadProductReturnCountException("Return count is greater than ordered count");
        }
        ProductReturn productReturn = new ProductReturn(UUID.randomUUID(),order.getId(), LocalDate.now(), count);
        repository.save(productReturn);
    }

    public List<ProductReturn> findAll() {
        return repository.findAll();
    }

    public ProductReturn findById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
