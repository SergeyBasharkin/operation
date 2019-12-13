package ru.alta.svd.operation.impl;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alta.svd.operation.api.exception.SvdOperationException;
import ru.alta.svd.operation.impl.entity.OperationEntity;
import ru.alta.svd.operation.impl.entity.OperationId;
import ru.alta.svd.operation.impl.jpa.OperationRepository;
import ru.alta.svd.operation.impl.service.SvdOperationServiceImpl;
import ru.alta.svd.operation.impl.service.TestService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationImplApplicationTests {

    @Autowired
    private TestService service;

    @Autowired
    private OperationRepository repository;

    @Autowired
    private SvdOperationServiceImpl svdOperationService;
//
//    @Test
//    public void contextLoads() {
//        System.out.println(service.test("test 1"));
//        System.out.println(service.test("test 2", "Hello"));
//        service.testVoid("test 3");
//        System.out.println(service.testByte("test 4"));
//        System.out.println( service.testEntity("test 5"));
//    }

    @After
    public void destroy(){
        repository.deleteAll();
    }

    @Test
    public void test(){
        assertTrue(svdOperationService.isNew("test 1", "ru.alta.svd.operation.impl.service.TestService.test"));
        String test = service.test("test 1");
        assertTrue(TestService.exec);
        assertEquals(test, "test");
        assertFalse(svdOperationService.isNew("test 1", "ru.alta.svd.operation.impl.service.TestService.test"));
    }

    @Test
    public void testRepeated(){
        test();
        assertTrue(TestService.exec);
        TestService.exec = false;
        assertFalse(svdOperationService.isNew("test 1", "ru.alta.svd.operation.impl.service.TestService.test"));
        String test = service.test("test 1");
        assertFalse(TestService.exec);
        assertEquals(test, "test");
        assertFalse(svdOperationService.isNew("test 1", "ru.alta.svd.operation.impl.service.TestService.test"));
    }

    @Test
    public void testWithParameters(){
        assertTrue(svdOperationService.isNew("test 2", "ru.alta.svd.operation.impl.service.TestService.test"));
        String test = service.test("test 2", "Hello");
        assertTrue(TestService.exec);
        assertEquals(test, "Hello");
        assertFalse(svdOperationService.isNew("test 2", "ru.alta.svd.operation.impl.service.TestService.test"));
    }

    @Test
    public void testWithParametersRepeated(){
        testWithParameters();
        assertTrue(TestService.exec);
        TestService.exec = false;
        assertFalse(svdOperationService.isNew("test 2", "ru.alta.svd.operation.impl.service.TestService.test"));
        String test = service.test("test 2", "Hello");
        assertFalse(TestService.exec);
        assertEquals(test, "Hello");
        assertFalse(svdOperationService.isNew("test 2", "ru.alta.svd.operation.impl.service.TestService.test"));
    }


    @Test
    public void testVoid(){
        assertTrue(svdOperationService.isNew("test 3", "ru.alta.svd.operation.impl.service.TestService.testVoid"));
        service.testVoid("test 3");
        assertTrue(TestService.exec);
        assertFalse(svdOperationService.isNew("test 3", "ru.alta.svd.operation.impl.service.TestService.testVoid"));
    }

    @Test
    public void testVoidRepeated(){
        testVoid();
        assertTrue(TestService.exec);
        TestService.exec = false;
        assertFalse(svdOperationService.isNew("test 3", "ru.alta.svd.operation.impl.service.TestService.testVoid"));
        service.testVoid("test 3");
        assertFalse(TestService.exec);
        assertFalse(svdOperationService.isNew("test 3", "ru.alta.svd.operation.impl.service.TestService.testVoid"));
    }

    @Test
    public void testBytes(){
        assertTrue(svdOperationService.isNew("test 4", "ru.alta.svd.operation.impl.service.TestService.testByte"));
        byte[] bytes = service.testByte("test 4");
        assertEquals(new String(bytes),"hello");
        assertTrue(TestService.exec);
        assertFalse(svdOperationService.isNew("test 4", "ru.alta.svd.operation.impl.service.TestService.testByte"));
    }

    @Test
    public void testBytesRepeated(){
        testBytes();
        assertTrue(TestService.exec);
        TestService.exec = false;
        assertFalse(svdOperationService.isNew("test 4", "ru.alta.svd.operation.impl.service.TestService.testByte"));
        byte[] bytes = service.testByte("test 4");
        assertEquals(new String(bytes),"hello");
        assertFalse(TestService.exec);
        assertFalse(svdOperationService.isNew("test 4", "ru.alta.svd.operation.impl.service.TestService.testByte"));
    }

    @Test
    public void testEntity(){
        assertTrue(svdOperationService.isNew("test 5", "ru.alta.svd.operation.impl.service.TestService.testEntity"));
        OperationEntity entity = service.testEntity("test 5");
        assertEquals("test server", entity.getUuid());
        assertFalse(svdOperationService.isNew("test 5", "ru.alta.svd.operation.impl.service.TestService.testEntity"));
    }

    @Test
    public void testEntityRepeated(){
        testEntity();
        assertTrue(TestService.exec);
        TestService.exec = false;
        assertFalse(svdOperationService.isNew("test 5", "ru.alta.svd.operation.impl.service.TestService.testEntity"));
        OperationEntity entity = service.testEntity("test 5");
        assertEquals("test server", entity.getUuid());
        assertFalse(TestService.exec);
        assertFalse(svdOperationService.isNew("test 5", "ru.alta.svd.operation.impl.service.TestService.testEntity"));
    }

    @Test
    public void testEq(){
        OperationEntity e1 = new OperationEntity();
        e1.setUuid("1");

        OperationEntity e2 = new OperationEntity();
        e2.setUuid("1");
        assertEquals(e1, e2);
    }

    @Test
    public void testEqId(){
        OperationId e1 = new OperationId();
        e1.setUuid("1");
        e1.setMethod("test");

        OperationId e2 = new OperationId();
        e2.setUuid("1");
        e2.setMethod("test");
        assertEquals(e1.getUuid(), e2.getUuid());
        assertEquals(e1.getMethod(), e2.getMethod());
    }

    @Test
    public void testException() {
        boolean fail = false;
        try {
            svdOperationService.commit("1", new Object(), "2");
        } catch (SvdOperationException e){
            fail = true;
        }
        assertTrue(fail);
    }
}
