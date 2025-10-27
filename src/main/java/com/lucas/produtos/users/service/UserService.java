package com.lucas.produtos.users.service;

import com.lucas.produtos.produtos.exception.ExistenteException;
import com.lucas.produtos.users.UsuarioRepository.UserRepository;
import com.lucas.produtos.users.dto.UserDto;
import com.lucas.produtos.users.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserModel save(UserDto dto) {
        if(userRepository.findByEmail(dto.email()).isPresent()) {
            throw new ExistenteException("Usuario ja existe com esse email");
        }

        UserModel user = new UserModel(dto.nome(),dto.email(),dto.senha(),dto.telefone());
        return userRepository.save(user);
    }

    public List<UserModel> listarTodos() {
        return userRepository.findAll();
    }
}
