# Быстрый старт

Импорт настроек
```java
@SpringBootApplication
@Import({SvdOperationServiceConfig.class})
public class TestOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestOperationApplication.class, args);
    }
}
```

Чтобы метод выполнялся внутри транзакции SvdOperation, необходимо поставить над ним
аннотацию `@SvdOperation`, а также метод должен принимать `String uuid` уникальный 
идентификатор транзакции. Если все выполнено верно, то логика внутри метода будет выполнена
один раз на один uuid.
```java
@SvdOperation
public String test(String uuid){
    return test1(uuid);
}
```

# SvdOperation на стороне клиента

Импорт настроек и инъекция генератора uuid.
Для передачи uuid в метод SvdOperation можно использовать `generator.generate()`

```java
@SpringBootApplication
@Import({SvdOperationClientConfig.class})
public class TestOperationApplication {

    @Autowired
    private IdGenerator generator;

    public static void main(String[] args) {
        SpringApplication.run(TestOperationApplication.class, args);
    }
}
```