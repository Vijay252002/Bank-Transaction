package bank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Customer {

	//customer login using customerid,accountno
	public List<String> logincustomer(int customerid,int accountno)
	{
		List<String> customerDetails=new ArrayList<String>();
		
			try(Connection conn=DBconnection.provideConnection())
			{	
				PreparedStatement ps=conn.prepareStatement("select customer.FirstName,customer.LastName,account.balance from customer join account on customer.customer_id=account.customer_id where account.customer_id=? AND account.account_id=?");
			         ps.setInt(1,customerid);
			         ps.setInt(2,accountno);
			         PreparedStatement ps1=conn.prepareStatement("select branchName,BankName,BankAddress from branch where branch_id='1000';");
			         ResultSet rs=ps.executeQuery();
			         ResultSet rs1=ps1.executeQuery();
			              
			         if(rs.next())
			         {
			        	 String name=rs.getString("FirstName");
		            	  name=name+" "+rs.getString("LastName");
		            	  customerDetails.add(name);
		            	  float balance=rs.getFloat("balance");
		            	  customerDetails.add(Float.toString(balance));
		            	  if(rs1.next())
					         {
					        	 customerDetails.add(rs1.getString("branchName"));
					        	 customerDetails.add(rs1.getString("BankAddress"));
					         }
		            	  PreparedStatement ps2=conn.prepareStatement("insert into logindetails(account_id) value(?);");
		            	  ps2.setInt(1,accountno);
		            	  ps2.executeUpdate();
		            	  
		            	  
			         }
			         else
			         {
			        	 System.out.println("INVALID USERNAME/PASSWORD   ");
			        	 customerDetails.add("0");
			         }
			         
			
			         conn.close(); 
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return customerDetails;//customer details is returning
		
	}
	//checking customer balance
	public float checkBalances(int accountno)
	{
		float balance=0;
		try(Connection conn=DBconnection.provideConnection())
		{	
			PreparedStatement ps=conn.prepareStatement("select balance from account where account_id=?;");
		         ps.setInt(1,accountno);
		         ResultSet rs=ps.executeQuery();
		         if(rs.next())
		         {
		        	 balance=rs.getFloat("balance");
		         }
		         conn.close(); 
	}catch(Exception e)
		{
		System.out.println(e);
		}
    
		return balance;// balance returning
	
	
	
	}
	//adding new nominee to the customer
	public int addNominee(int accountno,String nomineeName,String Relationship,String nomineeContactno,String NomineeDOB)
	{
		
		int nominee_id=-1;
		try(Connection conn=DBconnection.provideConnection())
		{
			PreparedStatement ps=conn.prepareStatement("insert into nominee(account_id,nomineeName,relationship,contactNo,DOB) values(?,?,?,?,?);");
		     ps.setInt(1, accountno);
			 ps.setString(2,nomineeName);
		     ps.setString(3,Relationship);
		     ps.setString(4,nomineeContactno);
		     ps.setString(5,NomineeDOB);
		     int x=ps.executeUpdate();
			 if(x > 0) {
				 PreparedStatement ps2=conn.prepareStatement("select nominee_id from nominee where account_id=?;");
				 ps2.setInt(1, accountno);
				 ResultSet rs=ps2.executeQuery();
				 
				 if(rs.next()) {
					 nominee_id=rs.getInt("nominee_id");
				 }
				 
				 
			 }else
				 System.out.println("Inserted data is not correct");
			 conn.close(); 
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return nominee_id;// returning nominee id
	}
	
// this function is used to validate whether the nominee is already added or not
public int checkAlreadyAdded(int accountno)
{
	int nomineeid=0;
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps=conn.prepareStatement("select nominee_id from nominee where account_id in(?);");
		ps.setInt(1, accountno);
		ResultSet rs=ps.executeQuery();
		 
		 if(rs.next()) {
			 nomineeid=rs.getInt("nominee_id");
		 }
		 conn.close(); 
	}catch(Exception e)
	{
		System.out.println(e);
		
	}
	return nomineeid;      //return nominee id if having else return 0
}

//Used to update the existing nominee details
public void updateNominee(int accountno,String newNomineeName,String newNomineeRelationship,String newNomineecontactno,String newNomineeDOB)
{
	
	try(Connection conn=DBconnection.provideConnection())
	{
		if(newNomineeName!=null)
		 {
		 PreparedStatement ps=conn.prepareStatement("update nominee set nomineeName=? where account_id=?;");

		 ps.setString(1,newNomineeName );
		 ps.setInt(2,accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			 System.out.println("Nominee Name updated sucessfully..!");
		 	 System.out.println("-------------------------------");
		 }else {
			 System.out.println("Updation failed....Account Not Found");
			 System.out.println("--------------------------------------");
		 }}
		if(newNomineeRelationship!=null)
		 {
		 PreparedStatement ps=conn.prepareStatement("update nominee set relationship=? where account_id=?;");

		 ps.setString(1,newNomineeRelationship );
		 ps.setInt(2,accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			 System.out.println("Nominee Relationship updated sucessfully..!");
		 	 System.out.println("-------------------------------");
		 }else {
			 System.out.println("Updation failed....Account Not Found");
			 System.out.println("--------------------------------------");
		 }}
		if(newNomineecontactno!=null)
		 {
		 PreparedStatement ps=conn.prepareStatement("update nominee set contactNo=? where account_id=?;");

		 ps.setString(1,newNomineecontactno );
		 ps.setInt(2,accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			 System.out.println("Nominee contactno updated sucessfully..!");
		 	 System.out.println("-------------------------------");
		 }else {
			 System.out.println("Updation failed....Account Not Found");
			 System.out.println("--------------------------------------");
		 }}
		if(newNomineeDOB!=null)
		 {
		 PreparedStatement ps=conn.prepareStatement("update nominee set DOB=? where account_id=?;");

		 ps.setString(1,newNomineeDOB );
		 ps.setInt(2,accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			 System.out.println("Nominee DOB updated sucessfully..!");
		 	 System.out.println("-------------------------------");
		 }else {
			 System.out.println("Updation failed....Account Not Found");
			 System.out.println("--------------------------------------");
		 }}
		
		
		conn.close(); 
	}catch(Exception e)
	{
		System.out.println(e);
	}
}

//removing the existing nominee
public void removeNominee(int accountno)
{
	try(Connection conn= DBconnection.provideConnection()) {
		 PreparedStatement ps=conn.prepareStatement("delete from nominee where account_id=?;");

		 ps.setInt(1, accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			 System.out.println("Nominee details deleted sucessfully..!");
			 System.out.println("-------------------------------");
		 }else {
			 System.out.println("Something went wrong while daleted!!");
			 System.out.println("------------------------------------");
		 }	
		 conn.close(); 
}catch(SQLException e) {
		System.out.println(e);
	}
	
}
//deposit amount
public float depositamount(int accountno,float depositamount)
{
float balance=0;
	
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps=conn.prepareStatement("update account set balance=balance+? where account_id=?;");
		ps.setFloat(1,depositamount);
		ps.setInt(2, accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			  balance=checkBalances(accountno);
			 PreparedStatement ps1=conn.prepareStatement("insert into transaction(account_id,transaction_type,creditAmount,balance)value(?,?,?,?);");
			 ps1.setInt(1, accountno);
			 ps1.setString(2,"Deposit");
			 ps1.setFloat(3,depositamount);
			 ps1.setFloat(4, balance);
			 ps1.executeUpdate();
			 System.out.println("Amount deposited sucessfully..!");
		 	 System.out.println("-------------------------------");
		 }else {
			 System.out.println("deposited failed....Account Not Found");
			 System.out.println("--------------------------------------");
		 }
		 conn.close(); 
	}catch(Exception e) {
		System.out.println(e);
	}
	return balance;//return balance after deposit
	
}
//withdrawal amount
public float withdrawalamount(int accountno,float withdrawalamount)
{
float balance=0,mainBalance=0;
mainBalance=checkBalances(accountno);
if(withdrawalamount>mainBalance)
{
	System.out.println("Insufficient balance!!!!!!");
	return 0;
	
}
	
	try(Connection conn=DBconnection.provideConnection())
	{
		PreparedStatement ps=conn.prepareStatement("update account set balance=balance-? where account_id=?;");
		ps.setFloat(1,withdrawalamount);
		ps.setInt(2, accountno);
		int x=ps.executeUpdate();
		 
		 if(x > 0) {
			  balance=checkBalances(accountno);
			 PreparedStatement ps1=conn.prepareStatement("insert into transaction(account_id,transaction_type,debitAmount,balance)value(?,?,?,?);");
			 ps1.setInt(1, accountno);
			 ps1.setString(2,"Cheque");
			 ps1.setFloat(3,withdrawalamount);
			 ps1.setFloat(4, balance);
			 ps1.executeUpdate();
			 System.out.println("Amount withdrawal sucessfully..!");
		 	 System.out.println("-------------------------------");
		 }else {
			 System.out.println("withdrawal failed....Account Not Found");
			 System.out.println("--------------------------------------");
		 }
		 conn.close(); 
	}catch(Exception e) {
		System.out.println(e);
	}
	return balance;//return the balance after withdrawal
	
}


}


