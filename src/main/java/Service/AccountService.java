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
        if ((accountDAO.findAccount(account) != null) || (account.getUsername().length() == 0) || (account.getPassword().length() < 4)){
            return null;
        }
        return accountDAO.registerAccount(account);
    }

    /**
     * 
     * @param account to be logged onto
     * @return Account Object if successful, otherwise null
     */
    public Account loginAccount(Account account){
        if (accountDAO.findAccount(account) == null){
            return null;
        }
        return accountDAO.loginAccount(account);
    }

    /**
     * @param account_id ID of the account in question
     * @return true if account exists, otherwise false
     */
    public boolean accountIdExists(int account_id){
        return accountDAO.accountIdExists(account_id);
    }
    
}
