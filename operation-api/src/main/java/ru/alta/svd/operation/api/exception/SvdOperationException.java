package ru.alta.svd.operation.api.exception;

public class SvdOperationException extends Exception {
    public SvdOperationException(String s) {
        super(s);
    }

    public SvdOperationException(Exception e) {
        super(e);
    }
}
