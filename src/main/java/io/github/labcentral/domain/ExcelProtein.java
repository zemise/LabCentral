package io.github.labcentral.domain;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 与Proteins Excel对应的实体类
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2023/12/26
 * @since 1.0
 */

@Data
@EqualsAndHashCode
public class ExcelProtein {
    private String name;

    @Column(name = "mw")
    private String moleculeWeight;
}
