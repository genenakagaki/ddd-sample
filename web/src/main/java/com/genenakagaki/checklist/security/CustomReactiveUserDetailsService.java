package com.genenakagaki.checklist.security;

import com.genenakagaki.checklist.query.user.UserAuthenticationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@RequiredArgsConstructor
public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationQueryService userAuthenticationQueryService;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.defer(() -> {
            return userAuthenticationQueryService.find(username)
                    .map(u -> Mono.<UserDetails>just(new CustomUserDetails(u)))
                    .orElse(Mono.empty());
        }).subscribeOn(Schedulers.boundedElastic());


    }
}
