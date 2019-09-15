package Kabina.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, String>, JpaRepository<User, String> {
	User findByUserName(String userName);
	User findByUserId(Integer userId);
	User deleteByUserId(Integer userId);
}
