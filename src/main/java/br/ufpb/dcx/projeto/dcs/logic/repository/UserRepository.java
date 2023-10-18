package br.ufpb.dcx.projeto.dcs.logic.repository;

import br.ufpb.dcx.projeto.dcs.db.dto.UserDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

}
