package com.lucas.produtos.users.controller;

import com.lucas.produtos.produtos.dto.ProdutoDTO;
import com.lucas.produtos.users.dto.UserDto;
import com.lucas.produtos.users.model.UserModel;
import com.lucas.produtos.users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> criar(@RequestBody UserDto userDto) {
        return  ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> listar() {
        return ResponseEntity.ok(userService.listarTodos());
    }

}
