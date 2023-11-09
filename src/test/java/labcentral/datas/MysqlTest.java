package labcentral.datas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@SpringBootTest
public class MysqlTest {
    private final UserRepository repository;
    @Autowired
    public MysqlTest(UserRepository repository) {
        this.repository = repository;
    }



    @Test
    void printName() {

        User user = repository.findById(2L).get();
        user.setName("test003");
        repository.save(user);
        System.out.println(user.getName());


    }

    @Test
    void testFind(){
        User user = repository.findByNameOrPassword(null, "密码");
        System.out.println(user.getName());

        System.out.println(repository.findByPassword("密码").get(0).getName());
    }
}
