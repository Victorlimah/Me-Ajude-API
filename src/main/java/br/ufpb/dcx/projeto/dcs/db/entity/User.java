package br.ufpb.dcx.projeto.dcs.db.entity;

import br.ufpb.dcx.projeto.dcs.db.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
