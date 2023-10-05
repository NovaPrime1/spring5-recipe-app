package guru.springframework.repository;

import guru.springframework.model.Category;
import org.apache.catalina.startup.Catalina;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
