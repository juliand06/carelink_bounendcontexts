package com.carelink.infrastructure.contactemailaddress.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.contactemailaddress.command.RegisterContactEmailAddressCommand;
import com.carelink.application.contactemailaddress.command.UpdateContactEmailAddressCommand;
import com.carelink.application.contactemailaddress.dto.ContactEmailAddressResponse;
import com.carelink.application.contactemailaddress.usecase.DeleteContactEmailAddressUseCase;
import com.carelink.application.contactemailaddress.usecase.GetContactEmailAddressByIdUseCase;
import com.carelink.application.contactemailaddress.usecase.ListContactEmailAddressUseCase;
import com.carelink.application.contactemailaddress.usecase.RegisterContactEmailAddressUseCase;
import com.carelink.application.contactemailaddress.usecase.UpdateContactEmailAddressUseCase;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.contactemailaddress.adapters.in.rest.dtos.CreateContactEmailAddressRequest;
import com.carelink.infrastructure.contactemailaddress.adapters.in.rest.dtos.UpdateContactEmailAddressRequest;
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
@RequestMapping("/api/contact-email-addresses")
public class ContactEmailAddressController {
    private final RegisterContactEmailAddressUseCase registerUseCase;
    private final GetContactEmailAddressByIdUseCase getByIdUseCase;
    private final ListContactEmailAddressUseCase listUseCase;
    private final UpdateContactEmailAddressUseCase updateUseCase;
    private final DeleteContactEmailAddressUseCase deleteUseCase;

    public ContactEmailAddressController(RegisterContactEmailAddressUseCase registerUseCase,
            GetContactEmailAddressByIdUseCase getByIdUseCase, ListContactEmailAddressUseCase listUseCase,
            UpdateContactEmailAddressUseCase updateUseCase, DeleteContactEmailAddressUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ContactEmailAddressResponse> create(@Valid @RequestBody CreateContactEmailAddressRequest request) {
        var command = new RegisterContactEmailAddressCommand(
                new TrustedContactId(request.trustedContactId()),
                request.emailAddress());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ContactEmailAddressResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactEmailAddressResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ContactEmailAddressId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactEmailAddressResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateContactEmailAddressRequest request) {
        var command = new UpdateContactEmailAddressCommand(
                new ContactEmailAddressId(id),
                new TrustedContactId(request.trustedContactId()),
                request.emailAddress());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ContactEmailAddressId(id));
        return ResponseEntity.noContent().build();
    }
}
