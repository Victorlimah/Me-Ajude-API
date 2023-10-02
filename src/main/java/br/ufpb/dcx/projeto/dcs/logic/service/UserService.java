package br.ufpb.dcx.projeto.dcs.logic.service;

import br.ufpb.dcx.projeto.dcs.config.exception.AppException;
import br.ufpb.dcx.projeto.dcs.db.dto.LoginDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import br.ufpb.dcx.projeto.dcs.db.enums.ErrorTypes;
import br.ufpb.dcx.projeto.dcs.db.response.LoginResponse;
import br.ufpb.dcx.projeto.dcs.logic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    public LoginResponse login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new AppException(ErrorTypes.NOT_FOUND, "No exist user with this email"));

        if (!user.getPassword().equals(loginDTO.getSenha())) {
            throw new AppException(ErrorTypes.UNAUTHORIZED, "Credentials invalid");
        }

        String token = jwtService.generateToken(user);
        return LoginResponse.builder().token(token).build();
    }


}
