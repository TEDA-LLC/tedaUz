package com.example.tedabot.api.dto;

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
}
