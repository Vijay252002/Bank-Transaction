# Bank Transaction System

## Overview

   The Bank Transaction System is a user-friendly application designed to manage banking transactions efficiently for both customers and managers. It offers a range of features including account management, transaction processing, statement generation, nominee management, balance inquiry, and more. With role-based access control, customers and managers can securely perform their respective tasks while ensuring data privacy and security.

### Key Features

- **Roles**: Supports two roles - Manager and Customer - each with specific permissions and functionalities.
- **Account Management**: Create, update, and remove customer accounts.
- **Interest Calculating**: Calculate interest based on flag.
- **Email Sending**: Sending mail if Aadhar number not linked.
- **Login Details**: Show the customer login time and account id.
- **Statement Format in PDF Generation**: Generate detailed transaction statements for customers to review their account activity.
- **Nominee Management**: Manage nominee details for customer accounts, providing beneficiaries for financial assets.
- **Transaction Processing**: Deposit, withdraw, and transfer funds between accounts seamlessly.
- **Validation**: Validate the name, email, mobile number, Date of Birth, Aadhar number, credit card, debit card number.

## Functionality Overview

### Manager Role

- **Account Management**: Create, update, and remove customer accounts as needed.
- **Transaction Monitoring**: View transaction logs for monthly statement generation and monitor account activity for security purposes.
- **Statement Generation**: Generate transaction statements as PDF for customers upon request.
- **List Customers**: List the customers added for the given time.
- **Email**: Send email if Aadhar details are not linked with their account.
- **Login History**: Show a list of customers login.
- **System Administration**: Configure system settings and manage user roles and permissions.

### Customer Role

- **Balance Inquiry**: Check account balances.
- **Transaction Processing**: Deposit funds, withdraw cash, and transfer money between accounts securely.
- **Nominee Management**: Add, edit, or remove nominee details to ensure proper asset allocation.
- **Secure Authentication**: Log in securely using unique credentials to access account information and perform transactions.

### How to Access

- Managers can access the system using their designated login credentials provided during setup.
- Customers can log in using their unique username and password to access their accounts and perform banking transactions.

## Installation

### System Setup

1. Install Eclipse.
2. Install MySQL Workbench.

### Database Configuration
```java
Class.forName("com.mysql.cj.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/banktransaction";
conn= DriverManager.getConnection(url,"root","root");
```
### Structure of database
BankTransaction Database Structure:

Tables:
- Manager
  - manager_id: INT
  - username: VARCHAR(50) 
  - password: VARCHAR(50) 
  - email: VARCHAR(100) 
  - full_name: VARCHAR(100) 

- Customer
  - customer_id: INT 
  - username: VARCHAR(50) 
  - password: VARCHAR(50) 
  - email: VARCHAR(100) 
  - full_name: VARCHAR(100) 
  - date_of_birth: DATE 
  - mobile_number: VARCHAR(15)
  - aadhar_number: VARCHAR(20) 
  - address: VARCHAR(255) 
  - created_at: TIMESTAMP 

- Account
  - account_id: INT 
  - customer_id: INT 
  - balance: DECIMAL(10,2)
  - account_type: VARCHAR(50) 
  - branch_id: INT
  - creditCard: VARCHAR(20)
  - created_at: TIMESTAMP 

- Transaction
  - transaction_id: INT 
  - account_id: INT 
  - amount: DECIMAL(10,2) 
  - transaction_type: VARCHAR(50) 
  - transaction_date: TIMESTAMP 
  - remarks: VARCHAR(255) 

- LoginDetails
  - login_id: INT 
  - user_id: INT 
  - login_time: TIMESTAMP 

- Nominee
  - nominee_id: INT 
  - account_id: INT 
  - full_name: VARCHAR(100) 
  - relationship: VARCHAR(50)
  - address: VARCHAR(255) 
  - mobile_number: VARCHAR(15) 

- Statement
  - statement_id: INT
  - account_id: INT 
  - statement_date: DATE 

- Branch
  - branch_id: INT 
  - branch_name: VARCHAR(100) 
  - address: VARCHAR(255) 
  - contact_number: VARCHAR(15)

### User Access Setup
- Run the BankTransactionMain.java
### Manager login
![Manager login](pictures/manager.png)
- Manager can access the functionalities of Bank Transactions.
- Key Functionalities include:
  - Viewing and editing customer details in the `BankTransaction` database.
  - Generating pdf statement for transaction for specific customer.
  - Send email if Aadhar details not updated with bank account.
  - List the customer login details.
  - List the customer added for given time.
  - Access to all databases, including `Customer`, `Account`, and `Transaction`.
### Customer login
![Customer login](pictures/customer.png)
- Customer can access the transaction like deposit,withdrawal.
- Key functionalities include:
   - check balance
   - Add,edit,remove nominee details
   - Deposit amount
   - Withdrawal amount
### PDF generation
![pdf](pictures/pdf.png)
- Monthly statement in the form of pdf 
# Conclusion
   The Bank Transaction System offers a comprehensive solution for managing banking operations efficiently and securely. With intuitive user interfaces and robust security measures, both customers and managers can conduct transactions with confidence while enjoying a seamless banking experience.

