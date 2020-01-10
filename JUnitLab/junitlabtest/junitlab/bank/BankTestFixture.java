package junitlab.bank;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;
import junit.runner.Version; //Version Contoller.

import static org.junit.Assert.*;
import static java.lang.System.out;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankTestFixture {
	FirstNationalBank myAccount;
	String showMeTheMoney, showMeTheMoney2;
	
	@Before
	public void stage_0_setUp() throws AccountNotExistsException {
		myAccount = new FirstNationalBank();
		showMeTheMoney = myAccount.openAccount();
		showMeTheMoney2 = myAccount.openAccount();
		long depositAmount1 = 1500;
		long depositAmount2 = 12000;
		myAccount.deposit(showMeTheMoney, depositAmount1);
		myAccount.deposit(showMeTheMoney2, depositAmount2);
	}
	
	@Test
	public void stage_1_testTransfer() throws AccountNotExistsException,NotEnoughFundsException {	
		long transferAmount = 3456;
		myAccount.transfer(showMeTheMoney2, showMeTheMoney, transferAmount); //Az elsõ számlaszáma a terhelhetõ
		
		long myBalance = myAccount.getBalance(showMeTheMoney);
		long myBalance2 = myAccount.getBalance(showMeTheMoney2);	
		
		out.println("Számlaszámom: " + showMeTheMoney); 
		out.println("GigaEgyenlegem: " + myBalance);
		out.println("Második számlaszámom:" + showMeTheMoney2);
		out.println("Második Gigaegyenlegem: " + myBalance2);
		assertEquals(4956,myBalance,0);
		assertEquals(8544,myBalance2,0);
	}
	
	@Test (expected=NotEnoughFundsException.class)
	public void stage_2_testTransferWithoutEnoughFunds() throws NotEnoughFundsException, AccountNotExistsException {	
		long transferAmount = 3456;
		long myBalance = myAccount.getBalance(showMeTheMoney);
		long myBalance2 = myAccount.getBalance(showMeTheMoney2);	
		out.println("Számlaszámom: " + showMeTheMoney); 
		out.println("GigaEgyenlegem: " + myBalance);
		out.println("Második számlaszámom:" + showMeTheMoney2);
		out.println("Második Gigaegyenlegem: " + myBalance2);
		
		myAccount.transfer(showMeTheMoney, showMeTheMoney2, transferAmount); //Az elsõ számlaszáma a terhelhetõ
		//Ezt azért nem írja ki, mert: 
		//junitlab.bank.NotEnoughFundsException: Not enough funds on account 34778000-00000001
		/*myBalance = myAccount.getBalance(showMeTheMoney);
		myBalance2 = myAccount.getBalance(showMeTheMoney2);	
		out.println("Számlaszámom: " + showMeTheMoney); 
		out.println("GigaEgyenlegem: " + myBalance);
		out.println("Második számlaszámom:" + showMeTheMoney2);
		out.println("Második Gigaegyenlegem: " + myBalance2);*/

	}
}
