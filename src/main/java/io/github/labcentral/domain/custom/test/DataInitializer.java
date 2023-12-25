package io.github.labcentral.domain.custom.test;

import jakarta.annotation.PostConstruct;
import io.github.labcentral.domain.custom.domain.PrimaryAntibody;
import io.github.labcentral.domain.custom.repository.DaoPrimaryAntibody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/11/9
 * @since 1.0
 */
@Component
public class DataInitializer {
    private final DaoPrimaryAntibody daoPrimaryAntibody;

    @Autowired
    public DataInitializer(DaoPrimaryAntibody daoPrimaryAntibody) {
        this.daoPrimaryAntibody = daoPrimaryAntibody;
    }

    @PostConstruct
    public void initializeData() {
        PrimaryAntibody antibody001 = new PrimaryAntibody("GSK", 88.0, "https://www.cellsignal.cn/products/primary-antibodies/gsk-3b-d5c5z-xp-rabbit-mab/12456");
        PrimaryAntibody antibody002 = new PrimaryAntibody("PARP", 116.0, "https://www.cellsignal.cn/products/primary-antibodies/parp-antibody/9542");
        PrimaryAntibody antibody003 = new PrimaryAntibody("GAPDH", 36.0, "https://www.cellsignal.cn/products/primary-antibodies/gapdh-14c10-rabbit-mab/2118");

        List<PrimaryAntibody> list = new ArrayList<>();
        list.add(antibody001);
        list.add(antibody002);
        list.add(antibody003);
        daoPrimaryAntibody.saveAll(list);
    }
}
