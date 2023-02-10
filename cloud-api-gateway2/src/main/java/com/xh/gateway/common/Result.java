package com.xh.gateway.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Data
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public Result() {

    }
    @JsonIgnore
    public boolean isSuccess() {

        return String.valueOf(HttpStatus.OK.value()).equals(this.code);
    }
    public static <T> Result<T> buildSuccessResult() {
        Result<T> result = new Result(HttpStatus.OK.value() + "", HttpStatus.OK.getReasonPhrase());
        return result;
    }
    public static <T> Result<T> buildSuccessResult(T data)  {
        Result<T> result = new Result(HttpStatus.OK.value() + "", HttpStatus.OK.getReasonPhrase());
        result.setData(data);
        return result;
    }

    public static <T> Result buildFailResult() {
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public static Result buildFailResult(String message) {
        Result result = new Result(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", message);
        return result;
    }


    public Result(@NonNull String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(@NonNull String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
