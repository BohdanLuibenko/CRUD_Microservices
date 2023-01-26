package Repository;

import org.springframework.data.repository.CrudRepository;

import Entity.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
}
