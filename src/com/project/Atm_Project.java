package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Atm_Project {

	public static void main(String[] args) {
		/*
		 * In the ATM program, the user has to select an option from the options displayed on the screen. 
		 * 1 .The options are related to withdraw the money, deposit the money, check the balance, and exit.
		 * 2 .To check balance, we simply print the total balance of the user.
		 * 
		 * 3.To withdraw the money, we simply get the withdrawal amount from the user and
		 *   remove that amount from the total balance updated to database and print the successful message.
		 * 4.To deposit the money, we simply get the deposit amount from the user, 
		 *   add it to the total balance and update the deposit amount to the database
		 *   and print the successful message.
		 * 5. if the user enter the wrong pin ,then give the message 
         */
		try {
			//JDBC Connection	
			
			String url="jdbc:mysql://localhost:3306/atm";
			String username="root";
			String pass="Selva3998@";	
//			Class.forName("com.mysql.jdbc.Driver");
			
			//Establish the connection
			Connection con=DriverManager.getConnection(url, username, pass);
			//Create statement for execute the SQL query
			Statement stmt=con.createStatement();
			Scanner scanner=new Scanner(System.in);
			System.out.println("Welcome to MINI ATM!!");
			System.out.println("Enter your Pin number");
			 //we have to taken an input by an user
			int pin=scanner.nextInt();
			ResultSet rs=stmt.executeQuery("select * from customer_details where acc_no="+pin);
			String name;
			int balance=0;
			int count=0;
			while(rs.next())
			{
				name=rs.getString(3)+"balance"+rs.getInt(4);
				
				count++;
			}
	
        int addAmount=0;
        int takeAmount=0;
      
      if(count>0)
      {
    	  System.out.println("Enter your Name");
    	  name=scanner.next();
    	  System.out.println("Welcome "+ name);
    	  while(true)
    	  {
    		  System.out.println("Press 1 to check your balance");
    		  System.out.println("Press 2 to add amount ");
    		  System.out.println("Press 3 to take amount");
    		  System.out.println("Press 4 to take receipt");
    		  System.out.println("press 5 to EXIT");
    		  System.out.println("===========================================");
    		  
    		  int opt=scanner.nextInt();
    		  switch(opt)
    		  {
    		  case 1:
    			  ResultSet balanceAmount=stmt.executeQuery("select balance from customer_details where acc_no ="+pin);
    			  while(balanceAmount.next()) {
    			  System.out.println("Your current balance :"+balanceAmount.getInt("balance"));
    			  }
    			  break;
    		  case 2:
    			  System.out.println("How much amount did your want add?");
    			  addAmount=scanner.nextInt();
    			  System.out.println("Successfully Credited");
    			  ResultSet  afterAddAmount=stmt.executeQuery("select balance from customer_details where acc_no ="+pin);
    			  while(afterAddAmount.next()) {
    			  balance=addAmount+afterAddAmount.getInt("balance");
    			  }
    			  int bal=stmt.executeUpdate("update customer_details set balance ="+ balance +" where acc_no="+pin);
    			  //10000=100+10000
    			  //10100
    			  break;
    		  case 3:
    			  System.out.println("How much amount did you want take? ");
    			  takeAmount=scanner.nextInt();
    			  if(takeAmount>balance)
    			  {
    				  System.out.println("your balance is insuffient");
    				  System.out.println("try less than your available balance");
    				  
    			  }
    			  else
    			  {
    				  System.out.println("successfully taken");
    				  balance=balance - takeAmount;
    				   int sub=stmt.executeUpdate("update customer_details set balance ="+ balance +" where acc_no="+pin);
    				  //balance=10100-100
    				  //balance=10000
    			  }
    			  break;
    		  case 4:
    			  System.out.println("Welcome to  MINI ATM");
    			  System.out.println("Available balance is "+balance);
    			  System.out.println("Amount deposited "+addAmount);
    			  System.out.println("Amount taken "+ takeAmount);
    			  System.out.println("Thanks for Coming ");
    			  System.out.println("===========================================");
    			  break;
    			  
    			  
    		  }
    		  if(opt==5)
    		  {
    			  System.out.println("Thankyou");
    			  break;
    		  }
    	  }
      }
      else
      {
    	  System.out.println("Wrong pin number ");
    	  
      }
      scanner.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
      
	}

	}


