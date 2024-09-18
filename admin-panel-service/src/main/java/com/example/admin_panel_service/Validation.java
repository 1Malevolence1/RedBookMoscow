package com.example.admin_panel_service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class Validation extends Throwable {
    public String error;

    public Validation(String error) {
        this.error = error;
    }

    public Validation(String message, String error) {
        super(message);
        this.error = error;
    }

    public Validation(String message, Throwable cause, String error) {
        super(message, cause);
        this.error = error;
    }

    public Validation(Throwable cause, String error) {
        super(cause);
        this.error = error;
    }

    public Validation(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
