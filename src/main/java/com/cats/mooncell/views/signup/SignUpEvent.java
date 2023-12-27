package com.cats.mooncell.views.signup;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class SignUpEvent extends ComponentEvent<Component> {
    private String username;
    private String password;

    public SignUpEvent(Component source, String username, String password) {
        super(source, false);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}