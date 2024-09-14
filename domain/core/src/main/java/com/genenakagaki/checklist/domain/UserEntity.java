package com.genenakagaki.checklist.domain;

import com.genenakagaki.checklist.domain.user.Password;
import com.genenakagaki.checklist.domain.user.UserId;
import com.genenakagaki.checklist.domain.user.Username;
import lombok.Value;

import java.util.function.Function;

@Value
public class UserEntity {

    Data data;

    public record Data(UserId userId, Username username, String encodedPassword) {
    }

    public static UserEntity register(Username username, Password password, Function<String, String> encodePassword) {
        Data data = new Data(new UserId(Base64UUID.create()), username, encodePassword.apply(password.value()));
        return new UserEntity(data);
    }
}

