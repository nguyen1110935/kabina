package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, String>, JpaRepository<Booking, String> {
	List<Booking> findAll();

}
