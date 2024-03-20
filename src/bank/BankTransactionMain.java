package bank;
import java.util.*;
public class BankTransactionMain {
public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Boolean login1=true;
		while(login1) {
			
			System.out.println("Welcome To Online Banking System");
			System.out.println("1. Manager Portal \r\n"
					         + "2. Customer Portal \r\n"
					         + "3. exit");
			System.out.println("Choose your option:");
			int choice=sc.nextInt();//Manager=>1 or customer=>2
			switch(choice)
			{
				  case 1://MANAGER login
				  {
				    String memail,mpass;
				    Manager mg=new Manager();
					boolean login=true;
					List<String> manager=new ArrayList<>();//To store manager name and bank location
				    while(true)//Untill email and password are valid
				    {
						System.out.println("LOGIN <<---->> MANAGER");
						System.out.println("--------------------------");
						System.out.println("Enter Email:");
						memail=sc.next();
						System.out.println("Enter Password:");
						mpass=sc.next();
						manager=mg.loginManager(memail,mpass);
						if(manager.get(0).equals("0"))    //similar to true or false
						{
							System.out.println("Try again!!");
						}else
						{
							break;
						}
				    }
					   System.out.println("WELCOME "+manager.get(0)+" MANAGER "+manager.get(2));
					   try {
							   while(login)//Untill manager want to exit
							   {
								 System.out.println("ENTER CHOICE:");
								 System.out.println("---------------------------------\r\n"
									          + "1.Add new customer\r\n"
									          + "2.Update existing account details\r\n"
									          + "3.Remove the existing account\r\n"
									          + "4.Show list of customers added for a given time\r\n"
									          + "5.Send alert mail if aadhar not linked\r\n"
									          + "6.Monthly statement generation\r\n"
									          + "7.Show last logged in details\r\n"
									          + "8.Logout\r\n"
									          +"----------------------------------\r\n");
							
							
							    int choice1=sc.nextInt();
							    ManagerDetails mgdetails=new ManagerDetails();
							    switch(choice1)
							    {
						        
						             //Add new customer     
						                case 1:
											{
										        mgdetails.AddNewCustomer();
									 			break;
											
											}
									//update the customer account
									    case 2:
									        {               
											    mgdetails.UpdateCustomerDetails();
									 			break;
										    }
										//delete the customer account
										case 3:  
										   {
												mgdetails.DeleteCustomer();
												break;
									       }
										
									//LIST THE CUSTOMER ADDED FOR A GIVEN TIME
									    case 4:
											{
												mgdetails.ListCustomerAddedForGivenTime();
												break;
											}
									
									//SEND ALERT MAIL IF AADHAR NOT LINKED
									    case 5:
									         {
									
												mgdetails.SendAlertMailAadharNotLinked();
													break;
									         }
									//monthly statement generation
									    case 6:
									         {    
										     	mgdetails.MonthlyStatementPdf();//monthly generated statement can be done for the previous month to current month
										         	break;
									         }
								       //show last logged in details
										case 7:
										     {
												mg.loggedin();
												break;
										     }
										
										//LOGGED OUT
										 case 8:
										     {
												System.out.println("--------------YOU SUCCESSFULLY LOGGED OUT---------");
												login=false;
												break;
										     }
										default:
											System.out.println("invalid");
							     }
						    }
						
						
						
				        }catch(Exception e) {
						    System.out.print(e);
					       }
			     break;
				}
			  case 2://customer login
				{
				
					int customerid,accountno;
					boolean login=true;
					List<String> customer=new ArrayList<>();//to store customer details
					Customer cs=new Customer();
					while(true) //iterate until user credentials are valid
					{
						System.out.println("LOGIN <<---->> CUSTOMER");
						System.out.println("--------------------------");
						System.out.println("Enter customerId");
						customerid=sc.nextInt();
						System.out.println("Enter Account No");
						accountno=sc.nextInt();
						customer=cs.logincustomer(customerid,accountno);
						if(customer.get(0).equals("0"))   //similar to true or false
						{
							System.out.println("Try again");
						}
						else
						{
							break;
						}
					}	
						try {
						    while(login)// customer can view/edit details until log out
							{
								System.out.println("WELCOME \""+customer.get(0)+"\" Customer in "+customer.get(2));
								System.out.println("ACCOUNT NO:"+accountno);
								System.out.println("ENTER CHOICE:");								
								System.out.println("---------------------------------\r\n"
										          + "1.Check balance\r\n"
										          + "2.Add nominee\r\n"
										          + "3.Modify nominee\r\n"
										          + "4.Remove nominee\r\n"
										          + "5.Deposit amount\r\n"
										          + "6.withdrawal/cheque amount\r\n"
										          + "7.Logout\r\n"
										          +"----------------------------------\r\n");
						       int choice3=sc.nextInt();
						       CustomerDetails csdetails=new CustomerDetails(customerid,accountno);
								
						       switch(choice3)
							   {
								
								//-----------------------check balance---------------------------
											case 1:
											{
												csdetails.CheckBalance();
												break;
											}
						       //------------------------Add Nominee------------------------------------------
											case 2:
											{   
												csdetails.AddNominee();
												break;
											}
								 //--------------------Update nominee details-------------------------
											case 3:
											{
												csdetails.UpdateNominee();
												break;
											}
									//---------------Remove nominee-----------------
											case 4:
											{
												csdetails.RemoveNominee();
									 			break;
											}
								    //-------deposit/credit amount---------
											case 5:
											{
												csdetails.Deposit();
												break;
											}
								   //---------Withdrawal amount----------
											case 6:
											{
												csdetails.withdrawal();
												break;
											}
									
									//---------LOGGED OUT--------
											case 7:
									        {
									        	System.out.println("--------------YOU SUCCESSFULLY LOGGED OUT---------");
									        	login=false;
									        
									        }
						        
						      }
						      
							}
						}catch(Exception e)
						 {
							System.out.println(e);
						 }
			break;						
			}
			case 3:
			{
				  System.out.println("======Exits=====");
				  login1=false;
		    }
			  
		}
			
    }
		sc.close();
  }

}
