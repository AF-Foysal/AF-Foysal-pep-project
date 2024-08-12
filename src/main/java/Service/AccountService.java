package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;


public class AccountService {

    private AccountDAO accountDAO;

    /**
     * Default Constructor
     */
    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * Parametrized Constructor
     * @param messageDAO a given MessageDAO
     */
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    /**
     * @param account to be registered
     * @return Account Object if persists, otherwise null
     */
    public Account registerAccount(Account account){
        return accountDAO.registerAccount(account);
    }

    /**
     * 
     * @param account to be logged onto
     * @return Account Object if successful, otherwise null
     */
    public Account loginAccount(Account account){
        return accountDAO.loginAccount(account);
    }
    
}
