package com.rizamo.goldStore.exception;

import com.rizamo.goldStore.features.auth.authException.LoginFailedException;
import com.rizamo.goldStore.features.invoice.invoiceException.FailedToCreateInvoiceException;
import com.rizamo.goldStore.features.user.userException.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice(basePackages = "com.rizamo.goldStore")
public class GlobaleExceptionHandler {


    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<MessageExceptionDTO> handleBadRequest(BadRequest e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FailedToCreateInvoiceException.class)
    public ResponseEntity<MessageExceptionDTO> handleFailedToCreateInvoiceException(FailedToCreateInvoiceException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO("Failed to create invoice / فشل إنشاء الفاتورة"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<MessageExceptionDTO> handleLoginFailedException(LoginFailedException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO("Wrong username or password / خطأ في الدخول"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageExceptionDTO> handleBadCredentialsException(BadCredentialsException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO("Wrong username or password / خطأ في الدخول"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<MessageExceptionDTO> handleSomethingWentWrongException(SomethingWentWrongException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO("Something went wrong. Please contact the developer / حدث خطأ ما. يرجى التواصل مع المطوّر"), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
