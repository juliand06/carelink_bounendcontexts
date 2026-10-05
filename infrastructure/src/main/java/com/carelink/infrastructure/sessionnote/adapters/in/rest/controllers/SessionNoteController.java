package com.carelink.infrastructure.sessionnote.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.sessionnote.command.RegisterSessionNoteCommand;
import com.carelink.application.sessionnote.command.UpdateSessionNoteCommand;
import com.carelink.application.sessionnote.dto.SessionNoteResponse;
import com.carelink.application.sessionnote.usecase.DeleteSessionNoteUseCase;
import com.carelink.application.sessionnote.usecase.GetSessionNoteByIdUseCase;
import com.carelink.application.sessionnote.usecase.ListSessionNoteUseCase;
import com.carelink.application.sessionnote.usecase.RegisterSessionNoteUseCase;
import com.carelink.application.sessionnote.usecase.UpdateSessionNoteUseCase;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.sessionnote.adapters.in.rest.dtos.CreateSessionNoteRequest;
import com.carelink.infrastructure.sessionnote.adapters.in.rest.dtos.UpdateSessionNoteRequest;
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
@RequestMapping("/api/session-notes")
public class SessionNoteController {
    private final RegisterSessionNoteUseCase registerUseCase;
    private final GetSessionNoteByIdUseCase getByIdUseCase;
    private final ListSessionNoteUseCase listUseCase;
    private final UpdateSessionNoteUseCase updateUseCase;
    private final DeleteSessionNoteUseCase deleteUseCase;

    public SessionNoteController(RegisterSessionNoteUseCase registerUseCase,
            GetSessionNoteByIdUseCase getByIdUseCase, ListSessionNoteUseCase listUseCase,
            UpdateSessionNoteUseCase updateUseCase, DeleteSessionNoteUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SessionNoteResponse> create(@Valid @RequestBody CreateSessionNoteRequest request) {
        var command = new RegisterSessionNoteCommand(
                new AppointmentId(request.appointmentId()),
                new SpecialistId(request.specialistId()),
                request.subjective(),
                request.objective(),
                request.assessment(),
                request.plan(),
                request.supplementaryNotes(),
                request.signedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SessionNoteResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionNoteResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SessionNoteId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionNoteResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSessionNoteRequest request) {
        var command = new UpdateSessionNoteCommand(
                new SessionNoteId(id),
                new AppointmentId(request.appointmentId()),
                new SpecialistId(request.specialistId()),
                request.subjective(),
                request.objective(),
                request.assessment(),
                request.plan(),
                request.supplementaryNotes(),
                request.signedAt());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SessionNoteId(id));
        return ResponseEntity.noContent().build();
    }
}
