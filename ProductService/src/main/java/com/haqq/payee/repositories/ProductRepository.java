package com.haqq.payee.repositories;

import com.haqq.payee.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCreatorUuid(String creatorUuid);

    void deleteByProductCode(String productCode);

    Product findByProductCode(String productCode);
}
