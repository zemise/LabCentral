package labcentral.datas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MysqlTest {
    private final UserService userService;

    @Autowired
    public MysqlTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void printName() {
        System.out.println("==========");

        for (int i = 1; i <= 5; i++) {
            System.out.println(userService.getUserById(i).getUsername());
        }
    }
}
