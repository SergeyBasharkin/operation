package ru.alta.svd.operation.impl.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "operation")
@Getter
@Setter
@IdClass(OperationId.class)
@EqualsAndHashCode
public class OperationEntity {

    @Id
    private String uuid;

    @Id
    private String method;

    private String returnValue;

}
