package bank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class Manager{
	
    //Manager login and returning manager details
	public List<String> loginManager(String email,String pass)
	{
	     List<String> logindetails=new ArrayList<>();
			try(Connection conn=DBconnection.provideConnection())
			{	
				PreparedStatement ps=conn.prepareStatement("select*from manager where email=? AND password=?");
			         ps.setString(1,email);
			         ps.setString(2,pass);
			         ResultSet rs=ps.executeQuery();
			              
			         if(rs.next())
			              {
			            	  String mname=rs.getString("name");
			            	  String memail=rs.getString("email");
			            	  String bankname=rs.getString("bankName");
			            	  logindetails.add(mname);
			            	  logindetails.add(memail);
			            	  logindetails.add(bankname);
			            	  
			            	  
			              }
			         else
			         {
			        	 System.out.print("INVALID USERNAME/PASSWORD     ");
			        	 logindetails.add("0");
			         }
			        conn.close();      
			
			}catch (SQLException e) {
				 System.out.print("Database not connected");	
				}
			
	       return logindetails;
	       
       }
	
	//Adding customer details and returning the customer details
	public int addCustomer(String firstname,String Lastname,String email,String mobileNo,String dob,String address,String Panno,String gender) 
	{
	   
     int customer_id=-1;
	 try(Connection conn= DBconnection.provideConnection()) {
		 PreparedStatement ps=conn.prepareStatement("insert into Customer(firstname,lastname,email,mobileno,dob,address,pancardno,gender) values(?,?,?,?,?,?,?,?)");
	     ps.setString(1,firstname);
	     ps.setString(2,Lastname);
	     ps.setString(3,email);
	     ps.setString(4,mobileNo);
	     ps.setString(5,dob);
	     ps.setString(6,address);
	     ps.setString(7,Panno);
	     ps.setString(8,gender);
	     
	     
		int x=ps.executeUpdate();
		if(x > 0) {
			 PreparedStatement ps2=conn.prepareStatement("select customer_id from Customer where email=? AND mobileno=?");
			 ps2.setString(1, email);
			 ps2.setString(2, mobileNo);
			 
			 ResultSet rs=ps2.executeQuery();
			 
			 if(rs.next()) 
			 {
				 customer_id=rs.getInt("customer_id");
			 }
			 
			 
		 }else
			 System.out.println("Inserted data is not correct");
		 
		 conn.close(); 
		}catch(SQLException e) {
			
			e.printStackTrace();

		}
		
		return customer_id;
		
	}
	
	//find account number using customer id
	public int findAccountNo(int customerID)
	{
		int account_Id=0;
		try(Connection conn= DBconnection.provideConnection()) {
			PreparedStatement ps2=conn.prepareStatement("select account_id from account where customer_id=?");
		
		 ps2.setInt(1,customerID);
		 
		 ResultSet rs=ps2.executeQuery();
		 
		 if(rs.next()) {
			 account_Id=rs.getInt("account_id");
		 }
		 conn.close(); 
	}catch(Exception e)
	{
		System.out.println(e);
	}
		return account_Id;
	}
	
	//adding the details credit,debit card and returning account number
	public void addAccount(int customerId,float balance,String accounttype,String branchID,String aadharNo,String creditNo,String debitNo)
	{
		
		try(Connection conn= DBconnection.provideConnection()) {
			 
			
				 PreparedStatement ps=conn.prepareStatement("insert into account(customer_id,balance,account_type,branch_id,AadharNo,creditCardNo,debitCardNo) values(?,?,?,?,?,?,?)");
			     ps.setInt(1,customerId);
			     ps.setFloat(2,balance);
			     ps.setString(3,accounttype);
			     ps.setString(4,branchID);
			     ps.setString(5,aadharNo);
			     ps.setString(6,creditNo);
			     ps.setString(7,debitNo);
			    int addaccount=ps.executeUpdate();
				if(addaccount>0)
				{
					System.out.println("Account added sucessfully..!");
					int accountno=findAccountNo(customerId);
					PreparedStatement ps1=conn.prepareStatement("insert into transaction(account_id,transaction_type,balance)value(?,?,?);");
					ps1.setInt(1, accountno);
					ps1.setString(2,"Balance brought");
					ps1.setFloat(3, balance);
					ps1.executeUpdate();					
				}else
				 {
					System.out.println("Inserted data is not correct");
				 }
				conn.close(); 
			}catch(Exception e)
				{
				System.out.print("Something happened during adding account");
				}
	}
	
	//Check whether the account is having or not to update the details
	public int checkaccount(int accountno)     
	{
		int customerid=-1;
		try(Connection conn=DBconnection.provideConnection())
		{
			
			PreparedStatement ps=conn.prepareStatement("select customer_id from account where account_id =?;");
			ps.setInt(1,accountno);
			ResultSet rs=ps.executeQuery();
			 if(rs.next()) {
				
				 customerid=rs.getInt("customer_id");
				 
			 }
			 conn.close(); 
		}catch(Exception e)
		{
			
		}
		return customerid;//return customer id if having else return -1
	}
	
	//Return Aadhar number if Aadhar number already linked
	public String ChechAadharLinked(int accountno)
	{
		String aadhar="";
		try(Connection conn=DBconnection.provideConnection())
		{
			PreparedStatement ps=conn.prepareStatement("select AadharNo from account where account_id in(?);");
			ps.setInt(1, accountno);
			ResultSet rs=ps.executeQuery();
			 
			 if(rs.next()) {
				 aadhar=rs.getString("AadharNo");
			 }
			 conn.close(); 

		}catch(Exception e)
		{
			System.out.println("Something happened during Aadhar card linking status");
		}
		return aadhar;
	}
	
	//update the customer details if any mistakes and add the aadhar number if aadhar not linked
	public void updateCustomer(int accountNo,String newaddress,String newMobileNo,String newEmail,String aadharno,String debitcard,String creditcard)
	{
		
		try(Connection conn= DBconnection.provideConnection()) {
			 
			 if(newaddress!=null)
			 {
			 PreparedStatement ps=conn.prepareStatement("update customer i inner join account a on i.customer_id=a.customer_id AND a.account_id=? set i.address=?;");

			 ps.setInt(1, accountNo);
			 ps.setString(2,newaddress);
			int x=ps.executeUpdate();
			 
			 if(x > 0) {
				 System.out.println("Address updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }}
			 
			 if(newMobileNo!=null)
			 {
			 PreparedStatement ps=conn.prepareStatement("update customer i inner join account a on i.customer_id=a.customer_id AND a.account_id=? set i.mobileno=?;");

			 ps.setInt(1, accountNo);
			 ps.setString(2,newMobileNo);
			int x=ps.executeUpdate();
			 
			 if(x > 0) {
				 System.out.println("Mobile number updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }}
			 
			 if(newEmail!=null)
			 {
				 
			 PreparedStatement ps=conn.prepareStatement("update customer i inner join account a on i.customer_id=a.customer_id AND a.account_id=? set i.email=?;");

			 ps.setInt(1, accountNo);
			 ps.setString(2,newEmail);
			int x=ps.executeUpdate();
			 
			 if(x > 0) {
				 System.out.println("Email updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }}
			 if(aadharno!=null)
			 {
			 PreparedStatement ps=conn.prepareStatement("update customer i inner join account a on i.customer_id=a.customer_id AND a.account_id=? set a.AadharNo=?;");

			 ps.setInt(1, accountNo);
			 ps.setString(2,aadharno);
			int x=ps.executeUpdate();
			 
			 if(x > 0) {
				 System.out.println("aadhar number updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }}
			 
			 if(debitcard!=null)
			 {
			 PreparedStatement ps=conn.prepareStatement("update customer i inner join account a on i.customer_id=a.customer_id AND a.account_id=? set a.debitCardNo=?;");

			 ps.setInt(1, accountNo);
			 ps.setString(2,debitcard);
			int x=ps.executeUpdate();
			 
			 if(x > 0) {
				 System.out.println("debit card  number updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }}
			 
			 if(creditcard!=null)
			 {
			 PreparedStatement ps=conn.prepareStatement("update customer i inner join account a on i.customer_id=a.customer_id AND a.account_id=? set a.creditCardNo=?;");

			 ps.setInt(1, accountNo);
			 ps.setString(2,creditcard);
			int x=ps.executeUpdate();
			 
			 if(x > 0) {
				 System.out.println("credit card  number updated sucessfully..!");
			 	 System.out.println("-------------------------------");
			 }else {
				 System.out.println("Updation failed....Account Not Found");
				 System.out.println("--------------------------------------");
			 }}
			 
			 
			 
			 
			 
			 
			 conn.close(); 
			}catch(SQLException e) {
				System.out.println("Something happened during updating customer details");
				
			}
		}
		//Remove the customer 
		public void removeCustomer(int accountID)
		{
			try(Connection conn= DBconnection.provideConnection()) {
				 
				PreparedStatement ps1=conn.prepareStatement("delete from transaction where account_id=?;");
				 ps1.setInt(1, accountID);
				 ps1.executeUpdate();
				 PreparedStatement ps2=conn.prepareStatement("delete from nominee where account_id=?;");
				 ps2.setInt(1, accountID);
				 ps2.executeUpdate();
				 PreparedStatement ps3=conn.prepareStatement("delete from loginDetails where account_id=?;");
				 ps3.setInt(1, accountID);
				 ps3.executeUpdate();
				 PreparedStatement ps=conn.prepareStatement("delete from account where account_id=?;");
				 ps.setInt(1, accountID);
			     
				int x4=ps.executeUpdate();
				
				 
				 if(x4>0) {
					 System.out.println("Account deleted sucessfully..!");
					 System.out.println("-------------------------------");
				 }else {
					 System.out.println("Something went wrong while daleted!!");
					 System.out.println("------------------------------------");
				 }	
				 conn.close(); 	
			}catch(SQLException e) {
					System.out.println("Something happened during removing customer");
				}
		}
			
		//return the list of customer added for given time
		public HashMap<Integer, ArrayList<Object>> listCustomer(String startdate, String enddate) {
		    HashMap<Integer, ArrayList<Object>> customers = new HashMap<>();
		    try (Connection conn = DBconnection.provideConnection()) {
		        PreparedStatement ps = conn.prepareStatement("select customer.customer_id, customer.mobileno,customer.firstname,customer.lastname,customer.email,account.balance,account.account_id from customer join account on customer.customer_id=account.customer_id where account.created_at between ? and ?;");
		        ps.setString(1, startdate);
		        ps.setString(2, enddate);
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            int customerid = rs.getInt("customer_id");
		            if (!customers.containsKey(customerid)) {
		                customers.put(customerid, new ArrayList<Object>());
		            }

		            ArrayList<Object> customerData = new ArrayList<>();
		            int accountid = rs.getInt("account_id");
		            customerData.add(accountid);

		            float b = rs.getFloat("balance");
		            customerData.add(b);

		            String email = rs.getString("email");
		            customerData.add(email);

		            String FN = rs.getString("firstname");
		            //customerData.add(FN);
                     FN=FN+" ";
		             FN += rs.getString("lastname");
		            
		            customerData.add(FN);

		            String mob = rs.getString("mobileno");
		            customerData.add(mob);

		            // Add the ArrayList containing customer data to the HashMap
		            customers.get(customerid).add(customerData);
		        }
		        conn.close(); 
		    } catch (Exception e) {
		        System.out.println(e);
		    }
		    return customers;//returning hashmap containing list of customer details
		}
		
		//List the Email address who are not linked the aadhar details
		public List<String> AadharNotLinkedList()
		{
			List<String> emaillist= new ArrayList<String>();
			 try (Connection conn = DBconnection.provideConnection()) {
			        PreparedStatement ps = conn.prepareStatement("select customer.email from customer join account on customer.customer_id=account.customer_id where account.AadharNo='';");
			        ResultSet rs=ps.executeQuery();
			        while(rs.next())
			        {
			        	emaillist.add(rs.getString("email"));
			        }
			        conn.close();      
			 }catch(Exception e)
			 {
				 System.out.print(e);
			 }
			
			
			return emaillist;
		}
		
	//Storing the transaction history for statement generation	
	public ArrayList<String> statementGeneration(int accountno,int customerid,String startdate,String enddate)
	{
		ArrayList<String> statement=new ArrayList<String>();
		 try (Connection conn = DBconnection.provideConnection()) 
		 { 
		        PreparedStatement ps = conn.prepareStatement("select DateOfTransaction,balance,creditAmount,debitAmount,ReferencesID,transaction_type from transaction where account_id=? and DateOfTransaction between ? and ? ;");
		        ps.setInt(1, accountno);
		        ps.setString(2,startdate);
		        ps.setString(3, enddate);
		        ResultSet rs=ps.executeQuery();
		        while(rs.next())
		        {
		            statement.add(rs.getDate("DateOfTransaction").toString());
		            statement.add(rs.getString("transaction_type"));
		            statement.add(Integer.toString(rs.getInt("ReferencesID")));
		            statement.add(String.valueOf(rs.getFloat("debitAmount")));
		            statement.add(String.valueOf(rs.getFloat("creditAmount")));
		            statement.add(String.valueOf(rs.getFloat("balance")));
		            
		            
		            
		        }
		        conn.close(); 
		 }catch(Exception e)
		 {
			 System.out.print(e);
		 }
		
		return statement;//return transaction history
	}
//This is used to generate statement number
public int statementNumberGeneration(int accountno)
{
	int statementno=0;
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps = conn.prepareStatement("insert into statement(account_id) value(?);");
		ps.setInt(1, accountno);
		int x=ps.executeUpdate();
		if(x>0)
		{
			PreparedStatement ps1=conn.prepareStatement("select statement_number from statement where account_id=? order by Time desc limit 1;");
		    ps1.setInt(1, accountno);
		    ResultSet rs=ps1.executeQuery();
		    if(rs.next())
		    {
		    	statementno=rs.getInt("statement_number");
		    }
		    
		}
		conn.close(); 
		}catch(Exception e)
		{
			System.out.println(e);
		}
		return statementno;
	}
//Returning previous previous month last date balance
	public float previousmonthbalance(int accountno,String previousdate,String PreviousEnddate)
	{
		float balance=0;
		try(Connection conn= DBconnection.provideConnection()) {
		 PreparedStatement ps2=conn.prepareStatement("select balance from transaction where account_id=? and DateOfTransaction between ? and ? order by DateOfTransaction DESC limit 1");
		 ps2.setInt(1,accountno);
		 ps2.setString(2,previousdate);
		 ps2.setString(3, PreviousEnddate);
		 ResultSet rs=ps2.executeQuery();
		 if(rs.next()) {
			 balance=rs.getFloat("balance");
		 }
		 conn.close(); 
	}catch(Exception e)
	{
		System.out.println(e);
	}
		return balance;
	}
	
//Returning customer and bank details for generating statement
public ArrayList<String> CustomerAndBankAddress(int accountno)
{
	ArrayList<String> address=new ArrayList<String>();
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps2=conn.prepareStatement("select customer.FirstName,customer.Address from customer join account on customer.customer_id=account.customer_id where account.account_id=?");
		 ps2.setInt(1,accountno);
         ResultSet rs=ps2.executeQuery();
         PreparedStatement ps3=conn.prepareStatement("select BankName,BankAddress from branch where branch_id='1000';");
         ResultSet rs1=ps3.executeQuery(); 
		
         if(rs.next()&&rs1.next()) {
			 address.add(rs.getString("FirstName"));
			 address.add(rs.getString("Address"));
			 address.add(rs1.getString("BankName"));
			 address.add(rs1.getString("BankAddress"));
		 }
         conn.close(); 
	}catch(Exception e)
	{
		System.out.println(e);
	}
	
	
	return address;
}
//returning the last login details
public void loggedin()
{
	
	System.out.println("Last logged in users");
	System.out.printf("%-12s %-20s%n","Account id","Login Time");
	try(Connection conn=DBconnection.provideConnection()) {
		
		PreparedStatement ps=conn.prepareStatement("select account_id,LoginTime from logindetails;");
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			
				System.out.printf("%-12s %-20s%n",rs.getString("account_id"),rs.getString("LoginTime"));
			
		}
		System.out.println();
		conn.close(); 
		
	}catch(Exception e)
	{
		System.out.println(e);
	}
}

//Service fees calculating and updating the balance
public void serviceFee(int accountno,double fees,double charge,String date)
{
	//float balance=0;
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps=conn.prepareStatement("update account set balance=? where account_id=?;");
		//PreparedStatement ps1=conn.prepareStatement("insert into transaction(account_id,transaction_type,debitAmount,balance,DateOfTransaction)value(?,'Service Fee',?,?,?);");
		ps.setFloat(1,(float)fees);
		ps.setInt(2, accountno);
		ps.executeUpdate();
	    PreparedStatement ps1=conn.prepareStatement("insert into transaction(account_id,transaction_type,debitAmount,balance,DateOfTransaction)value(?,?,?,?,?);");
		ps1.setInt(1, accountno);
		ps1.setString(2,"serviceFee");
		ps1.setFloat(3, (float)charge);
		ps1.setFloat(4, (float)fees);
		ps1.setString(5, date+" 23:59:59");//service fees transaction is stored 
		ps1.executeUpdate();
		conn.close(); 
	}catch(Exception e)
	{
		System.out.println("fees update error"+e);
	}
	
}
//Calculating interest based on account type
public float interestflag(int accountno)
{ String type;
  float balance=0;
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps=conn.prepareStatement("select account_type,balance from account where account_id=?;");
		ps.setInt(1, accountno);
		ResultSet rs=ps.executeQuery();		
	    if(rs.next()) {
	    	type=rs.getString("account_type");
	    	balance=rs.getFloat("balance");
	    	if(type.equalsIgnoreCase("saving"))
	    	{
	    	  	return (float) (balance*0.0003);// rate 0.03
	    	}
	    	else
	    	{
	    		return 0;
	    	}
	    }
	    conn.close(); 
	}
	catch(Exception e)
	{
		
	}
return 0;
}

}


