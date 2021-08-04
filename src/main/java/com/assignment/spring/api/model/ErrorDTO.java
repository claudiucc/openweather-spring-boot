
package com.assignment.spring.api.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
    private String message;
    private LocalDateTime timestamp;
}
