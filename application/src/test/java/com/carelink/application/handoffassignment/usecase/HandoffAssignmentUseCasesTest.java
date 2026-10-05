package com.carelink.application.handoffassignment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoffassignment.command.RegisterHandoffAssignmentCommand;
import com.carelink.application.handoffassignment.command.UpdateHandoffAssignmentCommand;
import com.carelink.application.handoffassignment.dto.HandoffAssignmentResponse;
import com.carelink.application.handoffassignment.exception.HandoffAssignmentNotFoundApplicationException;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentDeletedEvent;
import com.carelink.domain.handoffassignment.model.aggregate.HandoffAssignment;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class HandoffAssignmentUseCasesTest {
    private static HandoffAssignment sample() {
        return HandoffAssignment.register(
                HandoffId.generate(),
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        HandoffAssignmentResponse response = new RegisterHandoffAssignmentUseCase(repository).execute(
                new RegisterHandoffAssignmentCommand(
                HandoffId.generate(),
                    SpecialistId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        HandoffAssignment aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffAssignmentResponse response = new GetHandoffAssignmentByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffAssignmentNotFoundApplicationException.class,
                () -> new GetHandoffAssignmentByIdUseCase(repository).execute(HandoffAssignmentId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListHandoffAssignmentUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        HandoffAssignment aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffAssignmentResponse response = new UpdateHandoffAssignmentUseCase(repository).execute(
                new UpdateHandoffAssignmentCommand(
                aggregate.id(),
                    HandoffId.generate(),
                    SpecialistId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        HandoffAssignment other = sample();
        assertThrows(HandoffAssignmentNotFoundApplicationException.class,
                () -> new UpdateHandoffAssignmentUseCase(repository).execute(
                        new UpdateHandoffAssignmentCommand(
                        other.id(),
                        HandoffId.generate(),
                        SpecialistId.generate())));
    }

    private static final class FakeRepository implements HandoffAssignmentRepository {
        private HandoffAssignment aggregate;
        private HandoffAssignment deletedAggregate;

        private FakeRepository(HandoffAssignment aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public HandoffAssignment save(HandoffAssignment value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<HandoffAssignment> findById(HandoffAssignmentId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<HandoffAssignment> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(HandoffAssignment value) {
            deletedAggregate = value;
        }

        private HandoffAssignment deletedAggregate() {
            return deletedAggregate;
        }
    }
}
