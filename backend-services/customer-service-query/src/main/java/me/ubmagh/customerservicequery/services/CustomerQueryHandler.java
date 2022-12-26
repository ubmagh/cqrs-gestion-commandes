package me.ubmagh.customerservicequery.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.customerservicequery.entities.Customer;
import me.ubmagh.customerservicequery.repositories.CustomerRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import queries.GetAllCustomersQuery;
import queries.GetCustomerByIdQuery;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CustomerQueryHandler {

    private CustomerRepository repository;

    @QueryHandler
    public List<Customer> on(GetAllCustomersQuery query){
        return repository.findAll();
    }

    @QueryHandler
    public Customer on(GetCustomerByIdQuery query){
        return repository.findById(query.getId()).get();
    }


}
