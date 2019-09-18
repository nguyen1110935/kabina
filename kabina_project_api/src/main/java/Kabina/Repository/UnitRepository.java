package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Unit;

@Repository
public interface UnitRepository extends CrudRepository<Unit, String>, JpaRepository<Unit, String>{

	List<Unit> findAll();
	Unit findByUnitId(long unitId);
}
