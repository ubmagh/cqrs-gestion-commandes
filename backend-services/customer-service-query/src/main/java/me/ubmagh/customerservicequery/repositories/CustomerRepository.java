package me.ubmagh.customerservicequery.repositories;

import me.ubmagh.customerservicequery.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository< Customer, String> {



}
