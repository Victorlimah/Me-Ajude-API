package br.ufpb.dcx.projeto.dcs.db.dto;

import br.ufpb.dcx.projeto.dcs.db.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String number;

    @Column(unique=true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private String document;

    private Role role;

    @JsonIgnore
    private Boolean active = true;
}
