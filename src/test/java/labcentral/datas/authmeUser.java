package labcentral.datas;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("authme")
@Data
public class authmeUser {
    @Id
    private int id;
    private String username;
    private String realname;
}
