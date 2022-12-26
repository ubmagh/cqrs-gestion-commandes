package me.ubmagh.customerservicequery.services;

import events.CustomerCreatedEvent;
import events.CustomerDeletedEvent;
import events.CustomerUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ubmagh.customerservicequery.entities.Customer;
import me.ubmagh.customerservicequery.repositories.CustomerRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CustomerEventHandler {

    private CustomerRepository repository;

    @EventHandler
    public void handle(CustomerCreatedEvent event){
        Customer customer = new Customer(
                event.getId(),
                event.getNom(),
                event.getEmail(),
                event.getTel(),
                event.getAdresse()
        );
        repository.save(customer);
        log.info("👉👉 Customer persisted !");
    }

    @EventHandler
    public void handle(CustomerUpdatedEvent event){

        Customer customer = repository.findById(event.getId()).get();
        customer.setAdresse(event.getAdresse());
        customer.setEmail(event.getEmail());
        customer.setNom(event.getNom());
        customer.setTel(event.getTel());
        repository.save(customer);
        log.info("👉👉 Customer updated !");
    }

    @EventHandler
    public void handle(CustomerDeletedEvent event){
        repository.deleteById(event.getId());
        log.info("👉👉 Customer deleted !");
    }


}
