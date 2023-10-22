package br.ufpb.dcx.projeto.dcs.db.dto;

import br.ufpb.dcx.projeto.dcs.db.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String number;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private String document;

}
