package br.ufpb.dcx.projeto.dcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufpb.dcx.projeto.dcs.db.dto.LoginDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.UserDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import br.ufpb.dcx.projeto.dcs.db.response.LoginResponse;
import br.ufpb.dcx.projeto.dcs.logic.service.UserService;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    UserService UserService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
    return new ResponseEntity<>(UserService.login(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserDTO signUpDTO) {
    return new ResponseEntity<>(UserService.signUp(signUpDTO), HttpStatus.CREATED);
    }

}
