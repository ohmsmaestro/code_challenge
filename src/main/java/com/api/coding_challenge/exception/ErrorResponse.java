package com.api.coding_challenge.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorResponse {

    private String status;
    private String message;
    private String info;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> errors = new HashMap<>();

    public void addError(String field, String message){
        errors.put(field, message);
    }

}
