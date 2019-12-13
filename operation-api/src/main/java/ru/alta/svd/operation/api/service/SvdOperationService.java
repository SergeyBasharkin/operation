package ru.alta.svd.operation.api.service;

import ru.alta.svd.operation.api.exception.SvdOperationException;

public interface SvdOperationService {
    /**
     * Проверка на существование операции
     *
     * @param uuid уникальный ID операции
     * @param method название метода
     * @return True если операция не выполнялась, False - в противном случае
     */

    Boolean isNew(String uuid, String method);


    /**
     * Успешное завершение операции.
     *
     * @param uuid уникальный ID операции
     * @param method название метода
     */

    void commit(String uuid, String method) throws SvdOperationException;


    /**
     * Успешное завершение операции.
     *
     * @param uuid            уникальный ID операции
     * @param operationResult результат выполнения операции
     * @param method название метода
     */

    void commit(String uuid, Object operationResult, String method) throws SvdOperationException;


    /**
     * Получение результата операции.
     *
     * @param uuid уникальный ID операции
     * @param method название метода
     * @return результат операции
     */

    String getOperationResult(String uuid, String method) throws SvdOperationException;
    
}
