package com.carelink.infrastructure.biologicalsex.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.biologicalsex.command.RegisterBiologicalSexCommand;
import com.carelink.application.biologicalsex.command.UpdateBiologicalSexCommand;
import com.carelink.application.biologicalsex.dto.BiologicalSexResponse;
import com.carelink.application.biologicalsex.usecase.DeleteBiologicalSexUseCase;
import com.carelink.application.biologicalsex.usecase.GetBiologicalSexByIdUseCase;
import com.carelink.application.biologicalsex.usecase.ListBiologicalSexUseCase;
import com.carelink.application.biologicalsex.usecase.RegisterBiologicalSexUseCase;
import com.carelink.application.biologicalsex.usecase.UpdateBiologicalSexUseCase;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.infrastructure.biologicalsex.adapters.in.rest.dtos.CreateBiologicalSexRequest;
import com.carelink.infrastructure.biologicalsex.adapters.in.rest.dtos.UpdateBiologicalSexRequest;
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
@RequestMapping("/api/biological-sexes")
public class BiologicalSexController {
    private final RegisterBiologicalSexUseCase registerUseCase;
    private final GetBiologicalSexByIdUseCase getByIdUseCase;
    private final ListBiologicalSexUseCase listUseCase;
    private final UpdateBiologicalSexUseCase updateUseCase;
    private final DeleteBiologicalSexUseCase deleteUseCase;

    public BiologicalSexController(RegisterBiologicalSexUseCase registerUseCase,
            GetBiologicalSexByIdUseCase getByIdUseCase, ListBiologicalSexUseCase listUseCase,
            UpdateBiologicalSexUseCase updateUseCase, DeleteBiologicalSexUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<BiologicalSexResponse> create(@Valid @RequestBody CreateBiologicalSexRequest request) {
        var command = new RegisterBiologicalSexCommand(
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<BiologicalSexResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BiologicalSexResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new BiologicalSexId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BiologicalSexResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateBiologicalSexRequest request) {
        var command = new UpdateBiologicalSexCommand(
                new BiologicalSexId(id),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new BiologicalSexId(id));
        return ResponseEntity.noContent().build();
    }
}
