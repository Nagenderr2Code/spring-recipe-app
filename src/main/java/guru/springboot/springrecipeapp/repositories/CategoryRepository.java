package guru.springboot.springrecipeapp.repositories;

import guru.springboot.springrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
