package com.example.tedabot.api.exception;

import com.example.tedabot.api.dto.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 15:11 *
 */

public class GlobalException {
    @ExceptionHandler(AccessDeniedException.class)
    public HttpEntity<?> handleError(){
        return  ResponseEntity.
                status(403).
                body(ApiResponse.builder().
                        message("Forbidden").
                        status(403).
                        success(false).
                        build());
    }
}
