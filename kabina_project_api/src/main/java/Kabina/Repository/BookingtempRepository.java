package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Kabina.Model.Bookingtemp;

@Repository
public interface BookingtempRepository extends CrudRepository<Bookingtemp, String>, JpaRepository<Bookingtemp, String> {
	List<Bookingtemp> findAll();
	
	//get NEW booking unapprove 
	@Query(
			value = "Select * From BookListTemp where BookListId not IN (SELECT BookListId FROM booklist)",
			nativeQuery = true
		)
	List<Bookingtemp> findAllNewBookingUnapprove();
	
	//get EDIT  unapprove 
	@Query(
			value = "Select * From BookListTemp where BookListId IN (SELECT BookListId FROM booklist)",
			nativeQuery = true
		)
	List<Bookingtemp> findAllEditUnapprove();
}
