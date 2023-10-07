package labcentral.datas;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<authmeUser, Integer> {
}
