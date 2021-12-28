package com.example.groupproject.repository.sales;

import com.example.groupproject.models.sales.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {
}