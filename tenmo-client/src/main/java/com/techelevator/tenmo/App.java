package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class App {

private static final String API_BASE_URL = "http://localhost:8090/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String TRANSFER_OPTIONS_SPECIFIC = "Look up specific transfer";
	private static final String TRANSFER_OPTIONS_ALL = "See all transfer history";
	private static final String[] TRANSFER_OPTIONS = {TRANSFER_OPTIONS_ALL, TRANSFER_OPTIONS_SPECIFIC, MENU_OPTION_EXIT};



    private AuthenticatedUser currentUser;
	private Accounts currentAccount;
    private ConsoleService console;
	private AuthenticationService authenticationService;
	private RestTemplate restTemplate = new RestTemplate();
	private UserService userService;
	private AccountService accountService;
	public TransferService transferService;



    public static void main(String[] args) {
    	App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL), new UserService(API_BASE_URL), new AccountService(API_BASE_URL), new TransferService(API_BASE_URL));
    	app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService, UserService userService, AccountService accountService, TransferService transferService) {
		this.console = console;
		this.authenticationService = authenticationService;
		this.userService = userService;
		this.accountService = accountService;
		this.transferService = transferService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");
		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		// TODO Auto-generated method stud
		for (Accounts account : accountService.getAllAccounts()) {
			if (currentUser.getUser().getId() == account.getUser_id()) {
				System.out.println("Your current balance is: $" + account.getBalance());
			}
		}
	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		while (true){
			for (Accounts account : accountService.getAllAccounts()) {
				if (currentUser.getUser().getId() == account.getUser_id()) {
					currentAccount = account;
				}
			}
			String choice = (String) console.getChoiceFromOptions(TRANSFER_OPTIONS);
			if(TRANSFER_OPTIONS_ALL.equals(choice)){
				for(Transfer transfer : transferService.getAllTransfers()){
					if(currentAccount.getAccount_id() == transfer.getAccountFrom() || currentAccount.getAccount_id() == transfer.getAccountTo()) {
						System.out.println("-------------------------------------------");
						System.out.println("Transfer id      from/to        amount");
						System.out.println("-------------------------------------------");
						System.out.println(transfer.getTransferID() + "         from:" + transfer.getAccountFrom() + "         $" + transfer.getAmount());
						System.out.println(transfer.getTransferID() + "           to:" + transfer.getAccountTo() + "           $" + transfer.getAmount());
					}
				}
			}else exitProgram();

		}
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
		System.out.println("-------------------");
		System.out.println("USERS");
		System.out.println("ID          Name");
		System.out.println("--------------------");
		for (User user : userService.getAllUser()) {
			System.out.println(user.getId() + "          " + user.getUsername());
		}
		System.out.println("--------------------");
		long sendingID = currentUser.getUser().getId();
		long receivingID = console.getUserInputInteger("Enter user id");

		if (sendingID == receivingID) {
			System.out.println("You can not send TE bucks to yourself!");
			mainMenu();
		}

		Integer amount = console.getUserInputInteger("How many TE bucks would you like to send?");

		for (Accounts account : accountService.getAllAccounts()) {
			if (account.getUser_id() == sendingID) {
				Accounts sendingAccount = account;

				if (sendingAccount.getBalance() < amount) {
					System.out.println("\nInsufficient funds");
					break;
				}

				sendingAccount.setBalance(amount * -1);
				accountService.updateBalance(sendingAccount);
			}
			if ((account.getUser_id() == receivingID)) {
				Accounts receivingAccount = account;
				receivingAccount.setBalance(amount);
				accountService.updateBalance(receivingAccount);
			}
		}




	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}
	
	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}
