package com.bank.fcb.user;

import org.springframework.web.bind.annotation.RestController;

import com.bank.fcb.post.Post;
import com.bank.fcb.post.PostRepository;

import jakarta.validation.Valid;

import java.util.List;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserJpaResource {

    private UserDaoService userService;

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserDaoService userService, UserRepository userRepository, PostRepository postRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> get() {
        return userRepository.findAll();
    }


    @GetMapping("/jpa/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserByIdPosts(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get().getPosts();
    }


    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> savePost(@PathVariable Integer id, @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        return ResponseEntity.created(null).build();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.created(null).build();
    }

}
