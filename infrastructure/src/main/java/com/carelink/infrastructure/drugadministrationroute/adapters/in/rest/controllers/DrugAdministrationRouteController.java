package com.carelink.infrastructure.drugadministrationroute.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.drugadministrationroute.command.RegisterDrugAdministrationRouteCommand;
import com.carelink.application.drugadministrationroute.command.UpdateDrugAdministrationRouteCommand;
import com.carelink.application.drugadministrationroute.dto.DrugAdministrationRouteResponse;
import com.carelink.application.drugadministrationroute.usecase.DeleteDrugAdministrationRouteUseCase;
import com.carelink.application.drugadministrationroute.usecase.GetDrugAdministrationRouteByIdUseCase;
import com.carelink.application.drugadministrationroute.usecase.ListDrugAdministrationRouteUseCase;
import com.carelink.application.drugadministrationroute.usecase.RegisterDrugAdministrationRouteUseCase;
import com.carelink.application.drugadministrationroute.usecase.UpdateDrugAdministrationRouteUseCase;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;
import com.carelink.infrastructure.drugadministrationroute.adapters.in.rest.dtos.CreateDrugAdministrationRouteRequest;
import com.carelink.infrastructure.drugadministrationroute.adapters.in.rest.dtos.UpdateDrugAdministrationRouteRequest;
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
@RequestMapping("/api/drug-administration-routes")
public class DrugAdministrationRouteController {
    private final RegisterDrugAdministrationRouteUseCase registerUseCase;
    private final GetDrugAdministrationRouteByIdUseCase getByIdUseCase;
    private final ListDrugAdministrationRouteUseCase listUseCase;
    private final UpdateDrugAdministrationRouteUseCase updateUseCase;
    private final DeleteDrugAdministrationRouteUseCase deleteUseCase;

    public DrugAdministrationRouteController(RegisterDrugAdministrationRouteUseCase registerUseCase,
            GetDrugAdministrationRouteByIdUseCase getByIdUseCase, ListDrugAdministrationRouteUseCase listUseCase,
            UpdateDrugAdministrationRouteUseCase updateUseCase, DeleteDrugAdministrationRouteUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DrugAdministrationRouteResponse> create(@Valid @RequestBody CreateDrugAdministrationRouteRequest request) {
        var command = new RegisterDrugAdministrationRouteCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<DrugAdministrationRouteResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugAdministrationRouteResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new DrugAdministrationRouteId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugAdministrationRouteResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateDrugAdministrationRouteRequest request) {
        var command = new UpdateDrugAdministrationRouteCommand(
                new DrugAdministrationRouteId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DrugAdministrationRouteId(id));
        return ResponseEntity.noContent().build();
    }
}
