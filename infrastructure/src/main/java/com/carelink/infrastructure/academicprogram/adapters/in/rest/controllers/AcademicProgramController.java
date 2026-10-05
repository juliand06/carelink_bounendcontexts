package com.carelink.infrastructure.academicprogram.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.academicprogram.command.RegisterAcademicProgramCommand;
import com.carelink.application.academicprogram.command.UpdateAcademicProgramCommand;
import com.carelink.application.academicprogram.dto.AcademicProgramResponse;
import com.carelink.application.academicprogram.usecase.DeleteAcademicProgramUseCase;
import com.carelink.application.academicprogram.usecase.GetAcademicProgramByIdUseCase;
import com.carelink.application.academicprogram.usecase.ListAcademicProgramUseCase;
import com.carelink.application.academicprogram.usecase.RegisterAcademicProgramUseCase;
import com.carelink.application.academicprogram.usecase.UpdateAcademicProgramUseCase;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.infrastructure.academicprogram.adapters.in.rest.dtos.CreateAcademicProgramRequest;
import com.carelink.infrastructure.academicprogram.adapters.in.rest.dtos.UpdateAcademicProgramRequest;
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
@RequestMapping("/api/academic-programs")
public class AcademicProgramController {
    private final RegisterAcademicProgramUseCase registerUseCase;
    private final GetAcademicProgramByIdUseCase getByIdUseCase;
    private final ListAcademicProgramUseCase listUseCase;
    private final UpdateAcademicProgramUseCase updateUseCase;
    private final DeleteAcademicProgramUseCase deleteUseCase;

    public AcademicProgramController(RegisterAcademicProgramUseCase registerUseCase,
            GetAcademicProgramByIdUseCase getByIdUseCase, ListAcademicProgramUseCase listUseCase,
            UpdateAcademicProgramUseCase updateUseCase, DeleteAcademicProgramUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AcademicProgramResponse> create(@Valid @RequestBody CreateAcademicProgramRequest request) {
        var command = new RegisterAcademicProgramCommand(
                request.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AcademicProgramResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicProgramResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AcademicProgramId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicProgramResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAcademicProgramRequest request) {
        var command = new UpdateAcademicProgramCommand(
                new AcademicProgramId(id),
                request.title());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AcademicProgramId(id));
        return ResponseEntity.noContent().build();
    }
}
