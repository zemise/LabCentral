package io.github.labcentral.domain.repository;

import io.github.labcentral.domain.Protein;
import org.springframework.data.repository.CrudRepository;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2023/12/26
 * @since 1.0
 */
public interface DaoProtein extends CrudRepository<Protein, Long> {
    Protein findByName(String name);

    Protein findByNameLikeIgnoreCase(String name);

}
