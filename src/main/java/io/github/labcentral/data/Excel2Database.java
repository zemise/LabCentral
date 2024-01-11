package io.github.labcentral.data;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import io.github.labcentral.domain.ExcelProtein;
import io.github.labcentral.domain.Protein;
import io.github.labcentral.domain.repository.DaoProtein;
import io.github.labcentral.util.FileUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 这是一个将自带的protein Excel数据转存到数据库中
 * todo： 目前主要考虑用户使用自己的protein excel表，这个类只是暂时测试用
 * </p>
 *
 * @author <a href= "https://github.com/zemise">Zemise</a>
 * @Date 2023/12/26
 * @since 1.0
 */
@Slf4j
@Controller
public class Excel2Database {
    private final DaoProtein daoProtein;

    @Autowired
    public Excel2Database(DaoProtein daoProtein) {
        this.daoProtein = daoProtein;
    }

    //@PostConstruct
    public void init() {
        String excelFilePath = FileUtil.getPath() + "data" + File.separator + "Proteins.xlsx";
        log.debug("Initializing Excel2Database, excelFilePath: {}", excelFilePath);
        //InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("io/github/labcentral/data/Proteins.xlsx");
        List<Protein> proteins = new ArrayList<>();

        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(excelFilePath, ExcelProtein.class, new PageReadListener<ExcelProtein>(datalist -> {
            log.info("Excel含有{}条蛋白数据，开始初始化数据库！", datalist.size());
            for (ExcelProtein excelProtein : datalist) {
                Protein protein = new Protein();
                protein.setName(excelProtein.getName());
                protein.setMoleculeWeight(excelProtein.getMoleculeWeight());
                proteins.add(protein);
            }
        //})).sheet(0).headRowNumber(0).doReadSync();
        })).sheet(0).doRead();

        daoProtein.saveAll(proteins);
        log.info("自带Protein excel数据已存储到数据库！");
    }
}
