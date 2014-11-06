package timeoff;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
	List<User> findByEmpGroup(String empGroup);
	User findByHandle(String handle);
}