package pu.fmi.masters.openbanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.fmi.masters.openbanking.bean.RoleBean;

/**
 * This interface provides methods to retrieve data from role table.
 */
@Repository
public interface RoleRepo extends JpaRepository<RoleBean, Integer> {

	/**
	 * This method retrieves a {@link RoleBean} by it's id.
	 * 
	 * @param id - searched id;
	 * @return - {@link RoleBean} with matching id.
	 */
	Optional<RoleBean> findById(Integer id);

	/**
	 * This method retrieves a {@link RoleBean} by it's name.
	 * 
	 * @param role - searched role.
	 * @return - {@link RoleBean} with matching role name.
	 */
	RoleBean findByRole(String role);

}
