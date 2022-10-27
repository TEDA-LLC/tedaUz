package com.example.tedabot.api;

import lombok.*;

/**
 * @author * Sunnatullayev Mahmudnazar *  * tedabot *  * 12:28 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ApiResponse<T> {
    private String message;
    private boolean success;
    private int status;
    private T data;

    public ApiResponse(String message, boolean success, int status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
