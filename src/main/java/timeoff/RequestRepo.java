package timeoff;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepo extends CrudRepository<Request, Long> {
	List<Request> findByUser(String user);
}