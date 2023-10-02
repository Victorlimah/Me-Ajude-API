package br.ufpb.dcx.projeto.dcs.db.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private int status;
    private String title;
    private String message;
    private String timestamp;

    public ErrorDTO(int status, String title, String message, String timestamp) {
        super();
        this.status = status;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
    }
}