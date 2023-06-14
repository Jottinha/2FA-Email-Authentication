package com.project.auth.controller;

import com.project.auth.model.PasswordEncryption;
import com.project.auth.model.User;
import com.project.auth.model.UserFactory;
import com.project.auth.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@RestController
@RequestMapping(value = "/api/v1")
@Api(tags = "Usuários")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UsuarioRepository userData;
    @ApiOperation("Get all users")
    @GetMapping("/usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, message = "There is no user"),
            @ApiResponse(code = 404, message = "There are users")
    })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userData.findAll();
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (User userToLink : user){
            long id = userToLink.getId();
            userToLink.add(linkTo(methodOn(AuthController.class).getUser(id)).withSelfRel());
        }
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
    @ApiOperation("Get users by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users successfully found"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") long id){
        Optional<User> usuarioId = userData.findById(id);
        if (!usuarioId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioId.get().add(linkTo(methodOn(AuthController.class).getAllUsers()).withRel("usuarios"));
        return new ResponseEntity<User>(usuarioId.get(), HttpStatus.FOUND);
    }

    @PostMapping("/usuarios/login/")
    public ResponseEntity<String> getUserByEmail(@RequestBody User loginUser){
        Optional<User> userFound = userData.findByEmail(loginUser.getEmail());
        if (!userFound.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!new PasswordEncryption().checkEncryptedPassword(loginUser.getPassword(), userFound.get().getPassword())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //TODO: make a method to send a email code to user
        return new ResponseEntity<>("Email code", HttpStatus.FOUND);
    }

    @ApiOperation("Create a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User create successfully"),
            @ApiResponse(code = 404, message = "User was not created successfully")
    })
    @PostMapping("/usuarios/salvar")
    public ResponseEntity<User> postUser(@RequestBody User newUser){

        boolean usuarioExiste = userData.existsByEmail(newUser.getEmail());

        if (usuarioExiste){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User userSave = new UserFactory().userSaveFactory(newUser);
        userData.save(userSave);

        return new ResponseEntity<User>(userSave , HttpStatus.OK);
    }

}
