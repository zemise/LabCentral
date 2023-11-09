//package labcentral.datas;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DepartmentService {
//    private final DepartmentRepository repository;
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public DepartmentService(DepartmentRepository repository, JdbcTemplate jdbcTemplate) {
//        this.repository = repository;
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public Department insertDepartment(Department department){
//        jdbcTemplate.update("insert into department (id, name) values (?, ?)",
//                department.getId(),
//                department.getName());
//
//
//        return department;
//    }
//
//    public Department create(Department department){
//        return repository.save(department);
//    }
//
//    public Department updateDepartment(Department department){
//        return repository.save(department);
//    }
//
//    public Department findByName(String name){
//        return repository.findByName(name);
//    }
//
//    public Department findByDate(String date){
//        return repository.findByDate(date);
//    }
//}
