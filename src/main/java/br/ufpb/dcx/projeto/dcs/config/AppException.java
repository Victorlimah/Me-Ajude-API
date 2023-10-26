package br.ufpb.dcx.projeto.dcs.config;

import br.ufpb.dcx.projeto.dcs.db.enums.ErrorTypes;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private String message;
    private String title;
    private HttpStatus status;

    public AppException(ErrorTypes errorType, String message) {
        ErrorTypes error = ErrorTypes.getErrorType(errorType);
        this.message = message;
        this.title = error.getTitle();
        this.status = error.getStatus();
    }
}