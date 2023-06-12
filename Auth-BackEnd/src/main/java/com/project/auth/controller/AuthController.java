package com.project.auth.controller;

import com.project.auth.model.Usuario;
import com.project.auth.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> user = usuario.findAll();
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Usuario userToLink : user){
            long id = userToLink.getId();
            userToLink.add(linkTo(methodOn(AuthController.class).getUsuario(id)).withSelfRel());
        }
        return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }
    @ApiOperation("Get users by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users successfully found"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "id") long id){
        Optional<Usuario> usuarioId = usuario.findById(id);
        if (!usuarioId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuarioId.get().add(linkTo(methodOn(AuthController.class).getAllUsuarios()).withRel("usuarios"));
        return new ResponseEntity<Usuario>(usuarioId.get(), HttpStatus.OK);
    }

    @ApiOperation("Create a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User create successfully"),
            @ApiResponse(code = 404, message = "User was not created successfully")
    })
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> postUsuarios(@RequestBody Usuario newUser){

        boolean usuarioExiste = usuario.existsByEmail(newUser.getEmail());

        if (usuarioExiste){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        usuario.save(newUser);
        return new ResponseEntity<Usuario>(newUser , HttpStatus.OK);
    }
    @ApiOperation("Update a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User update successfully"),
            @ApiResponse(code = 404, message = "User was not update successfully")
    })
    @PutMapping("/usuarios")
    public ResponseEntity<Usuario> PutUsuarios(@RequestBody Usuario user){

        Optional<Usuario> existeUsuario = usuario.findById(user.getId());

        if (!existeUsuario.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Usuario atualizarUsuario = existeUsuario.get();
        atualizarUsuario.setEmail(user.getEmail());
        atualizarUsuario.setPassword(user.getPassword());
        atualizarUsuario.setSalt(user.getSalt());

        usuario.save(atualizarUsuario);
        return new ResponseEntity<Usuario>(atualizarUsuario, HttpStatus.OK);
    }
    @ApiOperation("Delete a user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User delete successfully"),
            @ApiResponse(code = 404, message = "User was not delete successfully")
    })
    @DeleteMapping("/usuarios")
    public ResponseEntity<Usuario> deleteUsuarios(@RequestBody Usuario user){
        if (usuario.existsById(user.getId())){
            usuario.delete(user);
            return new ResponseEntity<Usuario>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
