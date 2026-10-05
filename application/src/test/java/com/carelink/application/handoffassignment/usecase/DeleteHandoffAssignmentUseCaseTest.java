package com.carelink.application.handoffassignment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoffassignment.exception.HandoffAssignmentNotFoundApplicationException;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentDeletedEvent;
import com.carelink.domain.handoffassignment.model.aggregate.HandoffAssignment;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteHandoffAssignmentUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        HandoffAssignment aggregate = HandoffAssignment.register(
                HandoffId.generate(),
                SpecialistId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffAssignmentDeletedEvent event = new DeleteHandoffAssignmentUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffAssignmentNotFoundApplicationException.class,
                () -> new DeleteHandoffAssignmentUseCase(repository).execute(HandoffAssignmentId.generate()));
        assertNull(repository.deletedAggregate());
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
