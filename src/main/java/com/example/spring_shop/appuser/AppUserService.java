package com.example.spring_shop.appuser;

import com.example.spring_shop.registration.token.ConfirmationToken;
import com.example.spring_shop.registration.token.ConfirmationTokenRepository;
import com.example.spring_shop.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AppUserService.class);
    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).
                isPresent();

        AppUser existingUser = new AppUser();
        LocalDateTime expires;
        boolean userTokenExpired = false;

        try{
            existingUser = appUserRepository.findAppUserByEmail(appUser.getEmail());
            expires = confirmationTokenRepository.findFirstByAppUserOrderByExpiresAtDesc(existingUser).getExpiresAt();
            if(expires.isBefore(LocalDateTime.now())){
                userTokenExpired = true;
            }
        }catch (NullPointerException e){
            LOGGER.error("no existing user in database");
        }catch (IllegalArgumentException e){
            LOGGER.error("no existing user in database");
        }


        if(userExists && !userTokenExpired) {
            throw new IllegalStateException("email already taken");
        }
        if(!userExists && !userTokenExpired){
            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);
            appUserRepository.save(appUser);

            String token = UUID.randomUUID().toString();

            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    appUser
            );

            confirmationTokenService.saveConfirmationToken(confirmationToken);

            return token;
        }
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                existingUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
