package com.gokul.busbooking.Security;

import com.gokul.busbooking.entity.User;
import com.gokul.busbooking.jwt.JwtUtil;
import com.gokul.busbooking.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public OAuth2LoginSuccessHandler(
            UserRepository userRepository,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oauthUser =
                (OAuth2User) authentication.getPrincipal();

        String email =
                oauthUser.getAttribute("email");

        Optional<User> existingUser =
                userRepository.findByEmail(email);

        User dbUser;

        if (existingUser.isPresent()) {

            dbUser = existingUser.get();

        } else {

            dbUser = new User();

            dbUser.setEmail(email);

            // ADMIN Gmail
            if (email.equalsIgnoreCase(
                    "gokulrajofficial123@gmail.com")) {

                dbUser.setRole("ADMIN");

            } else {

                dbUser.setRole("USER");
            }
        }

        // Generate JWT Token
        String token =
                jwtUtil.generateToken(email);

        dbUser.setToken(token);

        userRepository.save(dbUser);

        // Redirect based on Role
        if (dbUser.getRole().equals("ADMIN")) {

            response.sendRedirect(
                    "http://localhost:8080/admin.html?token="
                            + token
            );

        } else {

            response.sendRedirect(
                    "http://localhost:8080/user.html?token="
                            + token
            );
        }
    }
}