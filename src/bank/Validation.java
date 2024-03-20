package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	
	public  boolean isValidName(String name) {
        // Regular expression pattern for a name (letters, spaces, and hyphens)
        String regex = "^[a-zA-Z\\s-]+$";

        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher with the given name string
        Matcher matcher = pattern.matcher(name);

        // Return true if the name matches the pattern, otherwise false
        return matcher.matches();
    }
	
	
	
	public boolean EmailValidation(String email)
	{
		
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Return true if the email matches the pattern, otherwise false
        return matcher.matches();
	}

	
	public boolean MobileNumberValidation(String phoneNumber)
	{
		String regex = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        // Return true if the phone number matches the pattern, otherwise false
        return matcher.matches();
	}
	
	public  boolean isValidDateOfBirth(String dobString) {
        // Define the date format expected for the date of birth
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        try {
            // Parse the date of birth string to a LocalDate object
            LocalDate dob = LocalDate.parse(dobString, dateFormatter);
            
            // Calculate the age based on the date of birth
            LocalDate currentDate = LocalDate.now();
            Period ageDifference = Period.between(dob, currentDate);
            int age = ageDifference.getYears();
            
            // Check if the age is at least 18 years
            return age >= 18;
        } catch (DateTimeParseException e) {
            // Catch exception if the date format is invalid
            return false;
        }
    }
	
	 public boolean isValidPanNumber(String panNumber) {
	      
	        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(panNumber);

	        // Return true if the PAN number matches the pattern, otherwise false
	        return matcher.matches();
	    }
	 
	 public boolean isValidAadhaarNumber(String aadhaarNumber) {
	        // Remove spaces from Aadhaar number
	        String cleanAadhaarNumber = aadhaarNumber.replaceAll("\\s+", "");
	        String regex = "^\\d{12}$";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(cleanAadhaarNumber);

	        // Return true if the Aadhaar number matches the pattern, otherwise false
	        return matcher.matches();
	    }
	 
	 public boolean isValidCreditCardNumber(String creditCardNumber) {
	        // Remove any non-digit characters from the credit card number
	        String cleanedNumber = creditCardNumber.replaceAll("\\D", "");

	        // Check if the credit card number contains only digits and has a valid length
	        if (!cleanedNumber.matches("\\d{13,19}")) {
	            return false;
	        }

	        // Apply the Luhn algorithm to validate the credit card number
	        int sum = 0;
	        boolean alternate = false;
	        for (int i = cleanedNumber.length() - 1; i >= 0; i--) {
	            int digit = Integer.parseInt(cleanedNumber.substring(i, i + 1));
	            if (alternate) {
	                digit *= 2;
	                if (digit > 9) {
	                    digit -= 9;
	                }
	            }
	            sum += digit;
	            alternate = !alternate;
	        }
	        return (sum % 10 == 0);
	    }
	
	 public  boolean isValidFloat(String input) {
	        // Regular expression pattern for float numbers
	        String regex = "^[-+]?\\d*\\.?\\d+$";

	        // Check if the input matches the pattern
	        return input.matches(regex);
	    }
	 
	 
	 public  boolean isValidDebitCardNumber(String debitCardNumber) {
	        // Regular expression pattern for valid debit card numbers (16 digits)
	        String regex = "^[0-9]{15}$";

	        // Compile the pattern
	        Pattern pattern = Pattern.compile(regex);

	        // Create a matcher with the given debit card number string
	        Matcher matcher = pattern.matcher(debitCardNumber);

	        // Return true if the debit card number matches the pattern, otherwise false
	        return matcher.matches();
	    }
	 
	 public int alreadyAddedemail(String email)
	 {
		 //int n;
		 
		 try(Connection conn=DBconnection.provideConnection())
		 {
			 PreparedStatement ps = conn.prepareStatement("SELECT COUNT(email) AS email_count FROM customer WHERE email=?;");
			 ps.setString(1, email);
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
			     int emailCount = rs.getInt("email_count");
			 return emailCount;    
			 }

		 }catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 return 0;
	 }
	
	 public int alreadyAddedMobile(String mobile)
	 {
		 //int n;7655675678
		 
		 try(Connection conn=DBconnection.provideConnection())
		 {
			 PreparedStatement ps = conn.prepareStatement("SELECT COUNT(mobileNo) AS mobile_count FROM customer WHERE mobileNo=?;");
			 ps.setString(1, mobile);
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
			     int mobCount = rs.getInt("mobile_count");
			 return mobCount;    
			 }

		 }catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 return 0;
	 }
	 
	 public int alreadyAddedPan(String pan)
	 {
		 //int n;7655675678
		 
		 try(Connection conn=DBconnection.provideConnection())
		 {
			 PreparedStatement ps = conn.prepareStatement("SELECT COUNT(pancardNo) AS pan_count FROM customer WHERE pancardNo=?;");
			 ps.setString(1, pan);
			 ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
			     int panCount = rs.getInt("pan_count");
			 return panCount;    
			 }

		 }catch(Exception e)
		 {
			 System.out.println(e);
		 }
		 return 0;
	 }
	 
}
