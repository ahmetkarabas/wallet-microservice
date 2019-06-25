package com.ahmet.wallet.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ahmet.wallet.constant.WalletConstant;

@ControllerAdvice
public class WalletResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
		  
		  BindingResult result = ex.getBindingResult();
		  
		  StringBuilder errorMessage = new StringBuilder();
		  errorMessage.append("Validation Failed.");
		  if(result.getFieldError().getField().equals(WalletConstant.EXT_TRANS_ID)) {
			  errorMessage.append(" External Transaction Id is not Valid");
		  }else {
			  errorMessage.append(" Please send all required fields");
		  }
		  
		  ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), errorMessage.toString(),
				  request.getDescription(false));
		  
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	  } 

	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleAccountNotFoundException(AccountNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PlayerNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handlePlayerNotFoundException(PlayerNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InSufficientBalanceException.class)
	public final ResponseEntity<ExceptionResponse> handleInSufficientBalanceException(InSufficientBalanceException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(TransactionConflictException.class)
	public final ResponseEntity<ExceptionResponse> handleTransactionConflictException(TransactionConflictException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	}
}