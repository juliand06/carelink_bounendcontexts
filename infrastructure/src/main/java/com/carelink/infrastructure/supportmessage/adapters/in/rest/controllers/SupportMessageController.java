package com.carelink.infrastructure.supportmessage.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.supportmessage.command.RegisterSupportMessageCommand;
import com.carelink.application.supportmessage.command.UpdateSupportMessageCommand;
import com.carelink.application.supportmessage.dto.SupportMessageResponse;
import com.carelink.application.supportmessage.usecase.DeleteSupportMessageUseCase;
import com.carelink.application.supportmessage.usecase.GetSupportMessageByIdUseCase;
import com.carelink.application.supportmessage.usecase.ListSupportMessageUseCase;
import com.carelink.application.supportmessage.usecase.RegisterSupportMessageUseCase;
import com.carelink.application.supportmessage.usecase.UpdateSupportMessageUseCase;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.infrastructure.supportmessage.adapters.in.rest.dtos.CreateSupportMessageRequest;
import com.carelink.infrastructure.supportmessage.adapters.in.rest.dtos.UpdateSupportMessageRequest;
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
@RequestMapping("/api/support-messages")
public class SupportMessageController {
    private final RegisterSupportMessageUseCase registerUseCase;
    private final GetSupportMessageByIdUseCase getByIdUseCase;
    private final ListSupportMessageUseCase listUseCase;
    private final UpdateSupportMessageUseCase updateUseCase;
    private final DeleteSupportMessageUseCase deleteUseCase;

    public SupportMessageController(RegisterSupportMessageUseCase registerUseCase,
            GetSupportMessageByIdUseCase getByIdUseCase, ListSupportMessageUseCase listUseCase,
            UpdateSupportMessageUseCase updateUseCase, DeleteSupportMessageUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SupportMessageResponse> create(@Valid @RequestBody CreateSupportMessageRequest request) {
        var command = new RegisterSupportMessageCommand(
                new SupportConversationId(request.conversationId()),
                new MessageFormatId(request.messageFormatId()),
                new ConversationMemberId(request.memberId()),
                request.payload(),
                request.extraData());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SupportMessageResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportMessageResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SupportMessageId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportMessageResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSupportMessageRequest request) {
        var command = new UpdateSupportMessageCommand(
                new SupportMessageId(id),
                new SupportConversationId(request.conversationId()),
                new MessageFormatId(request.messageFormatId()),
                new ConversationMemberId(request.memberId()),
                request.payload(),
                request.extraData());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SupportMessageId(id));
        return ResponseEntity.noContent().build();
    }
}
