package ru.alta.svd.operation.impl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.alta.svd.operation.api.exception.SvdOperationException;
import ru.alta.svd.operation.api.service.SvdOperationService;
import ru.alta.svd.operation.impl.entity.OperationEntity;
import ru.alta.svd.operation.impl.jpa.OperationRepository;

import java.util.Optional;

@Service
public class SvdOperationServiceImpl implements SvdOperationService {

    private final OperationRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SvdOperationServiceImpl(OperationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean isNew(String uuid, String method) {
        return !repository.findByUuidAndMethod(uuid, method).isPresent();
    }

    @Override
    public void commit(String uuid, String method) {
        OperationEntity entity = new OperationEntity();
        entity.setUuid(uuid);
        entity.setMethod(method);

        repository.save(entity);
    }

    @Override
    public void commit(String uuid, Object operationResult, String method) throws SvdOperationException {
        OperationEntity entity = new OperationEntity();
        entity.setUuid(uuid);
        entity.setMethod(method);
        try {
            entity.setReturnValue(objectMapper.writeValueAsString(operationResult));
        } catch (JsonProcessingException e) {
            throw new SvdOperationException(e);
        }

        repository.save(entity);
    }

    @Override
    public String getOperationResult(String uuid, String method) throws SvdOperationException {
        Optional<OperationEntity> operation = repository.findByUuidAndMethod(uuid, method);
        if (!operation.isPresent()) throw new SvdOperationException("Can not find operation by uuid:" + uuid);
        return operation.get().getReturnValue();
    }
}
