package io.github.labcentral.service;

import org.springframework.stereotype.Component;

@Component
public class DefaultLabService implements ALabService {
    @Override
    public String processName(final String name) {
        if(name.equals("Anton")) {
            return "Hello Anton!";
        }
        else {
            return "Hello Unknown Stranger!";
        }
    }

}
