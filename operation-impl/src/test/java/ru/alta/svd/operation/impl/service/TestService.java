package ru.alta.svd.operation.impl.service;

import org.springframework.stereotype.Service;
import ru.alta.svd.operation.impl.aspect.SvdOperation;
import ru.alta.svd.operation.impl.entity.OperationEntity;

@Service
public class TestService {

    public static boolean exec = false;

    @SvdOperation
    public String test(String uuid){
        TestService.exec = true;
        return "test";
    }

    @SvdOperation
    public String test(String uuid, String test){
        TestService.exec = true;
        return test;
    }


    @SvdOperation
    public void testVoid(String uuid){
        TestService.exec = true;
    }

    @SvdOperation
    public byte[] testByte(String uuid){
        TestService.exec = true;
        return "hello".getBytes();
    }

    @SvdOperation
    public OperationEntity testEntity(String uuid){
        TestService.exec = true;
        OperationEntity entity = new OperationEntity();
        entity.setUuid("test server");
        return entity;
    }
}
