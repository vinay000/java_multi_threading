//syncronizing means aranging in order one by one, only one thread can access a resource at one time
//asyncronizing means executing parallel

import java.util.*;

class Account{
	public int bal;
	
	public Account(int bal){
		this.bal = bal;
	}

	public boolean isSufficient(int withdarawAmount){
		if(bal >  withdarawAmount){
			return true;
		}
		else{
			return false;
		}
	}

	public void withdraw(int amount){
		bal = bal - amount;
		System.out.println("withdrawl amount is "+ amount);
		System.out.println("current balance is " + bal);

	}
}

class Customer implements Runnable{
	private String name;
	private Account account;
	
	public Customer(String name,Account account){
		this.name = name;
		this.account = account;
	}
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		System.out.println(name + " : enter amount you want to withdraw");
		int amt = sc.nextInt();

	//syncronized(shared resource){ logic }
		synchronized(account){

		if(account.isSufficient(amt)){
			System.out.println(name + "::");
			account.withdraw(amt);
		}
		else{
			System.out.println(name);
			System.out.println("=> amount is unsufficent");
		}

	}
		
	}

}

public class SyncronizingMultipleThread{
	public static void main(String args[]){

		Account a1 = new Account(1000);
		Customer c1 = new Customer("babbar",a1);
		Customer c2 = new Customer("jabbar",a1);
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c2);
		t1.start();
		t2.start();

	}
}