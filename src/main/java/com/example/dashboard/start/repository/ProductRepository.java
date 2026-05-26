package com.example.dashboard.start.repository;


import com.example.dashboard.start.entity.ProductEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@NotNull
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findBySku(String sku);

//    @Query("select e from ProductEntity as e where name = ?1")
    Page<ProductEntity> findByName(String name, Pageable pageable);

    List<ProductEntity> findAllByOrderByPriceDesc();
}