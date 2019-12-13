package ru.alta.svd.operation.impl.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alta.svd.operation.api.service.SvdOperationService;

@Aspect
@Component
public class OperationAspect {

    private final SvdOperationService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OperationAspect(SvdOperationService service) {
        this.service = service;
    }

    @Transactional
    @Around("@annotation(SvdOperation) && args(uuid,..)")
    public Object operation(ProceedingJoinPoint joinPoint, String uuid) throws Throwable {
        Object result = null;
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        if (service.isNew(uuid, method)) {
            Object proceed = joinPoint.proceed();
            if (proceed == null) {
                service.commit(uuid, method);
            } else {
                service.commit(uuid, proceed, method);
                result = proceed;
            }
        } else {
            String operationResult = service.getOperationResult(uuid, method);
            if (operationResult != null) {
                result = objectMapper.readValue(operationResult, ((MethodSignature) joinPoint.getSignature()).getReturnType());
            }
        }
        return result;
    }
}
