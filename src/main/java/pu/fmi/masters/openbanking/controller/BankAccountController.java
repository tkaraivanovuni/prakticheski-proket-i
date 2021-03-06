package pu.fmi.masters.openbanking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pu.fmi.masters.openbanking.bean.BankAccountBean;
import pu.fmi.masters.openbanking.bean.BankBean;
import pu.fmi.masters.openbanking.bean.UserBean;
import pu.fmi.masters.openbanking.repository.BankAccountRepo;
import pu.fmi.masters.openbanking.repository.BankRepo;

/**
 * This class handles viewing and adding {@link BankAccountBean} objects.
 */
@RestController
public class BankAccountController {

	BankAccountRepo bankAccountRepo;
	BankRepo bankRepo;

	/**
	 * Constructor.
	 * 
	 * @param bankAccountRepo - {@link BankAccountRepo} for data management.
	 * @param bankRepo        - {@link BankRepo} for data management.
	 */
	public BankAccountController(BankAccountRepo bankAccountRepo, BankRepo bankRepo) {
		this.bankAccountRepo = bankAccountRepo;
		this.bankRepo = bankRepo;
	}

	/**
	 * This class handles adding new bank accounts.
	 * 
	 * @param bankId        - id of the bank of the account.
	 * @param accountNumber - account number.
	 * @param currency      - currency of the account.
	 * @param session       - the current session.
	 * @return - string representing the outcome.
	 */
	@PostMapping(path = "/bank-account")
	public String addTransaction(@RequestParam(value = "bank_name") String bankName,
			@RequestParam(value = "account_number") String accountNumber,
			@RequestParam(value = "currency") String currency, HttpSession session) {

		UserBean user = (UserBean) session.getAttribute("user");
		BankBean bank = bankRepo.findByBankName(bankName);

		if (user != null && bank != null) {

			BankAccountBean bankAccount = new BankAccountBean();
			bankAccount.setUser(user);
			bankAccount.setBank(bank);
			bankAccount.setAccountNumber(accountNumber);
			bankAccount.setCurrency(currency);

			bankAccount = bankAccountRepo.saveAndFlush(bankAccount);

			if (bankAccount != null) {
				return String.valueOf(bankAccount.getId());
			} else {
				return "Error: adding account failed";
			}
		} else {
			return "Error: not logged in";
		}

	}

	/**
	 * This method handles viewing bank accounts.
	 * 
	 * @param session - current session.
	 * @return - list of {@link BankAccountBean}.
	 */
	@GetMapping(path = "/bank-account")
	public List<BankAccountBean> getBankAccounts(HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("user");
		List<BankAccountBean> all = bankAccountRepo.findAll();
		List<BankAccountBean> results = new ArrayList<>();
		for (BankAccountBean ba : all) {
			if (ba.getUser().getId() == (user.getId())) {
				results.add(ba);
			}
		}
		return results;
	}

}
