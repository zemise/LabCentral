package io.github.labcentral.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Zemise
 */
public class LoginEvent extends ApplicationEvent {
    private final boolean loggedIn;

    public LoginEvent(Object source, boolean loggedIn) {
        super(source);
        this.loggedIn = loggedIn;
    }

    public  boolean isLoggedIn(){
        return loggedIn;
    }
}
