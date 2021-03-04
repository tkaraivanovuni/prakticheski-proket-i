package pu.fmi.masters.openbanking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pu.fmi.masters.openbanking.bean.BankBean;

/**
 * This interface provides methods to retrieve data from the bank table.
 */
public interface BankRepo extends JpaRepository<BankBean, Integer> {

	/**
	 * This method returns a list of banks with matching name.
	 * 
	 * @param bankName - string representing searched name.
	 * @return - list with results.
	 */
	List<BankBean> findByBankNameContaining(String bankName);

	/**
	 * This method retrieves a {@link BankBean} by its id.
	 * 
	 * @param id - id to search by.
	 * @return - matching {@link BankBean}.
	 */
	Optional<BankBean> findById(Integer id);

}
