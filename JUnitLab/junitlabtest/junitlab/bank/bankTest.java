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
public class bankTest {
	
	FirstNationalBank myAccount;
	
	@Before
	public void stage_0_setUp() throws AccountNotExistsException {
		myAccount = new FirstNationalBank();
	}
	
	@Test ///ERROR!
	public void stage_1_testOpenAccount() throws AccountNotExistsException {	
		String showMeTheMoney = myAccount.openAccount();
		long myBalance = myAccount.getBalance(showMeTheMoney);
		
		String showMeTheMoney2 = myAccount.openAccount();
		long myBalance2 = myAccount.getBalance(showMeTheMoney2);	
		
		out.println("Számlaszámom: " + showMeTheMoney);
		out.println("GigaEgyenlegem: " + myBalance);
		out.println("Második számlaszámom:" + showMeTheMoney2);
		out.println("Második Gigaegyenlegem: " + myBalance2);
		assertEquals(0,myBalance,0);
		assertEquals(0,myBalance2,0);
	}
	
	@Test
	public void stage_2_testUniqueAccount() throws AccountNotExistsException {
		String showMeTheMoney = myAccount.openAccount();
		//long myBalance = myAccount.getBalance(showMeTheMoney);
		
		String showMeTheMoney2 = myAccount.openAccount();
		//long myBalance2 = myAccount2.getBalance(showMeTheMoney2);	
		assertNotEquals(showMeTheMoney, showMeTheMoney2);
	}
	
	@Test (expected=AccountNotExistsException.class)
	public void stage_3_testInvalidAccount() throws AccountNotExistsException{
		String showMeTheMoney = myAccount.openAccount();
		
		String showMeTheMoney3 = "34778000-0000025";
		long myBalance = myAccount.getBalance(showMeTheMoney3);
		//java.lang.AssertionError: Expected exception: java.lang.IllegalArgumentException
		// IT WORKS!
	}
	
	@Test
	public void stage_4_testDeposit() throws AccountNotExistsException {
		String showMeTheMoney = myAccount.openAccount();
		long myBalance = myAccount.getBalance(showMeTheMoney);
		long depositAmount2 = 2000;
		
		myAccount.deposit(showMeTheMoney, depositAmount2);
		myBalance = myAccount.getBalance(showMeTheMoney);
		assertEquals(2000,myBalance,0);
	}

}
