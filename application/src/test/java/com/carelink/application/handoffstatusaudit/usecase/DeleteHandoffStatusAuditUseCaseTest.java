package com.carelink.application.handoffstatusaudit.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoffstatusaudit.exception.HandoffStatusAuditNotFoundApplicationException;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditDeletedEvent;
import com.carelink.domain.handoffstatusaudit.model.aggregate.HandoffStatusAudit;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import org.junit.jupiter.api.Test;

class DeleteHandoffStatusAuditUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        HandoffStatusAudit aggregate = HandoffStatusAudit.register(
                HandoffId.generate(),
                HandoffStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffStatusAuditDeletedEvent event = new DeleteHandoffStatusAuditUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffStatusAuditNotFoundApplicationException.class,
                () -> new DeleteHandoffStatusAuditUseCase(repository).execute(HandoffStatusAuditId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements HandoffStatusAuditRepository {
        private HandoffStatusAudit aggregate;
        private HandoffStatusAudit deletedAggregate;

        private FakeRepository(HandoffStatusAudit aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public HandoffStatusAudit save(HandoffStatusAudit value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<HandoffStatusAudit> findById(HandoffStatusAuditId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<HandoffStatusAudit> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(HandoffStatusAudit value) {
            deletedAggregate = value;
        }

        private HandoffStatusAudit deletedAggregate() {
            return deletedAggregate;
        }
    }
}
