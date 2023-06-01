package com.Abdessalam.friendMA.service;

import com.google.firebase.auth.*;
import com.Abdessalam.friendMA.dto.model.UserDto;
import com.Abdessalam.friendMA.enumeration.ERole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final RoleService roleService;

    public String login(String idToken) throws FirebaseAuthException {

        System.out.println("idToken: " + idToken);
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        return decodedToken.getUid();
    }

    public ResponseEntity<String> register(UserDto userDto) throws Exception {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(userDto.getEmail())
                    .setEmailVerified(false)
                    .setPassword(userDto.getPassword())
                    .setDisplayName(userDto.getUsername())
                    .setDisabled(false);


            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            roleService.assignRole(List.of(ERole.ROLE_USER),userRecord.getUid());
            log.info("Successfully created new user: {}", userRecord.getUid());

            return ResponseEntity.ok("Successfully created new user: " + userRecord.getUid()
                    + " with email: " + sendVerificationEmail(userRecord.getEmail()));


        } catch (FirebaseAuthException e) {
            log.error("Error while creating user: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error while creating user: " + e.getMessage());
        }

    }

    public String sendVerificationEmail(String email) {

        ActionCodeSettings actionCodeSettings = ActionCodeSettings.builder()
                .setUrl("https://localhost:8080/")
                .build();

        try {
            // Construct email verification template, embed the link and send
            // using custom SMTP server.
            return FirebaseAuth.getInstance().generateEmailVerificationLink(
                    email, actionCodeSettings);
        } catch (FirebaseAuthException e) {
            log.error("Firebase Auth Error ", e);
            return null;
        }
    }
}
