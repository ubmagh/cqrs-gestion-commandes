package me.ubmagh.customerservicecommand.web;

import commands.CreateCustomerCommand;
import commands.DeleteCustomerCommand;
import commands.UpdateCustomerCommand;
import dtos.CreateCustomerDTO;
import dtos.UpdateCustomerDTO;
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
@RequestMapping("/cutomers")
public class CustomerCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateCustomerDTO dto){
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                dto.getNom(),
                dto.getEmail(),
                dto.getTel(),
                dto.getAdresse()
        ));
        return commandResponse;
    }


    @PutMapping(path = "/update/{id}")
    public CompletableFuture<String> updateAccount(@PathVariable String id, @RequestBody UpdateCustomerDTO dto){
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateCustomerCommand(
                id,
                dto.getNom(),
                dto.getEmail(),
                dto.getTel(),
                dto.getAdresse()
        ));
        return commandResponse;
    }

    @DeleteMapping(path = "/delete/{id}")
    public CompletableFuture<String> deleteAccount(@PathVariable String id){
        CompletableFuture<String> commandResponse = commandGateway.send(new DeleteCustomerCommand(
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
