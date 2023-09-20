package labcentral.service;

import labcentral.event.LoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author Zemise
 */
@Service
public class LoginService implements ALabService {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public LoginService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String processName(final String name) {
        if(name.equals("logged")) {
            eventPublisher.publishEvent(new LoginEvent(this, true));
            return "已登录!";
        }
        else {
            eventPublisher.publishEvent(new LoginEvent(this, false));
            return "未登录!";
        }
    }
}
