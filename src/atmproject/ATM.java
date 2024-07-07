package atmproject;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","#","#");
			Statement smt = cn.createStatement();
			Scanner sc = new Scanner(System.in);
			while(true) {
			
			System.out.println("Welcome to JAVA ATM Machine ");
			System.out.println("Enter Your PIN Number or 0 for Exit ");
			int pin  = sc.nextInt();
			
			
			ResultSet rs = smt.executeQuery("select *from ATM where PIN="+pin);
			String name = null;
			int count =0;
			int Balance = 0;
			while(rs.next()) {
				name = rs.getString(3);
			    Balance = rs.getInt(4);
				count++;
				
			}
			
			if(pin == 0) {
				System.out.println("Exit");
				break;
			}
			
			
			int newpin = 0;
			
			
			int choice;
			int add_amt = 0;
			int takeamt = 0;
			
			if (count>0) {
				System.out.println("Hello " + name);
				while(true) {
				System.out.println("Option 1 for Balance Enquiry");
				System.out.println("Option 2 for Add Amount");
				System.out.println("Option 3 for Take Amount");
				System.out.println("Option 4 for Change Pin Number");
				System.out.println("Option 5 for Print Recepit");
				System.out.println("Option 6 for Exit");
				
				
				System.out.println();
				System.out.println("Enter Your Choice: ");
				choice=sc.nextInt();
				
				
				switch(choice) {
				case 1:
					System.out.println("Your Current balace is: " + Balance);
					System.out.println();
					break;
				case 2:
					System.out.println("How much amount you want to Add: ");
					add_amt = sc.nextInt();
					Balance = Balance + add_amt;
					int bal = smt.executeUpdate("UPDATE ATM SET BALANCE_AMT = " + Balance + "WHERE PIN = " + pin);
					System.out.println("Amount successfully Added and Now Your current balance is = " + Balance);
					System.out.println();
					break;
				
				case 3: 
					System.out.println("How much amount you want to take: ");
					takeamt = sc.nextInt();
					if(takeamt < Balance) {
					Balance = Balance - takeamt;
					int dep = smt.executeUpdate("UPDATE ATM SET BALANCE_AMT = " + Balance + "WHERE PIN = " + pin);
					System.out.println("Amount successfully Taken and Now Your current Balance is ="+ Balance);
					System.out.println();
					}else {
						System.out.println("Insufficient balance");
						System.out.println();
					}
					break;
					
				case 4:
					System.out.println("Enter the New Pin: ");
					newpin = sc.nextInt();
					int changedpin = smt.executeUpdate("UPDATE ATM SET PIN = " + newpin);
					System.out.println("Pin Was Changed Successfully");
					System.out.println();
					break;
					
				case 5:
					System.out.println("Thanks for Coming.......!");
					
					System.out.println("Taked Amount = " + takeamt);
					System.out.println("Amount Added = "+ add_amt);
					
					System.out.println("Current Balance = " + Balance);
					System.out.println();
					break;
				}
				if(choice == 6) {
					System.out.println("EXIT....Welcome Again");
					System.out.println();
					break;
					
				}
				
			}
			}
		
			else {
				System.out.println("Wrong PIN Number. EXIT......!");
				System.out.println();
			}
			
			
			
		}
			sc.close();
		}
			catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}
