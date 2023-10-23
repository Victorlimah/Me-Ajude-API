package br.ufpb.dcx.projeto.dcs.db.entity;

import br.ufpb.dcx.projeto.dcs.db.enums.Role;
import br.ufpb.dcx.projeto.dcs.db.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String number;

    @Column(unique=true)
    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private UserType type;

    private String document;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    private Boolean active = true;
}
