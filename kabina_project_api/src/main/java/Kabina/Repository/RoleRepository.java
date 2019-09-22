package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import Kabina.Model.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, String>, JpaRepository<Role, String> {
	List<Role> findAll();
	Role findByRoleId(long roleId);

}
