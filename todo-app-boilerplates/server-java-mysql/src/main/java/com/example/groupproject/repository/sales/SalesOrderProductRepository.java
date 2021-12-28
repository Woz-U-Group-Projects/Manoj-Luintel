package com.example.groupproject.repository.sales;

import com.example.groupproject.models.sales.SalesOrder;
import com.example.groupproject.models.sales.SalesOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesOrderProductRepository extends JpaRepository<SalesOrderProduct, Long> {
    List<SalesOrderProduct> findAllBySalesOrder(SalesOrder salesOrder);
}