package codes.dirty.sns.user.module.user.repository;

import codes.dirty.sns.user.module.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
