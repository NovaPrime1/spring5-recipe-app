package guru.springframework.repository;

import guru.springframework.model.Category;
import org.apache.catalina.startup.Catalina;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);


}
