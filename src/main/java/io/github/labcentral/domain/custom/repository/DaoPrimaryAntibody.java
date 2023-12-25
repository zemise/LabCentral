package io.github.labcentral.domain.custom.repository;

import io.github.labcentral.domain.custom.domain.PrimaryAntibody;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/11/9
 * @since 1.0
 */
public interface DaoPrimaryAntibody extends CrudRepository<PrimaryAntibody, Long> {
    PrimaryAntibody findByName(String name);
}
