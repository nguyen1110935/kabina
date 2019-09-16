package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Shelf;
@Repository
public interface ShelfRepository extends CrudRepository<Shelf, String>, JpaRepository<Shelf, String> {
	List<Shelf> findAll();
	
}
