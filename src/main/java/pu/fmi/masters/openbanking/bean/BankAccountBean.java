package pu.fmi.masters.openbanking.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class provides the main characteristics of the {@link BankAccountBean}
 * data model.
 */
@Entity
@Table(name = "bank_account")
public class BankAccountBean implements Serializable {

	private static final long serialVersionUID = 4055215539857229393L;

	@Id
	@Column(name = "bank_account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserBean user;

	@Column(name = "bank_id", nullable = false)
	private BankBean bank;

	@Column(name = "account_number", nullable = false)
	private String accountNumber;

	@Column(name = "currency", nullable = false)
	private String currency;

	/**
	 * No arguments constructor.
	 */
	public BankAccountBean() {
	}

	/**
	 * Required arguments constructor.
	 * 
	 * @param user     - {@link UserBean} holding the account.
	 * @param bank     - {@link BankBean} the account is at.
	 * @param account  number - the account's number
	 * @param currency - the currency of the account.
	 */
	public BankAccountBean(UserBean user, BankBean bank, String accountNumber, String currency) {
		super();
		this.user = user;
		this.bank = bank;
		this.accountNumber = accountNumber;
		this.currency = currency;
	}

	/**
	 * This method returns the account's id.
	 * 
	 * @return - int representing the id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * This method sets the account's id.
	 * 
	 * @param id - new id to be set.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This method returns the user holding the account.
	 * 
	 * @return - {@link UserBean} representing the holder.
	 */
	public UserBean getUser() {
		return user;
	}

	/**
	 * This method sets the user holding the account.
	 * 
	 * @param user - new {@link UserBean} to be set.
	 */
	public void setUser(UserBean user) {
		this.user = user;
	}

	/**
	 * This method returns the bank the account is at.
	 * 
	 * @return - {@link BankBean} representing the bank.
	 */
	public BankBean getBank() {
		return bank;
	}

	/**
	 * This method sets the bank that the account is in.
	 * 
	 * @param bank - {@link BankBean} to be set.
	 */
	public void setBank(BankBean bank) {
		this.bank = bank;
	}

	/**
	 * This method returns the account number.
	 * 
	 * @return - string representing account number.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * This method sets the account number.
	 * 
	 * @param accountNumber - new account number to be set.
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * This method returns currency.
	 * 
	 * @return - string representing account currency.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * This method sets the account currency.
	 * 
	 * @param currency - currency to be set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
