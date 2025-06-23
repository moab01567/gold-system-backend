package com.rizamo.goldStore.features.invoice.invoiceException;

public class FailedToCreateInvoiceException extends RuntimeException {
    public FailedToCreateInvoiceException(String message) {
        super(message);
    }
}
