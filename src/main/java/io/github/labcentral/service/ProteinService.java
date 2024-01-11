package io.github.labcentral.service;

import io.github.labcentral.domain.Protein;
import io.github.labcentral.domain.repository.DaoProtein;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * todo: 这是一个用于查询蛋白名，并返回蛋白名+分子量字符串的类
 * </p>
 *
 * @author <a href= "https://github.com/zemise">zemise</a>
 * @Date 2023/12/27
 * @since 1.0
 */

@Service
public class ProteinService {
    private  final DaoProtein daoProtein;

    @Autowired
    public ProteinService(DaoProtein daoProtein) {
        this.daoProtein = daoProtein;
    }


    public String getProteinNameMw(String name) {
        Protein protein = daoProtein.findByNameLikeIgnoreCase(name);
        if(null !=protein){
            return protein.getName();
        }
        return null;
    }
}
