package com.akerke.wishlist.exception.handler;

import com.akerke.wishlist.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handle(EntityNotFoundException e) {
        return withDetails(e, 404);
    }

    private ProblemDetail withDetails(RuntimeException e, int sc) {
        log.error("created problem details message={} status_code={}", e.getMessage(), sc);
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(sc), e.getMessage());
    }
}
