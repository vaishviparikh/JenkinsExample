package com.glosport.utility;

import java.time.LocalDateTime;

public class ErrorInfo {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String details;

    public ErrorInfo(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
