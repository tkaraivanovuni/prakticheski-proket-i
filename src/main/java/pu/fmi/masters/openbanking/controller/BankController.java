package pu.fmi.masters.openbanking.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pu.fmi.masters.openbanking.bean.BankBean;
import pu.fmi.masters.openbanking.bean.UserBean;
import pu.fmi.masters.openbanking.repository.BankRepo;

/**
 * This class handles viewing and adding {@link BankBean} objects.
 */
@RestController
public class BankController {

	BankRepo bankRepo;

	public BankController(BankRepo bankRepo) {
		this.bankRepo = bankRepo;
	}

	/**
	 * This class handles adding {@link BankBean} objects.
	 * 
	 * @param bankName  - name of the bank.
	 * @param apiKey    - application key.
	 * @param secret    - application secret.
	 * @param apiLink   - bank API url.
	 * @param authLink  - bank authentication url.
	 * @param tokenLink - bank token url.
	 * @param session   - current session.
	 * @return - string representing the outcome.
	 */
	@PostMapping(path = "/bank")
	@Secured(value = { "ROLE_ADMIN" })
	public String addTransaction(@RequestParam(value = "bank_name") String bankName,
			@RequestParam(value = "api_key") String apiKey, @RequestParam(value = "secret") String secret,
			@RequestParam(value = "api_link") String apiLink, @RequestParam(value = "auth_link") String authLink,
			@RequestParam(value = "token_link") String tokenLink, HttpSession session) {

		UserBean user = (UserBean) session.getAttribute("user");

		if (user != null) {

			BankBean bank = new BankBean();
			bank.setBankName(bankName);
			bank.setApiKey(apiKey);
			bank.setSecret(secret);
			bank.setApiLink(apiLink);
			bank.setAuthLink(authLink);
			bank.setTokenLink(tokenLink);

			bank = bankRepo.saveAndFlush(bank);

			if (bank != null) {
				return String.valueOf(bank.getId());
			} else {
				return "Error: adding bank failed";
			}
		} else {
			return "Error: not logged in";
		}

	}
	
	/**
	 * This method handles viewing {@link BankBean} objects.
	 * 
	 * @return - list of all banks.
	 */
	@GetMapping(path = "/bank")
	public List<BankBean> getAllTransactions() {
		return bankRepo.findAll();
	}

}
