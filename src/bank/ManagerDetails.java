package bank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class ManagerDetails {
	
	Scanner sc=new Scanner(System.in);
	Manager mg=new Manager();
	Validation vd=new Validation();
	
	//getting the input details
	public void AddNewCustomer()
	{
		    String Firstname,Lastname,email,mobileNo,gender,dob,address,panNo,accounttype,branchID,aadharNo,creditNo,debitNo;
	        float balance;
	        ArrayList<String> list=new ArrayList<String>(Arrays.asList("Name","Email","MobileNO","Gender","DOB","Address","PanNo","Balance","AccountType"));
	        ArrayList<String> listdetails=new ArrayList<>();//To store customer details
			System.out.println("---------NEW ACCOUNT----------");
			while(true)
			{
			
			System.out.println("Enter Customer FirstName:");
			Firstname=sc.next();
			if(vd.isValidName(Firstname))
			{
				break;
			}
			else
			{
				 System.out.println("Invalid name.");
			}
			
			}
			listdetails.add(Firstname);
			
			while(true)
			{
			System.out.println("Enter Customer LastName:");
			Lastname=sc.next();
			if(vd.isValidName(Lastname))
			{
				break;
			}
			else
			{
				 System.out.println("Invalid name.");
			}
			
			}
			
			while(true)  //until email valid
			{
			System.out.println("Enter Email:");
			email=sc.next();
			if(vd.EmailValidation(email))
			{
				
				if(vd.alreadyAddedemail(email)<=0)
				{
					break;
				}
				else
				{
					System.out.println("Email already exits");
				}
				
				
			}
			else
			{
				System.out.println("Invalid email address.");
			}
			}
			listdetails.add(email);
			while(true)//until mobile number valid
			{
			System.out.println("Enter MobileNo:");
			mobileNo=sc.next();
		
			if(vd.MobileNumberValidation(mobileNo))
			{
				if(vd.alreadyAddedMobile(mobileNo)<=0)
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
				 System.out.println("Invalid phone number.");
			}
			}
			listdetails.add(mobileNo);
			System.out.println("Enter Gender:");
			gender=sc.next();
			listdetails.add(gender);
			while(true) //until dob valid
			{
			System.out.println("Enter DOB:(yyyy-mm-dd)");
			dob=sc.next();
			if(vd.isValidDateOfBirth(dob))
			{
				break;
			}
			else {
				System.out.println("Invalid date of birth or age under 18");
			}
			}
			listdetails.add(dob);
			System.out.println("Enter Address:");
			sc.nextLine();
			address=sc.nextLine();
			listdetails.add(address);
			while(true)
			{
			System.out.println("Enter PAN NUMBER:");
			panNo=sc.next();	
			if(vd.isValidPanNumber(panNo))
			{
					
				if(vd.alreadyAddedPan(panNo)<=0)
				{
					break;
				}
				else
				{
					System.out.println("Pan number already exits");
				}
				
			}
			else
			{
				System.out.println("Invalid Pan Number");
			}
	         }
		    listdetails.add(panNo);
			int customerId=-1;
			try
			{
				//addCustomer is the method name
				customerId=mg.addCustomer(Firstname,Lastname,email,mobileNo,dob,address,panNo,gender);
				try {
					System.out.println("Enter initial balance:");
					balance=sc.nextFloat();
					listdetails.add(Float.toString(balance));
					System.out.println("Enter account type:");
					sc.nextLine();
					accounttype=sc.nextLine();
					listdetails.add(accounttype);
					System.out.println("Enter branch ID:");
					branchID=sc.nextLine();
					while(true)  //validate Aadhar until true
					{
					System.out.println("Enter Aadhar card Number:");
					aadharNo=sc.nextLine();
					if((aadharNo=="") ||( vd.isValidAadhaarNumber(aadharNo)))
					{
						break;
					}
					else
					{
						System.out.println("Invalid Aadhaar number.");
					}
					}
					System.out.println("Enter Credit card Number:");
					creditNo=sc.nextLine();
					System.out.println("Enter Debit card Number:");
					debitNo=sc.nextLine();
					mg.addAccount(customerId,balance,accounttype,branchID,aadharNo,creditNo,debitNo);//add this details into account 
					
				}catch(Exception e)
				{
					System.out.println(e);
				}
				System.out.println("===Account details====");
				System.out.println("Customer ID:"+customerId);
				System.out.println("Account ID:"+mg.findAccountNo(customerId));
				//After creating the account list the customer details below for loop is used to display
			    for(int i=0;i<list.size();i++)
			    {
			    	System.out.println(list.get(i)+":"+listdetails.get(i));
			    }
				
				
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
			
	}
	
	
	
	// getting the input details to update
	public void UpdateCustomerDetails()
	{
		String newaddress=null,newMobileNo=null,newEmail=null,aadharno=null,debitcard=null,creditcard=null;
	    int accountNo,a=-1;
		System.out.println("-------UPDATE Customer details-------");
		while(true)
		{
		System.out.println("Enter Customer Account No. ");
			accountNo=sc.nextInt();
			a=mg.checkaccount(accountNo);
			if(a==-1)
			{
				System.out.println("Account not found try again!!");
			}
			else
			{
				break;
			}
		}
			System.out.println("Do you want to update OLD ADDRESS:yes/no");
			String edit1=sc.next();
			if(edit1.equalsIgnoreCase("yes"))
			{
				System.out.println("Enter new address:");
				sc.nextLine();
 			newaddress=sc.nextLine();
				
			}
			System.out.println("Do you want to update OLD MOBILENUMBER:yes/no");
			String edit2=sc.next();
			if(edit2.equalsIgnoreCase("yes"))
			{  
				while(true)
				{
				System.out.println("Enter new MobileNUMBER:");
 			    newMobileNo=sc.next();
 			    if(vd.MobileNumberValidation(newMobileNo))
 			    {
 			    	if(vd.alreadyAddedMobile(newMobileNo)<=0)
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
 			    	System.out.println("Invalid mobile number");
 			    }
				}
				
			}
			System.out.println("Do you want to update OLD EMAIL ID:yes/no");
			String edit3=sc.next();
			if(edit3.equalsIgnoreCase("yes"))
			{
				
				while(true)
				{
				System.out.println("Enter new Emial:");
 			newEmail=sc.next();
 			if(vd.EmailValidation(newEmail))
			{
 				if(vd.alreadyAddedemail(newEmail)<=0)
				{
					break;
				}
				else
				{
					System.out.println("Email already exits");
				}
			}
			else
			{
				System.out.println("Invalid email address.");
			}
				}
			}
			System.out.println("Do you want to Add aadhar NUMBER:yes/no");
			String edit4=sc.next();
			if(edit4.equalsIgnoreCase("yes"))
			{
				String aa=mg.ChechAadharLinked(accountNo);
				if(aa.equals(""))
				{
					while(true)
					{
				       System.out.println("Enter AADHAR NUMBER to link:");
 			           aadharno=sc.next();
			 			if((aadharno=="") ||( vd.isValidAadhaarNumber(aadharno)))
						{
							break;
						}
						else
						{
							System.out.println("Invalid Aadhaar number.");
						}
					}
				}
				else
				{
					System.out.println("Aadhar details already linked with bank!!!");
					
				}
			}
			System.out.println("Do you want to Add Debit card NUMBER:yes/no");
			String edit5=sc.next();
			if(edit5.equalsIgnoreCase("yes"))
			{
						while(true)
						{
						System.out.println("Enter debit card number:");
						debitcard=sc.next();
		 			if(vd.isValidDebitCardNumber(debitcard))
					{
		 				break;
					}
					else
					{
						System.out.println("Invalid debit card number.");
					}
						}
				}
			
			System.out.println("Do you want to Add credit card NUMBER:yes/no");
			String edit6=sc.next();
			if(edit6.equalsIgnoreCase("yes"))
			{
						while(true)
						{
						System.out.println("Enter credit card number:");
						creditcard=sc.next();
		 			if(vd.isValidDebitCardNumber(creditcard))
					{
		 				break;
					}
					else
					{
						System.out.println("Invalid credit card number.");
					}
						}
				}
			
				try {
				//updateCustomer is method to change the customer details
		mg.updateCustomer(accountNo,newaddress,newMobileNo,newEmail,aadharno,debitcard,creditcard);
		} catch (Exception e) {
			
		System.out.println("Something went wrong while updating customer details"+e);
		}
				
	}
	
	//delete the customer
	public void DeleteCustomer()
	{
		int accountNo,a=-1;
		System.out.println("-------DELETE THE CUSTOMER ACCOUNT-----");
		System.out.println("Enter ACCOUNT NUMBER to remove the customer:");
		accountNo=sc.nextInt();
			 a=mg.checkaccount(accountNo);
			if(a==-1)
			{
				System.out.println("Account not found try again!!");
			}
			else
			{
				
				mg.removeCustomer(accountNo);
				
			}
			
	}
	
	
	//Listing the customer added at a given time
	public void ListCustomerAddedForGivenTime()
	{

		String startdate,enddate;
		ArrayList<String> list=new ArrayList<>(Arrays.asList("Account Id:","Balance:","Email :","Name:","Mobile Number:"));
		System.out.println("LIST Of CUSTOMERS ADDED FOR A GIVEN TIME");
		System.out.println("Enter start date :YYYY-MM-DD");
		startdate=sc.next();
		System.out.println("Enter end date:YYYY-MM-DD");
		enddate=sc.next();
		HashMap<Integer, ArrayList<Object>> customers= mg.listCustomer(startdate,enddate);
		System.out.println("Number of customers:"+customers.size());
		for (Map.Entry<Integer, ArrayList<Object>> map : customers.entrySet()) {
		    System.out.println("Customer ID:"+map.getKey());
		    for (Object obj : map.getValue()) {
		        if (obj instanceof ArrayList) {
		            ArrayList<?> nestedList = (ArrayList<?>) obj;
		            for(int i=0;i<list.size();i++)
		            {
		            	System.out.println("    "+list.get(i)+""+nestedList.get(i));
		            }
		                System.out.println("----------------------------------------");
		            
		        } 
		    }
		}
		
	}
	
//sending the alert mail
	public void SendAlertMailAadharNotLinked()
	{
		
		EmailSender emailSender = new EmailSender("sriharishr105@gmail.com", "czwympvajwawowbh", "smtp.gmail.com", 587);
		try
		{
			List<String> AadharNotLinkedList=new ArrayList<String>();
	        AadharNotLinkedList=mg.AadharNotLinkedList();
	        System.out.println(AadharNotLinkedList);
	        for(int i=0;i<AadharNotLinkedList.size();i++)
	        {
					MimeMessage message = new MimeMessage(emailSender.getSession());
					String messageContent = "Your Aadhar Number is not linked with your bank account.So kindly link your Aadhar number with bank";
					message.setContent(messageContent, "text/html");
		 
		           // Set recipient and subject
		            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(AadharNotLinkedList.get(i)));
		           //  message.setSubject("Salary Details");
		            
		            // Send the email
			            emailSender.sendEmail(AadharNotLinkedList.get(i), "ALERT MAIL", message);
		    }
			System.out.println("Email sent successfully!!");
			}catch(Exception e)
			{
				System.out.println(e);
			}
	}
	
	//monthly statement generation pdf for period of one month before current month  
	public void MonthlyStatementPdf()
	{
		ArrayList<String> statement=new ArrayList<String>();
    	ArrayList<String>addd=new ArrayList<String>();
    	
	    int customer_id=-1,account_id,transactioncount=0,statementnumber;
	    float bankcharge=10;//basic  service charge for bank is 10
	    float balance,debit=0,credit=0,interest;
		System.out.println("Monthly statement generation");
		LocalDate today = LocalDate.now();
		LocalDate start = LocalDate.of(today.getYear(),today.getMonthValue(),01);
		LocalDate astart=start;
		LocalDate end = start.minusMonths(5);//take the previous previous month balance from 5 months before
		end = start.minusMonths(1);
		System.out.println("Enter account number:");
		account_id=sc.nextInt();//get account number
		customer_id=mg.checkaccount(account_id);//check account having or not
		if(customer_id==-1)
		{
			System.out.println("Sorry account not found");
		}
		else
		{
			statementnumber=mg.statementNumberGeneration(account_id);
			ArrayList<String> add=new ArrayList<>();
			add=mg.CustomerAndBankAddress(account_id);
		    addd.add(add.get(0));
		    addd.add(add.get(1));
		    addd.add(add.get(2));
		    addd.add(add.get(3));
		    //to identify previous previous month last date balance
			balance=mg.previousmonthbalance(account_id,start.minusMonths(7).toString(),end.minusDays(1).toString());
			//astart.minusMonth(1)  is the previous month start date
			String aastart=astart.minusMonths(1).toString();//If you want to enter date by manual uncomment the below line
			//System.out.println("Enter start date to generate statement");
			//aastart=sc.next();//enter start date to generate statement 
			
			
			//statement is generating from previous month to current day
			statement=mg.statementGeneration(account_id,customer_id,aastart,today.plusDays(1).toString());
			addd.add(today.toString());
			addd.add(aastart);
			addd.add(today.minusDays(1).toString());
			addd.add(aastart);
			addd.add("Balance brought");
			addd.add(" ");
			addd.add(" ");
			addd.add("");
			addd.add(Float.toString(balance));
			for(int i=0;i<statement.size();i=i+6)// this loop is used to find total credit,total debit,transaction count
			{  
				balance=Float.parseFloat(statement.get(i+5));
				transactioncount++;
				debit=debit+Float.parseFloat(statement.get(i+3));
				credit=credit+Float.parseFloat(statement.get(i+4));
				
			}
			bankcharge=(float) (bankcharge+transactioncount*3.5);
			interest=(float) mg.interestflag(account_id);//calculating interest based on flag if account type is saving calculate interest if account type is current do not calculate interest
			mg.serviceFee(account_id,balance-bankcharge+interest,transactioncount*3.5,today.toString());
			interest=(float) mg.interestflag(account_id);//calculating interest based on flag if account type is saving calculate interest if account type is current do not calculate interest
			PDFGenerator pdf=new PDFGenerator();
			addd.add(today.minusDays(1).toString());
			addd.add("Service fee");
			addd.add("SF");
			addd.add(Float.toString(bankcharge));
			addd.add(" ");
			addd.add(Float.toString(balance-bankcharge));
	        addd.add(Float.toString(credit));
	        addd.add(Float.toString(debit));
	        addd.add(Float.toString(bankcharge));
	        addd.add(Float.toString(interest));
	        System.out.println("PDF generarting");
			pdf.display(statement,addd,statementnumber);//pdf generation
			System.out.println("D:/jdk-11.0.2/BankTransactionhello.pdf");
			System.out.println("PDF generated");
			
			
		}
		
	}
	
}


