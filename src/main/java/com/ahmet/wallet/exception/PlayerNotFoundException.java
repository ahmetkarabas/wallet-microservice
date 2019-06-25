package com.ahmet.wallet.exception;

public class PlayerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7131266248952296430L;

    public PlayerNotFoundException(String exception) {
    	super(exception);
    }
}
