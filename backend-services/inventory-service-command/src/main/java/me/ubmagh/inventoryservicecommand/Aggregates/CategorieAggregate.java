package me.ubmagh.inventoryservicecommand.Aggregates;

import commands.CreateCategorieCommand;
import commands.DeleteCategorieCommand;
import commands.UpdateCategorieCommand;
import events.CategorieCreatedEvent;
import events.CategorieDeletedEvent;
import events.CategorieUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategorieAggregate {

    @AggregateIdentifier
    private String id;

    private String nom;
    private String description;

    private boolean deleted;

    public CategorieAggregate(){
    }

    @CommandHandler
    public CategorieAggregate(CreateCategorieCommand command){
        AggregateLifecycle.apply( new CategorieCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getDescription()
        ));
    }
    @EventSourcingHandler
    public void on(CategorieCreatedEvent event){
        this.id=event.getId();
        this.nom = event.getNom();
        this.description= event.getDescription();
        deleted=false;
    }


    @CommandHandler
    public void hanlde(UpdateCategorieCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new CategorieCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getDescription()
        ));
    }
    @EventSourcingHandler
    public void on(CategorieUpdatedEvent event){
        this.id=event.getId();
        this.nom = event.getNom();
        this.description=event.getDescription();
        deleted=false;
    }



    @CommandHandler
    public void hanlde(DeleteCategorieCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new CategorieDeletedEvent(
                command.getId()
        ));
    }
    @EventSourcingHandler
    public void on(DeleteCategorieCommand event){
        this.id=event.getId();
        deleted=true;
    }




}
