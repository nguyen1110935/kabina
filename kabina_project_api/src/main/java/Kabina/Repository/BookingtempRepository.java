package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Bookingtemp;

@Repository
public interface BookingtempRepository extends CrudRepository<Bookingtemp, String>, JpaRepository<Bookingtemp, String> {
	List<Bookingtemp> findAll();

}
