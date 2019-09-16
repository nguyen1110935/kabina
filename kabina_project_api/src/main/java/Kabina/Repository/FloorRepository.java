package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Kabina.Model.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, String>, JpaRepository<Floor, String> {	
	List<Floor> findAll();


}
