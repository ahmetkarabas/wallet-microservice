package com.ahmet.wallet.exception;

public class AccountNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7131266248952296430L;

    public AccountNotFoundException(String exception) {
    	super(exception);
    }
}
