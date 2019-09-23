package Kabina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {
	User findByUserName(String userName);
	User findByUserId(Long userId);
	void deleteByUserId(Long userId);
}
