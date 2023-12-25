package io.github.labcentral.domain.custom.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/11/9
 * @since 1.0
 */
@Entity
@Data
public class PrimaryAntibody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "mw")
    private Double molecularWeight;

    private String website;

    /**
     * 实体创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date dateCreated = new Date();

    /**
     * 实体修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date dateModified = new Date();

    /**
     * 乐观锁
     */
    private @Version Long version;

    public PrimaryAntibody(String name, Double molecularWeight, String website) {
        this.name = name;
        this.molecularWeight = molecularWeight;
        this.website = website;
    }

    public PrimaryAntibody() {
    }
}
