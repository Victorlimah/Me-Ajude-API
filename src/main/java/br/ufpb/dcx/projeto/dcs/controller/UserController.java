package br.ufpb.dcx.projeto.dcs.controller;

import br.ufpb.dcx.projeto.dcs.db.dto.EmailDTO;
import br.ufpb.dcx.projeto.dcs.db.dto.NameDTO;
import br.ufpb.dcx.projeto.dcs.db.entity.User;
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

  @PatchMapping("/update/email")
  ResponseEntity<User> updateUserEmail(@RequestHeader("Authorization") String token, @RequestBody EmailDTO newEmail) {
    return new ResponseEntity<>(UserService.updateEmail(token, newEmail), HttpStatus.OK);
  }

  @PatchMapping("/update/name")
  ResponseEntity<User> updateUserName(@RequestHeader("Authorization") String token, @RequestBody NameDTO newName) {
    return new ResponseEntity<>(UserService.updateName(token, newName), HttpStatus.OK);
  }

  @DeleteMapping
  ResponseEntity<User> deleteUser(@RequestHeader("Authorization") String token, @RequestBody EmailDTO email) {
    return new ResponseEntity<>(UserService.delete(token, email.getEmail()), HttpStatus.OK);
  }

  @PatchMapping("/active")
  ResponseEntity<User> active(@RequestHeader("Authorization") String token, @RequestBody EmailDTO emailDTO) {
    return new ResponseEntity<>(UserService.reactive(token, emailDTO.getEmail()), HttpStatus.OK);
  }

}
