package com.cats.mooncell.views.signup;

import com.cats.mooncell.data.User;
import com.cats.mooncell.data.UserRepository; // import UserRepository from com.cats.mooncell.data
import com.cats.mooncell.security.AuthenticatedUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.RouteUtil;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.security.crypto.password.PasswordEncoder;

@AnonymousAllowed
@PageTitle("Sign Up")
@Route(value = "signup")
public class SignUpView extends LoginOverlay implements BeforeEnterObserver {
    private final AuthenticatedUser authenticatedUser;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpView(AuthenticatedUser authenticatedUser, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticatedUser = authenticatedUser;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        setAction(RouteUtil.getRoutePath(VaadinService.getCurrent().getContext(), getClass()));

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

        // Validate the input values
        if (username.isEmpty() || password.isEmpty()) {
            Notification.show("Please fill in all fields");
        } else {
            try {
                userRepository.findByUsername(username);
                Notification.show("Username already exists");
                return;
            } catch (Exception e) {
                // Ignore
            }

            // Create a new user and save it to the database
            User user = new User();
            user.setUsername(username);
            user.setHashedPassword(passwordEncoder.encode(password));
            userRepository.save(user);

            Notification.show("User created successfully");
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