package br.ufpb.dcx.projeto.dcs.logic.controller;


import br.ufpb.dcx.projeto.dcs.db.dto.EmailDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.LoginDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.NameDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.UserDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
import br.ufpb.dcx.projeto.dcs.db.response.LoginResponse;
import br.ufpb.dcx.projeto.dcs.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

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

  @PatchMapping("/update/email")
  ResponseEntity<User> updateUserEmail(@RequestHeader String token, @RequestBody EmailDTO newEmail) {
    return new ResponseEntity<>(UserService.updateEmail(token, newEmail), HttpStatus.OK);
  }

  @PatchMapping("/update/name")
  ResponseEntity<User> updateUserName(@RequestHeader String token, @RequestBody NameDTO newName) {
    return new ResponseEntity<>(UserService.updateName(token, newName), HttpStatus.OK);
  }

  @DeleteMapping
  ResponseEntity<User> deleteUser(@RequestHeader String token, @RequestBody EmailDTO email) {
    return new ResponseEntity<>(UserService.delete(token, email.getEmail()), HttpStatus.OK);
  }
}
