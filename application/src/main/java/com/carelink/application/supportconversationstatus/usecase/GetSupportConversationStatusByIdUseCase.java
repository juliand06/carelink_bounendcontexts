package com.carelink.application.supportconversationstatus.usecase;

import com.carelink.application.supportconversationstatus.dto.SupportConversationStatusResponse;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import com.carelink.application.supportconversationstatus.exception.SupportConversationStatusNotFoundApplicationException;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public class GetSupportConversationStatusByIdUseCase {
    private final SupportConversationStatusRepository repository;

    public GetSupportConversationStatusByIdUseCase(SupportConversationStatusRepository repository) {
        this.repository = repository;
    }

    public SupportConversationStatusResponse execute(SupportConversationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SupportConversationStatusNotFoundApplicationException(id.value().toString()));
        return new SupportConversationStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
