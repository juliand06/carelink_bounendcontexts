package com.carelink.infrastructure.conversationmember.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.conversationmember.command.RegisterConversationMemberCommand;
import com.carelink.application.conversationmember.command.UpdateConversationMemberCommand;
import com.carelink.application.conversationmember.dto.ConversationMemberResponse;
import com.carelink.application.conversationmember.usecase.DeleteConversationMemberUseCase;
import com.carelink.application.conversationmember.usecase.GetConversationMemberByIdUseCase;
import com.carelink.application.conversationmember.usecase.ListConversationMemberUseCase;
import com.carelink.application.conversationmember.usecase.RegisterConversationMemberUseCase;
import com.carelink.application.conversationmember.usecase.UpdateConversationMemberUseCase;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.infrastructure.conversationmember.adapters.in.rest.dtos.CreateConversationMemberRequest;
import com.carelink.infrastructure.conversationmember.adapters.in.rest.dtos.UpdateConversationMemberRequest;
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
@RequestMapping("/api/conversation-members")
public class ConversationMemberController {
    private final RegisterConversationMemberUseCase registerUseCase;
    private final GetConversationMemberByIdUseCase getByIdUseCase;
    private final ListConversationMemberUseCase listUseCase;
    private final UpdateConversationMemberUseCase updateUseCase;
    private final DeleteConversationMemberUseCase deleteUseCase;

    public ConversationMemberController(RegisterConversationMemberUseCase registerUseCase,
            GetConversationMemberByIdUseCase getByIdUseCase, ListConversationMemberUseCase listUseCase,
            UpdateConversationMemberUseCase updateUseCase, DeleteConversationMemberUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ConversationMemberResponse> create(@Valid @RequestBody CreateConversationMemberRequest request) {
        var command = new RegisterConversationMemberCommand(
                new SupportConversationId(request.conversationId()),
                request.clientId() == null ? null : new ClientId(request.clientId()),
                request.specialistId() == null ? null : new SpecialistId(request.specialistId()),
                new SenderRoleId(request.senderRoleId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ConversationMemberResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationMemberResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ConversationMemberId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationMemberResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateConversationMemberRequest request) {
        var command = new UpdateConversationMemberCommand(
                new ConversationMemberId(id),
                new SupportConversationId(request.conversationId()),
                request.clientId() == null ? null : new ClientId(request.clientId()),
                request.specialistId() == null ? null : new SpecialistId(request.specialistId()),
                new SenderRoleId(request.senderRoleId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ConversationMemberId(id));
        return ResponseEntity.noContent().build();
    }
}
