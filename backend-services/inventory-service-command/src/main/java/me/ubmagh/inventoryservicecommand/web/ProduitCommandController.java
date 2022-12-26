package me.ubmagh.inventoryservicecommand.web;

import commands.*;
import dtos.ChangeCategorieProduitDTO;
import dtos.ChangeEtatDTO;
import dtos.CreateProduitDTO;
import dtos.UpdateProduitDTO;
import exceptions.InvalidInputsException;
import exceptions.InvalidPrixException;
import exceptions.InvalidQuantityException;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/produits")
public class ProduitCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;


    @PostMapping(path = "/create")
    public CompletableFuture<String> createProduit(@RequestBody CreateProduitDTO dto) throws InvalidQuantityException, InvalidPrixException {

        if (dto.getQuantite() < 0) {
            throw new InvalidQuantityException("quantité invalid !", dto.getQuantite());
        }
        if (dto.getPrix() <= 0) {
            throw new InvalidPrixException("prix invalid !", dto.getPrix());
        }

        CompletableFuture<String> commandResponse = commandGateway.send(new CreateProduitCommand(
                UUID.randomUUID().toString(),
                dto.getNom(),
                dto.getPrix(),
                dto.getQuantite(),
                dto.getEtat()
        ));
        return commandResponse;
    }

    @PatchMapping (path = "/change_etat/{id_prod}")
    public CompletableFuture<String> changeEtatProduit(@PathVariable(name = "id_prod") String id_prod, @RequestBody ChangeEtatDTO dto) {

        CompletableFuture<String> commandResponse = commandGateway.send(new ChangeEtatProduitCommand(
                id_prod,
                dto.getEtat()
        ));
        return commandResponse;
    }


    @PatchMapping (path = "/change_categorie/{id_prod}")
    public CompletableFuture<String> changeEtatProduit(@PathVariable(name = "id_prod") String id_prod, @RequestBody ChangeCategorieProduitDTO dto) {

        CompletableFuture<String> commandResponse = commandGateway.send(new ChangeCategorieProduitCommand(
                id_prod,
                dto.getCategorieId()
        ));
        return commandResponse;
    }


    @PutMapping("/update/{id}")
    public CompletableFuture<String> updateProduit(@PathVariable String id, @RequestBody UpdateProduitDTO dto) throws InvalidQuantityException, InvalidPrixException {
        if (dto.getQuantite() < 0) {
            throw new InvalidQuantityException("quantité invalid !", dto.getQuantite());
        }
        if (dto.getPrix() <= 0) {
            throw new InvalidPrixException("prix invalid !", dto.getPrix());
        }
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateProduitCommand(
                id,
                dto.getNom(),
                dto.getPrix(),
                dto.getQuantite(),
                dto.getEtat()
        ));
        return commandResponse;
    }


    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> updateProduit(@PathVariable String id) {
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteProduitCommand(
                id
        ));
        return commandResponse;
    }


    @ExceptionHandler(InvalidInputsException.class)
    public ResponseEntity<String> inputExceptionHandler(InvalidInputsException exception){
        ResponseEntity<String> response = new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> response = new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
