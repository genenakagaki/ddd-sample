package com.genenakagaki.checklist.controller;

import com.genenakagaki.checklist.app.UserService;
import com.genenakagaki.checklist.controller.error.BadRequestException;
import com.genenakagaki.checklist.domain.user.DuplicateUsernameException;
import com.genenakagaki.checklist.domain.user.Password;
import com.genenakagaki.checklist.domain.user.PasswordLengthException;
import com.genenakagaki.checklist.domain.user.PasswordPatternException;
import com.genenakagaki.checklist.domain.user.Username;
import com.genenakagaki.checklist.domain.user.UsernameLengthException;
import com.genenakagaki.checklist.domain.user.UsernamePatternException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public Mono<String> getUsers() {
        return Mono.just("user");
    }

    @PostMapping("register")
    public Mono<Void> register(@RequestBody @Valid RegisterUserBody body) {
        return Mono.<Void>defer(() -> {
            try {
                userService.register(body.username(), body.password(), (password) -> passwordEncoder.encode(password));
                return Mono.empty();
            } catch (DuplicateUsernameException e) {
                throw new BadRequestException("username", "Username is already taken.");
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Value
    public static class RegisterUserBody {
        @NotBlank(message = "Enter your username")
        String username;
        @NotBlank(message = "Enter your password")
        String password;

        public Username username() {
            try {
                return Username.create(username);
            } catch (UsernameLengthException e) {
                throw new BadRequestException("username",
                        String.format("Username must be between %s and %s characters long.",
                                Username.MIN_LENGTH,
                                Username.MAX_LENGTH));
            } catch (UsernamePatternException e) {
                throw new BadRequestException("username", "Username can only contain letters, numbers, and hyphens.");
            }
        }

        public Password password() {
            try {
                return Password.create(password);
            } catch (PasswordLengthException e) {
                throw new BadRequestException("password",
                        String.format("Password must be between %s and %s characters long.",
                                Password.MIN_LENGTH,
                                Password.MAX_LENGTH));
            } catch (PasswordPatternException e) {
                throw new BadRequestException("password",
                        "Password can only contain letters, numbers, and !@#$%^&*.");
            }
        }
    }
}
