package first;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserDetails {
	
    private Map<String, String> users = new HashMap<>();
	
    public boolean isValidEmail(String email) {
        if(email != null && email.contains("@") && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
        	return true;
        }
        return false;       
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 6  && password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,20}$");
    }
	
    public boolean signUp(String email, String password) {       
        if (isValidEmail(email) && isValidPassword(password)) {
            users.put(email, password);     
            return true;
        }
        else {        	
        	return false;
        }               
    }

    public boolean login(String email, String password) {
        if (users.containsKey(email) && users.get(email).equals(password)) {     
        	return true;
        }
        else {       	
            return false;
        }
    }

    public boolean resetPassword(String email, String password) {       
        if (!users.containsKey(email)) {
            return false;           
        }
        if (isValidPassword(password)) {
            users.put(email, password);
            return true;
            
        }
        else {
        	return false;
        }
    }
	
    public static void main(String[] args) {
    	Scanner sc= new Scanner(System.in);
        UserDetails userDetail = new UserDetails();
        // SignUp : 
        System.out.println("For SignUp : ");
        System.out.println("Enter your email:");
        String email= sc.nextLine();  
        System.out.println("Enter your password:");        
        String password = sc.nextLine();
        boolean signUpStatus= userDetail.signUp(email, password);
        if(signUpStatus) {
        	System.out.println("Sign up successful !");
        }
        else {
        	System.out.println("Invalid UserName or Password !");
        	System.exit(0);
        }
        
        // Login : 
        System.out.println("For Login : ");
        System.out.println("Enter your email:");
        String email2= sc.nextLine();         
        System.out.println("Enter your password:");        
        String password2 = sc.nextLine();
        boolean loginStatus=userDetail.login(email2, password2);
        if(loginStatus) {
        	System.out.println("Login successful!");
            
        }
        else {
        	System.out.println("Invalid email or password. \n To reset password enter email id:");
        	//Reset Password :
        	System.out.println("Enter your email:");
            String email3= sc.nextLine();
            if(!userDetail.isValidEmail(email3) || !userDetail.users.containsKey(email3)) {
            	System.out.println("Invalid Email");
            	System.exit(0);
            };
            
            System.out.println("Reset Link sent to " + email3);      
            System.out.println("Enter your new password:");
            String password3 = sc.nextLine();
            
            if(userDetail.resetPassword(email3, password3)) {
            	System.out.println("Password reset successful!");
            }            
        }        
    }
}

