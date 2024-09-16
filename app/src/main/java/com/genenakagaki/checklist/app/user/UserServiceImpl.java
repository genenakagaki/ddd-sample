package com.genenakagaki.checklist.app.user;

import com.genenakagaki.checklist.domain.UserEntity;
import com.genenakagaki.checklist.domain.user.error.DuplicateUsernameException;
import com.genenakagaki.checklist.domain.user.Password;
import com.genenakagaki.checklist.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void register(Username username, Password password, Function<String, String> encodePassword) throws DuplicateUsernameException {
        UserEntity entity = UserEntity.register(username, password, encodePassword);
        userRepository.save(entity.data());
    }
}
