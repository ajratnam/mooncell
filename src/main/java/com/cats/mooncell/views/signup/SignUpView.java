package com.cats.mooncell.views.signup;

import com.cats.mooncell.data.User;
import com.cats.mooncell.data.UserRepository;
import com.cats.mooncell.data.UserRole;
import com.cats.mooncell.data.UserRoleRepository;
import com.cats.mooncell.security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@AnonymousAllowed
@PageTitle("Sign Up")
@Route(value = "signup")
public class SignUpView extends LoginOverlay implements BeforeEnterObserver {
    private final AuthenticatedUser authenticatedUser;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpView(AuthenticatedUser authenticatedUser, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.authenticatedUser = authenticatedUser;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Mooncell");
        i18n.getHeader().setDescription("Sign up using your desired username and password");
        i18n.getForm().setTitle("Sign Up");
        i18n.getForm().setUsername("Username");
        i18n.getForm().setPassword("Password");
        i18n.getForm().setSubmit("Sign Up");
        i18n.getForm().setForgotPassword("Already have an account? Login");
        i18n.setAdditionalInformation(null);
        addLoginListener(this::onSignUp);

        setForgotPasswordButtonVisible(true);
        addForgotPasswordListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        setI18n(i18n);
        setOpened(true);
    }

    public void onSignUp(LoginEvent event) {
        String username = event.getUsername();
        String password = event.getPassword();

        if(PasswordValidator.validate(password) == false) {
            Notification.show("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number and one special character");
            setEnabled(true);
            return;
        }

        // Validate the input values
        if (username.isEmpty() || password.isEmpty()) {
            Notification.show("Please fill in all fields");
        } else {
            try {
                userRepository.findByUsername(username);
                Notification.show("Username already exists");
                setEnabled(true);
            } catch (Exception e) {
                User user = new User();
                user.setName(StringUtils.capitalize(username));
                user.setUsername(username);
                user.setHashedPassword(passwordEncoder.encode(password));
                userRepository.save(user);

                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole("USER");
                userRoleRepository.save(userRole);

                Notification.show("User created successfully");
                UI.getCurrent().navigate("login");
            }
        }
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            setOpened(false);
            event.forwardTo("");
        }
    }
}

class PasswordValidator {
    private static final int MIN_LENGTH = 8;

    private static List<Character> createCharacterList(char start, char end) {
        List<Character> list = new ArrayList<>();
        for (char c = start; c <= end; c++) {
            list.add(c);
        }
        return list;
    }

    private static List<Character> createCharacterList(String characters) {
        List<Character> list = new ArrayList<>();
        for (char c : characters.toCharArray()) {
            list.add(c);
        }
        return list;
    }

    private static boolean containsCharacterFromList(String password, List<Character> characterList) {
        for (char c : password.toCharArray()) {
            if (characterList.contains(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validate(String password) {
        if (password.length() < MIN_LENGTH) {
            return false;
        }
        if (!containsCharacterFromList(password, createCharacterList('a', 'z'))) {
            return false;
        }
        if (!containsCharacterFromList(password, createCharacterList('A', 'Z'))) {
            return false;
        }
        if (!containsCharacterFromList(password, createCharacterList('0', '9'))) {
            return false;
        }
        if (!containsCharacterFromList(password, createCharacterList("!@#$%^&*()_+"))) {
            return false;
        }
        return true;
    }
}
