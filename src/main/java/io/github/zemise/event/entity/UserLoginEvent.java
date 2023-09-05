package io.github.zemise.event.entity;

import io.github.zemise.event.Event;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @since 2023/7/24
 */
public class UserLoginEvent extends Event {
    private final String message;
    public UserLoginEvent(String message) {
        System.out.println(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
