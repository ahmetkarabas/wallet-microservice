package com.ahmet.wallet.exception;

public class TransactionConflictException extends RuntimeException {

    private static final long serialVersionUID = -7131266248952296430L;

    public TransactionConflictException(String exception) {
    	super(exception);
    }
}
