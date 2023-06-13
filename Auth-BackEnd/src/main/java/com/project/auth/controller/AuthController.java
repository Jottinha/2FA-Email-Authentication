package com.project.auth.controller;

import com.project.auth.model.User;
import com.project.auth.model.UserFactory;
import com.project.auth.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
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
    UsuarioRepository usuario;
    @ApiOperation("Get all users")
    @GetMapping("/usuarios")
    @ApiResponses({
            @ApiResponse(code = 200, message = "There is no user"),
            @ApiResponse(code = 404, message = "There are users")
    })
    public ResponseEntity<List<User>> getAllUsuarios() {
        List<User> user = usuario.findAll();
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (User userToLink : user){
            long id = userToLink.getId();
            userToLink.add(linkTo(methodOn(AuthController.class).getUsuario(id)).withSelfRel());
        }
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
    @ApiOperation("Get users by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users successfully found"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<User> getUsuario(@PathVariable(value = "id") long id){
        Optional<User> usuarioId = usuario.findById(id);
        if (!usuarioId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioId.get().add(linkTo(methodOn(AuthController.class).getAllUsuarios()).withRel("usuarios"));
        return new ResponseEntity<User>(usuarioId.get(), HttpStatus.OK);
    }

    @ApiOperation("Create a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User create successfully"),
            @ApiResponse(code = 404, message = "User was not created successfully")
    })
    @PostMapping("/usuarios/salvar")
    public ResponseEntity<User> postUsuarios(@RequestBody User newUser){

        boolean usuarioExiste = usuario.existsByEmail(newUser.getEmail());

        if (usuarioExiste){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User userSave = new UserFactory().userSaveFactory(newUser);
        usuario.save(userSave);

        return new ResponseEntity<User>(userSave , HttpStatus.OK);
    }

}
