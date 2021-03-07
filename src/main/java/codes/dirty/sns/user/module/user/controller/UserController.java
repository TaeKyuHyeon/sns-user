package codes.dirty.sns.user.module.user.controller;

import codes.dirty.sns.user.module.user.model.User;
import codes.dirty.sns.user.module.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Iterable<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<User> postUser(@RequestBody final User user) {
        final User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
