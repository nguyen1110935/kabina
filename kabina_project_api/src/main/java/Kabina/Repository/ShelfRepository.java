package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Shelf;

@Repository
public interface ShelfRepository extends CrudRepository<Shelf, String>, JpaRepository<Shelf, String> {
	List<Shelf> findAll();

	//Use for booking, that mean we must check that shelf in booking table and bookkingtemp table too
	@Query(value = "SELECT * FROM shelf where floor = ?1 and shelfId not in "
			+ "((select distinct shelfId from booking where StartDate BETWEEN (SELECT DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) "
			+ "AND (SELECT DATE_ADD(CURDATE(), INTERVAL + 4 - WEEKDAY(CURDATE()) DAY))) "
			+ " Union (select distinct shelfId from bookingtemp))", nativeQuery = true)
	List<Shelf> findFreeShelfOfThisWeek(int floor);

	//Find shelf free in the range user input
	@Query(value = "select * from "
			+ "((select * from booking where StartDate BETWEEN (SELECT DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) \n"
			+ "AND " + "(SELECT DATE_ADD(CURDATE(), INTERVAL + 4 - WEEKDAY(CURDATE()) DAY))) "
			+ "Union(select * from bookingtemp)) as b left join shelf as s on b.shelfId = s.shelfId "
			+ "where b.shelfId not in ( select b.shelfId where b.startDate between ?1 and ?2 or b.endDate between ?1 and ?2) "
			+ "and s.floor= ?3", nativeQuery = true)
	List<Shelf> findUsedShelfOfUserRange(String start, String end, int floor);

	@Query(value = "select s.*, sum((weekday(b.enddate)-weekday(b.startDate)+1)) as sum  from "
			+ "((select * from booking where StartDate BETWEEN (SELECT DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) "
			+ "AND (SELECT DATE_ADD(CURDATE(), INTERVAL + 4 - WEEKDAY(CURDATE()) DAY))) Union(select * from bookingtemp)) as b "
			+ "left join shelf as s on b.shelfId=s.shelfId where floor=?1 " + "group by b.shelfId "
			+ "HAVING sum>=5;", nativeQuery = true)
	List<Shelf> findFullShelfOfThisWeek(int floor);

	//use for export data for Login group, just check in booking table
	@Query(value ="SELECT * FROM shelf where floor = ?1 and shelfId not in "
			+ "(select distinct shelfId from booking where StartDate BETWEEN (SELECT DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) "
			+ "AND (SELECT DATE_ADD(CURDATE(), INTERVAL + 4 - WEEKDAY(CURDATE()) DAY)))", nativeQuery = true)
	List<Shelf> findFreeShelfAprroved(int floor);

}
