package com.carelink.infrastructure.messageformat.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.messageformat.command.RegisterMessageFormatCommand;
import com.carelink.application.messageformat.command.UpdateMessageFormatCommand;
import com.carelink.application.messageformat.dto.MessageFormatResponse;
import com.carelink.application.messageformat.usecase.DeleteMessageFormatUseCase;
import com.carelink.application.messageformat.usecase.GetMessageFormatByIdUseCase;
import com.carelink.application.messageformat.usecase.ListMessageFormatUseCase;
import com.carelink.application.messageformat.usecase.RegisterMessageFormatUseCase;
import com.carelink.application.messageformat.usecase.UpdateMessageFormatUseCase;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.infrastructure.messageformat.adapters.in.rest.dtos.CreateMessageFormatRequest;
import com.carelink.infrastructure.messageformat.adapters.in.rest.dtos.UpdateMessageFormatRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message-formats")
public class MessageFormatController {
    private final RegisterMessageFormatUseCase registerUseCase;
    private final GetMessageFormatByIdUseCase getByIdUseCase;
    private final ListMessageFormatUseCase listUseCase;
    private final UpdateMessageFormatUseCase updateUseCase;
    private final DeleteMessageFormatUseCase deleteUseCase;

    public MessageFormatController(RegisterMessageFormatUseCase registerUseCase,
            GetMessageFormatByIdUseCase getByIdUseCase, ListMessageFormatUseCase listUseCase,
            UpdateMessageFormatUseCase updateUseCase, DeleteMessageFormatUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MessageFormatResponse> create(@Valid @RequestBody CreateMessageFormatRequest request) {
        var command = new RegisterMessageFormatCommand(
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MessageFormatResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageFormatResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MessageFormatId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageFormatResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateMessageFormatRequest request) {
        var command = new UpdateMessageFormatCommand(
                new MessageFormatId(id),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new MessageFormatId(id));
        return ResponseEntity.noContent().build();
    }
}
