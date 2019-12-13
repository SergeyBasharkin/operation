package ru.alta.svd.operation.impl.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alta.svd.operation.impl.entity.OperationEntity;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<OperationEntity, String> {
    Optional<OperationEntity> findByUuidAndMethod(String uuid, String method);
}
