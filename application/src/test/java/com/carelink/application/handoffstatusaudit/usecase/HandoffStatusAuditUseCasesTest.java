package com.carelink.application.handoffstatusaudit.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoffstatusaudit.command.RegisterHandoffStatusAuditCommand;
import com.carelink.application.handoffstatusaudit.command.UpdateHandoffStatusAuditCommand;
import com.carelink.application.handoffstatusaudit.dto.HandoffStatusAuditResponse;
import com.carelink.application.handoffstatusaudit.exception.HandoffStatusAuditNotFoundApplicationException;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditDeletedEvent;
import com.carelink.domain.handoffstatusaudit.model.aggregate.HandoffStatusAudit;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;
import com.carelink.domain.handoffstatusaudit.port.repository.HandoffStatusAuditRepository;
import org.junit.jupiter.api.Test;

class HandoffStatusAuditUseCasesTest {
    private static HandoffStatusAudit sample() {
        return HandoffStatusAudit.register(
                HandoffId.generate(),
                HandoffStatusId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        HandoffStatusAuditResponse response = new RegisterHandoffStatusAuditUseCase(repository).execute(
                new RegisterHandoffStatusAuditCommand(
                HandoffId.generate(),
                    HandoffStatusId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        HandoffStatusAudit aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffStatusAuditResponse response = new GetHandoffStatusAuditByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffStatusAuditNotFoundApplicationException.class,
                () -> new GetHandoffStatusAuditByIdUseCase(repository).execute(HandoffStatusAuditId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListHandoffStatusAuditUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        HandoffStatusAudit aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffStatusAuditResponse response = new UpdateHandoffStatusAuditUseCase(repository).execute(
                new UpdateHandoffStatusAuditCommand(
                aggregate.id(),
                    HandoffId.generate(),
                    HandoffStatusId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        HandoffStatusAudit other = sample();
        assertThrows(HandoffStatusAuditNotFoundApplicationException.class,
                () -> new UpdateHandoffStatusAuditUseCase(repository).execute(
                        new UpdateHandoffStatusAuditCommand(
                        other.id(),
                        HandoffId.generate(),
                        HandoffStatusId.generate())));
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
