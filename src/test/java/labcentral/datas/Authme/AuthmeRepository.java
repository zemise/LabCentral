package labcentral.datas.Authme;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthmeRepository extends CrudRepository<Authme, Long> {
    // 分页查询
    Page<Authme> findByUsernameLike(String username, Pageable pageable);

    // 按照username升序排序，然后选第一条
    Authme findFirstByOrderByUsernameAsc();

    // 按照IP降序排序，然后取第一条
    Authme findTopByOrderByIpDesc();

    // 取前10条
    List<Authme> findFirst10ByRealname(String realname, Sort sort);

    //分页，取第N页的前10条
    Page<Authme> queryFirst10ByUsername(String username, Pageable pageable);
}
