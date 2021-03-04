package pu.fmi.masters.openbanking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pu.fmi.masters.openbanking.bean.BankAccountBean;
import pu.fmi.masters.openbanking.bean.UserBean;

/**
 * This interface provides methods to retrieve data from the bank_account table.
 */
@Repository
public interface BankAccountRepo extends JpaRepository<BankAccountBean, Integer>{
	
	/**
	 * This method retrieves bank accounts belonging to the user.
	 * 
	 * @param user - {@link UserBean} to search by.
	 * @return - a list of all bank accounts of the user.
	 */
	List<BankAccountBean> findByUser(UserBean user);
	
	/**
	 * This method retrieves a bank account by its id.
	 * 
	 * @param id - the id of the bank account.
	 */
	Optional<BankAccountBean> findById(Integer id);

}
