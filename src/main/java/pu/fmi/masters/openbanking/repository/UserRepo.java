package pu.fmi.masters.openbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.fmi.masters.openbanking.bean.UserBean;

/**
 * This interface provides methods to retrieve data from user table.
 */
@Repository
public interface UserRepo extends JpaRepository<UserBean, Integer> {

	/**
	 * This method retrieves a {@link UserBean} by its username and password.
	 * 
	 * @param username - username to match.
	 * @param password - password to match.
	 * @return - {@link UserBean} with matching username and password.
	 */
	UserBean findUserByUsernameAndPassword(String username, String password);

	/**
	 * This method retrieve a {@link UserBean} by its username.
	 * 
	 * @param username - username to match.
	 * @return - {@link UserBean} with matching username.
	 */
	UserBean findUserByUsername(String username);

}
