package com.rodrigolopes.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {

    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      System.out.println("Usuário já existe");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!!!");

    }

    System.out.println("IMPRIMINDO usermodel" + userModel.getName());
    System.out.println("IMPRIMINDO usermodel" + userModel.getUsername());
    System.out.println("IMPRIMINDO usermodel" + userModel.getPassword());
    System.out.println("IMPRIMINDO usermodel" + userModel.getClass());

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
