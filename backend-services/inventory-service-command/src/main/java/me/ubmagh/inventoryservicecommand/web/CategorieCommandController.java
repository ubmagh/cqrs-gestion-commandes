package me.ubmagh.inventoryservicecommand.web;

import commands.CreateCategorieCommand;
import commands.DeleteCategorieCommand;
import commands.UpdateCategorieCommand;
import dtos.CreateCategorieDTO;
import dtos.UpdateCategorieDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategorieCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;


    @PostMapping(path = "/create")
    public CompletableFuture<String> createCategorie(@RequestBody CreateCategorieDTO dto) {

        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCategorieCommand(
                UUID.randomUUID().toString(),
                dto.getNom().toLowerCase(Locale.ROOT),
                dto.getDescription()
        ));
        return commandResponse;
    }


    @PutMapping("/update/{id}")
    public CompletableFuture<String> updateCategorie(@PathVariable String id, @RequestBody UpdateCategorieDTO dto) {
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCategorieCommand(
                id,
                dto.getNom().toLowerCase(Locale.ROOT),
                dto.getDescription()
        ));
        return commandResponse;
    }


    @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> updateCategorie(@PathVariable String id) {
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteCategorieCommand(
                id
        ));
        return commandResponse;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> response = new ResponseEntity<String>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
