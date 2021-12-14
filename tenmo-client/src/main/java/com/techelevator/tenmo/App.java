package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
		accountService.setAuthToken(currentUser.getToken());
		System.out.println("Your current balance is: $" + accountService.getBalance(currentUser.getUser().getId()));
		}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		Map<Long, String> users = new HashMap<>();
		Map<Long, Long> accounts = new HashMap<>();
		while (true){
			for (Accounts account : accountService.getAllAccounts()) {
				if (currentUser.getUser().getId() == account.getUser_id()) {
					currentAccount = account;
				}
			}
			for (User user: userService.getAllUser()){
				users.put(user.getId(), user.getUsername());
			}
			for (Accounts account : accountService.getAllAccounts()){
				accounts.put(account.getAccount_id(),account.getUser_id());
			}
			String choice = (String) console.getChoiceFromOptions(TRANSFER_OPTIONS);
			if(TRANSFER_OPTIONS_ALL.equals(choice)){

				transferService.setAuthToken(currentUser.getToken());

				System.out.println("-------------------------------------------");
				System.out.println("Transfer id      from/to        amount");
				System.out.println("-------------------------------------------");

				for(Transfer transfer : transferService.getAllTransfers()){
					if (currentAccount.getAccount_id() == transfer.getAccountFrom()) {
						System.out.println(transfer.getTransferID() + "           to:" + users.get(accounts.get(transfer.getAccountTo())) + "         $" + transfer.getAmount());
					} else if (currentAccount.getAccount_id() == transfer.getAccountTo()) {
						System.out.println(transfer.getTransferID() + "         from:" + users.get(accounts.get(transfer.getAccountFrom())) + "         $" + transfer.getAmount());
					}
				}

				String exit = console.getUserInput("do you want to exit? press y: ");
				if(exit.equalsIgnoreCase("y")) {
					break;
				}

			}else if(TRANSFER_OPTIONS_SPECIFIC.equals(choice)){

				transferService.setAuthToken(currentUser.getToken());

				long transferId =  console.getUserInputInteger("Enter transfer id: ");
				Transfer transfer = transferService.getTransfer(transferId);
				if(users.containsKey(accounts.get(transfer.getAccountFrom())) && users.containsKey(accounts.get(transfer.getAccountTo()))){
				    System.out.println("--------------------------------------");
				    System.out.println("Transfer Details");
				    System.out.println("---------------------------------------");
				    System.out.println("Id: " + transfer.getTransferID());
				    System.out.println("From: " + users.get(accounts.get(transfer.getAccountFrom())));
				    System.out.println("To: " + users.get(accounts.get(transfer.getAccountTo())));
				    System.out.println("Status: " + transfer.getTransferStatus());
				    System.out.println("Amount: " + transfer.getAmount());
					System.out.println();
				    String exit = console.getUserInput("do you want to exit? press y: ");
				    if(exit.equalsIgnoreCase("y")) {
					mainMenu();
				    }
				}
			}
			exitProgram();
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
		Accounts receivingAccount;
		Accounts sendingAccount;
		Transfer transfer = new Transfer();
		transfer.setTransferType(2);
		transfer.setTransferStatus(2);
		long sendingID = currentUser.getUser().getId();
		long receivingID = console.getUserInputInteger("Enter user id");

		if (sendingID == receivingID) {
			System.out.println("You can not send TE bucks to yourself!");
			mainMenu();
		}
		accountService.setAuthToken(currentUser.getToken());

		Integer amount = console.getUserInputInteger("How many TE bucks would you like to send?");
		transfer.setAmount(BigDecimal.valueOf(amount));

		for (Accounts account : accountService.getAllAccounts()) {
			if (account.getUser_id() == sendingID) {
				sendingAccount = account;

				if (sendingAccount.getBalance() < amount) {
					System.out.println("\nInsufficient funds");
					break;
				}

				sendingAccount.setBalance(amount * -1);
				accountService.updateBalance(sendingAccount);
				transfer.setAccountFrom(sendingAccount.getAccount_id());
			}
			if ((account.getUser_id() == receivingID)) {
				receivingAccount = account;
				receivingAccount.setBalance(amount);
				accountService.updateBalance(receivingAccount);
				transfer.setAccountTo(receivingAccount.getAccount_id());
			}
			transferService.create(transfer);
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
