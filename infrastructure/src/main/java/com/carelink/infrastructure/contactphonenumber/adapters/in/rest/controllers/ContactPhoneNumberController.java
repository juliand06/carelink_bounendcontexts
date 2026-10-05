package com.carelink.infrastructure.contactphonenumber.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.contactphonenumber.command.RegisterContactPhoneNumberCommand;
import com.carelink.application.contactphonenumber.command.UpdateContactPhoneNumberCommand;
import com.carelink.application.contactphonenumber.dto.ContactPhoneNumberResponse;
import com.carelink.application.contactphonenumber.usecase.DeleteContactPhoneNumberUseCase;
import com.carelink.application.contactphonenumber.usecase.GetContactPhoneNumberByIdUseCase;
import com.carelink.application.contactphonenumber.usecase.ListContactPhoneNumberUseCase;
import com.carelink.application.contactphonenumber.usecase.RegisterContactPhoneNumberUseCase;
import com.carelink.application.contactphonenumber.usecase.UpdateContactPhoneNumberUseCase;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.contactphonenumber.adapters.in.rest.dtos.CreateContactPhoneNumberRequest;
import com.carelink.infrastructure.contactphonenumber.adapters.in.rest.dtos.UpdateContactPhoneNumberRequest;
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
@RequestMapping("/api/contact-phone-numbers")
public class ContactPhoneNumberController {
    private final RegisterContactPhoneNumberUseCase registerUseCase;
    private final GetContactPhoneNumberByIdUseCase getByIdUseCase;
    private final ListContactPhoneNumberUseCase listUseCase;
    private final UpdateContactPhoneNumberUseCase updateUseCase;
    private final DeleteContactPhoneNumberUseCase deleteUseCase;

    public ContactPhoneNumberController(RegisterContactPhoneNumberUseCase registerUseCase,
            GetContactPhoneNumberByIdUseCase getByIdUseCase, ListContactPhoneNumberUseCase listUseCase,
            UpdateContactPhoneNumberUseCase updateUseCase, DeleteContactPhoneNumberUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ContactPhoneNumberResponse> create(@Valid @RequestBody CreateContactPhoneNumberRequest request) {
        var command = new RegisterContactPhoneNumberCommand(
                new TrustedContactId(request.trustedContactId()),
                request.phoneNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ContactPhoneNumberResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactPhoneNumberResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ContactPhoneNumberId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactPhoneNumberResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateContactPhoneNumberRequest request) {
        var command = new UpdateContactPhoneNumberCommand(
                new ContactPhoneNumberId(id),
                new TrustedContactId(request.trustedContactId()),
                request.phoneNumber());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ContactPhoneNumberId(id));
        return ResponseEntity.noContent().build();
    }
}
