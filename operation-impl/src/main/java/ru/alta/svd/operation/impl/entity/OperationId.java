package ru.alta.svd.operation.impl.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"uuid", "method"})
public class OperationId implements Serializable {
    private String uuid;

    private String method;
}
