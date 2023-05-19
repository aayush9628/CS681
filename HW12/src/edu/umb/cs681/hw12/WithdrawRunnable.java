package edu.umb.cs681.hw12;

public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean done = false;

	public void setDone(){
		this.done = true;
	}
	
	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			while (true){
				if(done){
					break;
				}
				account.withdraw(100);
				Thread.sleep(2000);
			}
		}catch(InterruptedException exception){}
	}

	public static void main(String[] args) {

	}
}
