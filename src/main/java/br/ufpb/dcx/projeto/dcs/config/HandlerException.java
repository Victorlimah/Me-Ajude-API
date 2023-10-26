package br.ufpb.dcx.projeto.dcs.config;

import br.ufpb.dcx.projeto.dcs.config.AppException;
import br.ufpb.dcx.projeto.dcs.db.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDTO>  handleAppException(AppException e) {
        ErrorDTO error = ErrorDTO.builder()
                .status(e.getStatus().value())
                .title(e.getTitle())
                .message(e.getMessage())
                .timestamp(String.valueOf(System.currentTimeMillis()))
                .build();
        return new  ResponseEntity<>(error, e.getStatus());
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorDTO> handleSecurityException(SecurityException e) {
        ErrorDTO error = ErrorDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .title("Token invalido, inexistente ou expirado")
                .message(e.getMessage())
                .timestamp(String.valueOf(System.currentTimeMillis()))
                .build();
        return new  ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}