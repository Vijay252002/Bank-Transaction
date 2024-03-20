package bank;
import java.util.Scanner;
public class CustomerDetails {

	int accountno,customerid;
	Customer cs=new Customer();
	Validation vd=new Validation();
	Scanner sc=new Scanner(System.in);
	
	public CustomerDetails(int customerno,int accountno)//Set the customer account number untill the customer logout
	{
		this.customerid=customerno;
		this.accountno=accountno;
	}
	public void CheckBalance()//Check the balance (account number is set in the constructor)	
	{
		System.out.println("Account No:"+accountno);
		System.out.println("Balance:"+cs.checkBalances(accountno));//method to check balance
	}
	
	
	public void AddNominee()//customer is adding the new nominee
	{
		int alreadydone=0;
		String nomineeName,lastname,Relationship,nomineeContactno,NomineeDOB;
		alreadydone=cs.checkAlreadyAdded(accountno);
		if(alreadydone!=0)
		{
			System.out.println("You have already added the nominee details with NOMINEE ID\""+alreadydone+"\"");
		}
		else
		{
		System.out.println("Adding nomineee details");
		while(true)
		{
		System.out.println("Enter nominee Firstname:");
		nomineeName=sc.next();
		if(vd.isValidName(nomineeName))
		{
			break;
		}
		else
		{
			 System.out.println("Invalid name.");
		}
		
		}
		
		while(true)
		{
		System.out.println("Enter nominee LastName:");
		 lastname=sc.next();
		if(vd.isValidName(lastname))
		{
			break;
		}
		else
		{
			 System.out.println("Invalid name.");
		}
		
		}
		nomineeName=nomineeName+" "+lastname;
		
		while(true)
		{
		System.out.println("Enter nominee relationship");
		Relationship=sc.next();
		if(vd.isValidName(Relationship))
		{
			break;
		}
		else
		{
			 System.out.println("Invalid Relationship");
		}
		}
		while(true)
		{
		System.out.println("Enter nominee contact number:");
		nomineeContactno=sc.next();
		if(vd.MobileNumberValidation(nomineeContactno))
		{
			break;
		}
		else
		{
			System.out.println("Invalid mobilenumber");
		}
		}
		while(true)
		{
		System.out.println("Enter nominee DOB(yyyy-mm-dd):");
		NomineeDOB=sc.next();
		if(vd.isValidDateOfBirth(NomineeDOB))
		{
			break;
		}
		else {
			System.out.println("Invalid DOB");
			
		}
		}
		System.out.println("Nominee id:"+cs.addNominee(accountno,nomineeName,Relationship,nomineeContactno,NomineeDOB));
		
		}
		
	}
	
	
	public void UpdateNominee() //customer is updating the existing nominee
	{
		int alreadydone;
		String edit3,newNomineeName=null,newNomineeRelationship=null,newNomineecontactno=null,newNomineeDOB=null;
		System.out.println("---------Update the nominee details--------");
		alreadydone=cs.checkAlreadyAdded(accountno);
		if(alreadydone!=0)
		{
			System.out.println("Do you want to change nominee name:(yes/no)");
 			edit3=sc.next();
 			if(edit3.equalsIgnoreCase("yes"))
 			{
 				System.out.println("Enter new Nominee name:");
	 			newNomineeName=sc.next();
 			}
 			System.out.println("Do you want to change nominee Relationship:(yes/no)");
 			edit3=sc.next();
 			if(edit3.equalsIgnoreCase("yes"))
 			{
 				System.out.println("Enter new Nominee Relationship :");
	 			newNomineeRelationship=sc.next();
 			}
 			System.out.println("Do you want to change nominee contactno:(yes/no)");
 			edit3=sc.next();
 			if(edit3.equalsIgnoreCase("yes"))
 			{
 				while(true)
 				{
 				System.out.println("Enter nominee contact number:");
 				newNomineecontactno=sc.next();
 				if(vd.MobileNumberValidation(newNomineecontactno))
 				{
 					if(vd.alreadyAddedMobile(newNomineecontactno)<=0)
 					{
 						break;
 					}
 					else
 					{
 						System.out.println("Mobile number already exits");
 					}
 				}
 				else
 				{
 					System.out.println("Invalid mobilenumber");
 				}
 				}
 			}
 			System.out.println("Do you want to change nominee DOB:(yes/no)");
 			edit3=sc.next();
 			if(edit3.equalsIgnoreCase("yes"))
 			{
 				while(true)
 				{
 				System.out.println("Enter nominee DOB(yyyy-mm-dd):");
 				newNomineeDOB=sc.next();
 				if(vd.isValidDateOfBirth(newNomineeDOB))
 				{
 					break;
 				}
 				else {
 					System.out.println("Invalid DOB");
 					
 				}
 				}
 			}
 			try {
 				cs.updateNominee(accountno,newNomineeName,newNomineeRelationship,newNomineecontactno,newNomineeDOB);
 				
 			}catch(Exception e)
 			{
 				System.out.println(e);
 			}
			
		}
		else
		{
			System.out.println("Nominee details are not added until now for your account.Please add nominee details!!!!");
		}
		//sc.close();
	}
	
	public void RemoveNominee() // customer is removing the existing nominee
	{
		System.out.println("-------Remove nominee details to your account----");
		int accountid=cs.checkAlreadyAdded(accountno);
			if(accountid==0)
			{
				System.out.println("No nominee is found for this account!!");
			}
			else
			{
				cs.removeNominee(accountno);
			
			}
	
	}
	
	public void Deposit() //customer is depositing amount
	{
		float totalbalance;
		String depositamount;
		System.out.println("---------Amount deposit to your account-------");
		while(true)
		{
		System.out.println("Enter amount to deposit:");
		depositamount=sc.next();
		if(vd.isValidFloat(depositamount))
		  {
			break;
		}
		else
		{
			System.out.println("Non numeric characters not accepted");
		}
		}
		totalbalance=cs.depositamount(accountno, Float.parseFloat(depositamount));
		System.out.println("Balance:"+totalbalance);
		
	}
	
	public void withdrawal() //customer is withdrawalling amount
	{

		float balance;
		String withdrawalamount;
		System.out.println("---------Amount withdrawal from your account-------");
		while(true)
		{
		System.out.println("Enter amount to withdrawal:");
		withdrawalamount=sc.next();
		if(vd.isValidFloat(withdrawalamount))
		{
			break;
		}
		else
		{
			System.out.println("Non numeric characters not accepted");
		}
		}
		balance=cs.withdrawalamount(accountno, Float.parseFloat(withdrawalamount));
		System.out.println("Balance:"+balance);
	}
	
	
}
