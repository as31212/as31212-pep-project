package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    

    public Account createAccount(Account account){
        // create the account then if account was successfully created then search for account and return it
        
        if(account.username != "" && account.password.length() >= 4 && accountDAO.fetchAccountByUsername(account.username) == null){
            boolean accountCreated = accountDAO.createAccount(account);
            if(accountCreated){
             return accountDAO.login(account);
            }
        }
        return null;
    }

    public Account login(Account account){
        return accountDAO.login(account);
    }
    
}
