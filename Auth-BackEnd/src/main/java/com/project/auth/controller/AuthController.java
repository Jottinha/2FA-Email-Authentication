package com.project.auth.controller;

import com.project.auth.model.PasswordEncryption;
import com.project.auth.model.User;
import com.project.auth.model.UserBuilder;
import com.project.auth.repository.UsuarioRepository;
import com.project.auth.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "users")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class AuthController {
    @Autowired
    UsuarioRepository userData;
    @Autowired
    private EmailService senderService;

    @ApiOperation("Get all users")
    @GetMapping("/users")
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
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @ApiOperation("Get users by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Users successfully found"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") long id){
        Optional<User> userId = userData.findById(id);
        if (!userId.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userId.get().add(linkTo(methodOn(AuthController.class).getAllUsers()).withRel("users"));
        return new ResponseEntity<>(userId.get(), HttpStatus.FOUND);
    }

    @ApiOperation("Check if login exist in Data")
    @PostMapping("/users/login/")
    @SuppressWarnings("unused")
    public boolean checkLogin(@RequestBody User loginUser){
        Optional<User> userFound = userData.findByEmail(loginUser.getEmail());
        if (!userFound.isPresent() || !new PasswordEncryption().checkEncryptedPassword(loginUser.getPassword(), userFound.get().getPassword())){
            return false;
        }
        return true;
    }

    @ApiOperation("Send code verification to email user and receive the code")
    @PostMapping("/users/authentication/")
    @SuppressWarnings("unused")
    public String sendEmailVerification(@RequestBody User userEmail){

        String code = senderService.sendEmail(userEmail.getEmail());
        if (code.equals("")){
            return "";
        }
        return code;
    }

    @ApiOperation("Create a new user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "User create successfully"),
            @ApiResponse(code = 404, message = "User was not created successfully")
    })
    @PostMapping("/users/save")
    @SuppressWarnings("unused")
    public ResponseEntity<User> postUser(@RequestBody User newUser){

        boolean userExist = userData.existsByEmail(newUser.getEmail());

        if (userExist){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User userSave = new UserBuilder().userSaveFactory(newUser);
        userData.save(userSave);

        return new ResponseEntity<>(userSave , HttpStatus.OK);
    }

}
