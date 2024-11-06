package org.example.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jitendra (Jeetu)
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public GenericResponse(T data) {
        this.success = true;
        this.message = "success";
        this.data = data;
    }

    public GenericResponse(boolean success){
        this.success = success;
    }
}
