package me.ubmagh.customerservicequery.web;

import lombok.AllArgsConstructor;
import me.ubmagh.customerservicequery.entities.Customer;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import queries.GetAllCustomersQuery;
import queries.GetCustomerByIdQuery;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private QueryGateway queryGateway;

    @GetMapping("/customers")
    public List<Customer> customersList(){
        List<Customer> response = queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
        return response;
    }

    @GetMapping("/customer/{id}")
    public Customer customerById(@PathVariable String id){
        Customer response = queryGateway.query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Customer.class)).join();
        return response;
    }

}
