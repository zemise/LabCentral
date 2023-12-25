package io.github.labcentral.domain.custom.service;

import io.github.labcentral.domain.custom.repository.DaoPrimaryAntibody;
import io.github.labcentral.domain.custom.domain.PrimaryAntibody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/11/9
 * @since 1.0
 */

@Service
public class MyService {
    private final DaoPrimaryAntibody daoPrimaryAntibody;
    @Autowired
    public MyService(DaoPrimaryAntibody daoPrimaryAntibody) {
        this.daoPrimaryAntibody = daoPrimaryAntibody;
    }

    public PrimaryAntibody findByAntiBodyName(String name){
        return daoPrimaryAntibody.findByName(name);
    }
}
