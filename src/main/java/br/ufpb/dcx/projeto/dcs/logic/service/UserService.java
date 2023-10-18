package br.ufpb.dcx.projeto.dcs.logic.service;

import br.ufpb.dcx.projeto.dcs.config.exception.AppException;
import br.ufpb.dcx.projeto.dcs.db.dto.EmailDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.LoginDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.NameDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.UserDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import br.ufpb.dcx.projeto.dcs.db.enums.ErrorTypes;
import br.ufpb.dcx.projeto.dcs.db.enums.Role;
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
                .orElseThrow(() -> new AppException(ErrorTypes.NOT_FOUND, "Credentials invalid"));

        if (!user.getPassword().equals(loginDTO.getSenha()) || user.getActive().equals(false)) {
            throw new AppException(ErrorTypes.UNAUTHORIZED, "Credentials invalid");
        }

        String token = jwtService.generateToken(user);
        return LoginResponse.builder().token(token).build();
    }

    public Optional<User> checkEmailInUse(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new AppException(ErrorTypes.CONFLICT, "Email already in use");
        }
        return user;
    }
    public User signUp(UserDTO signUpDTO) {
        checkEmailInUse(signUpDTO.getEmail());

        return userRepository.save(User.builder()
            .name(signUpDTO.getName())
            .email(signUpDTO.getEmail())
            .password(signUpDTO.getPassword())
            .role(Role.USER)
            .type(signUpDTO.getType())
            .number(signUpDTO.getNumber())
            .document(signUpDTO.getDocument())
            .active(true)
            .build());
    }

    public User updateEmail(String token, EmailDTO emailDTO) {
        User user = jwtService.validateToken(token);

        user.setName(emailDTO.getEmail());
        userRepository.save(user);
        return user;
    }

    public User updateName(String token, NameDTO nameDTO) {
        User user = jwtService.validateToken(token);

        user.setName(nameDTO.getName());
        userRepository.save(user);
        return user;
    }

    public User delete(String token, String email) {
        User validatedUser = jwtService.validateToken(token);

        User userToBeDeleted = userRepository.findByEmail(email)
            .orElseThrow(() -> new AppException(ErrorTypes.NOT_FOUND, "User not found"));

        if (!validatedUser.getEmail().equals(userToBeDeleted.getEmail()) && !Role.ADMIN.equals(validatedUser.getRole())) {
            throw new AppException(ErrorTypes.UNAUTHORIZED, "Not authorized");
        }

        userToBeDeleted.setActive(false);
        userRepository.save(userToBeDeleted);
        return userToBeDeleted;
    }
}
