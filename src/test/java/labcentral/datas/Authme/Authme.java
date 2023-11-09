package labcentral.datas.Authme;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "authme")
@NamedQueries({
        @NamedQuery(name="findAllUser", query = "select u from Authme u"),
        @NamedQuery(name="findUserWithId",query = "select u from Authme u where u.id= ?1"),
        @NamedQuery(name="findUserWithUserName", query = "select u from Authme u where u.username = :username")
})
public class Authme {
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Id
    private Long id;

    private String username;

    private String realname;

    private String ip;

    public Authme(String username, String realname, String ip) {
        this.username = username;
        this.realname = realname;
        this.ip = ip;
    }

    public Authme() {

    }
}
