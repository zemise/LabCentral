package labcentral.datas.Authme;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class TestNamedQuery {
    @Resource
    EntityManagerFactory emf;

    @Autowired
    private AuthmeRepository repository;

    @Test
    void testNamedQuery() {
        EntityManager em = emf.createEntityManager();
        // according User class define NameQuery
        Query query = em.createNamedQuery("findUserWithUserName");
        query.setParameter("username", "zemise_");
        List<Authme> authmeLists = query.getResultList();

        for (Authme authme : authmeLists) {
            System.out.println("username: " + authme.getUsername() + ",ip: " + authme.getIp());
        }
    }

    @Test
    void testPageQuery() {
        int pageIndex = 0, pageSize = 2;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Authme> pageInfo = repository.findByUsernameLike("zemise_", pageable);
        System.out.println("当前页面" + pageInfo.getNumber()
                + ", 总记录数：" + pageInfo.getTotalElements()
                + ", 总页数：" + pageInfo.getTotalPages()
                + ", 是否有上一页：" + pageInfo.hasPrevious()
                + ", 是否有下一页：" + pageInfo.hasNext());

        for (Authme authme : pageInfo.getContent()) {
            System.out.println("name:" + authme.getUsername() + ", ip:" + authme.getIp());
        }
    }

    @Test
    void testFindFirstByOrderByUsernameAsc(){
        Authme user = repository.findFirstByOrderByUsernameAsc();
        System.out.println(user.getUsername());
    }
}
