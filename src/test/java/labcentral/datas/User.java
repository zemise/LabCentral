package labcentral.datas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(force = true)
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String password;

    private int age;

    private int money;

    public User(String name, String password, int age, int money) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.money = money;
    }
}
