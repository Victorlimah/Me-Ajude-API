package br.ufpb.dcx.projeto.dcs.db.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorTypes {
    NOT_FOUND("NotFound Error", HttpStatus.NOT_FOUND),
    CONFLICT("Conflict Error", HttpStatus.CONFLICT),
    UNPROCESSABLE_ENTITY("UnprocessableEntity Error", HttpStatus.UNPROCESSABLE_ENTITY),
    GENERIC_ERROR("Generic Error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("Unauthorized Error", HttpStatus.UNAUTHORIZED)
    ;

    private String title;
    private HttpStatus status;

    ErrorTypes(String title, HttpStatus status) {
        this.title = title;
        this.status = status;
    }

    public static ErrorTypes getErrorType(String title) {
        for (ErrorTypes errorType : ErrorTypes.values()) {
            if (errorType.getTitle().equals(title)) {
                return errorType;
            }
        }
        return GENERIC_ERROR;
    }

    public static ErrorTypes getErrorType(ErrorTypes errorType) {
        for (ErrorTypes error : ErrorTypes.values()) {
            if (error.getTitle().equals(errorType.getTitle())) {
                return error;
            }
        }
        return GENERIC_ERROR;
    }
}