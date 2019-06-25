package com.ahmet.wallet.exception;

public class InSufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InSufficientBalanceException(String exception) {
        super(exception);
    }

}
