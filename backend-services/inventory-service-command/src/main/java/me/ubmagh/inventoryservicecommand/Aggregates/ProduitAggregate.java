package me.ubmagh.inventoryservicecommand.Aggregates;

import commands.*;
import enums.EtatProduit;
import events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProduitAggregate {

    @AggregateIdentifier
    private String id;

    private String nom;
    private Double prix;
    private int quantite;
    private EtatProduit etat;

    private boolean deleted;

    public ProduitAggregate(){
    }

    @CommandHandler
    public ProduitAggregate(CreateProduitCommand command){
        AggregateLifecycle.apply( new ProduitCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getPrix(),
                command.getQuantite(),
                command.getEtat()
        ));
    }
    @EventSourcingHandler
    public void on(ProduitCreatedEvent event){
        this.id=event.getId();
        this.etat=event.getEtat();
        this.nom = event.getNom();
        this.quantite=event.getQuantite();
        this.prix=event.getPrix();
        deleted=false;
    }


    @CommandHandler
    public void hanlde(UpdateProduitCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new ProduitCreatedEvent(
                command.getId(),
                command.getNom(),
                command.getPrix(),
                command.getQuantite(),
                command.getEtat()
        ));
    }
    @EventSourcingHandler
    public void on(ProduitUpdatedEvent event){
        this.id=event.getId();
        this.etat=event.getEtat();
        this.nom = event.getNom();
        this.quantite=event.getQuantite();
        this.prix=event.getPrix();
        deleted=false;
    }



    @CommandHandler
    public void hanlde(DeleteProduitCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new ProduitDeletedEvent(
                command.getId()
        ));
    }
    @EventSourcingHandler
    public void on(DeleteProduitCommand event){
        this.id=event.getId();
        deleted=true;
    }


    @CommandHandler
    public void hanlde(ChangeEtatProduitCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new ProduitEtatChangedEvent(
                command.getId(),
                command.getEtat()
        ));
    }
    @EventSourcingHandler
    public void on(ProduitEtatChangedEvent event){
        this.id=event.getId();
        this.etat = event.getEtat();
        deleted=false;
    }

    @CommandHandler
    public void hanlde(ChangeCategorieProduitCommand command){
        if(this.deleted) return;
        AggregateLifecycle.apply( new ProduitCategorieChangedEvent(
                command.getId(),
                command.getCategorieId()
        ));
    }
    @EventSourcingHandler
    public void on(ProduitCategorieChangedEvent event){
        this.id=event.getId();
        deleted=false;
    }


}
