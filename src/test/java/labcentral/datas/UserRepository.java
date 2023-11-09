package labcentral.datas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByNameOrPassword(String name, String password);

    @Query(value = "select * from users u where u.password = ?1", nativeQuery = true)
    List<User> findByPassword(String password);
}
