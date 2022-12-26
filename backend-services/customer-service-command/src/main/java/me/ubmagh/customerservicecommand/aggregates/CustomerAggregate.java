package me.ubmagh.customerservicecommand.aggregates;

import commands.CreateCustomerCommand;
import commands.DeleteCustomerCommand;
import commands.UpdateCustomerCommand;
import events.CustomerCreatedEvent;
import events.CustomerDeletedEvent;
import events.CustomerUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CustomerAggregate {


    @AggregateIdentifier
    private String id;

    private String nom;
    private String email;
    private String tel;
    private String adresse;

    private boolean deleted;

    public CustomerAggregate(){

    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command){
        AggregateLifecycle.apply( new CustomerCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getEmail(),
                command.getTel(),
                command.getAdresse()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        this.id=event.getId();
        this.deleted=false;
        this.email=event.getEmail();
        this.nom=event.getNom();
        this.tel=event.getTel();
        this.adresse=event.getAdresse();
    }


    @CommandHandler
    public void handle(UpdateCustomerCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new CustomerCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getEmail(),
                command.getTel(),
                command.getAdresse()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerUpdatedEvent event){
        this.id=event.getId();
        this.deleted=false;
        this.email=event.getEmail();
        this.nom=event.getNom();
        this.tel=event.getTel();
        this.adresse=event.getAdresse();
    }


    @CommandHandler
    public void handle(DeleteCustomerCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new CustomerDeletedEvent(
                command.getId()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerDeletedEvent event){
        this.id=event.getId();
        this.deleted=true;
    }





}
