package first;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class UserDetailsTest {
	
	@Test
    public void testIsValidEmail() {
        UserDetails userDetails = new UserDetails();
        assertFalse(userDetails.isValidEmail(null));
        assertFalse(userDetails.isValidEmail("invalidemail.com"));
        assertFalse(userDetails.isValidEmail("email@gmail"));
        assertFalse(userDetails.isValidEmail("email@gmail..com"));
        assertFalse(userDetails.isValidEmail("@.com"));
        assertFalse(userDetails.isValidEmail("@."));
        assertFalse(userDetails.isValidEmail("email@@gmail.com"));
        assertFalse(userDetails.isValidEmail("email@gmail..com"));
        assertFalse(userDetails.isValidEmail("@gmail.com"));
        assertFalse(userDetails.isValidEmail("email.com"));
        assertFalse(userDetails.isValidEmail("email@gmail."));
        assertFalse(userDetails.isValidEmail("email@.com"));
        assertFalse(userDetails.isValidEmail("email@gmail.1"));
        assertFalse(userDetails.isValidEmail("email@123.123"));
        assertFalse(userDetails.isValidEmail("1@2.3"));
        assertFalse(userDetails.isValidEmail("email@gmail.123"));
        assertFalse(userDetails.isValidEmail("email@.123"));
        assertTrue(userDetails.isValidEmail("123user@gmail.com"));    
        assertTrue(userDetails.isValidEmail("user@gmail.com"));
        assertTrue(userDetails.isValidEmail("user123@gmail.com"));
    }
	
	@Test
    public void testIsValidPassword() {
        UserDetails userDetails = new UserDetails();
     // password should be of length-6 and contain atleast 1 numeric value and atleast 1 alphabet
        assertFalse(userDetails.isValidPassword(null));               
        assertFalse(userDetails.isValidPassword("user"));             
        assertFalse(userDetails.isValidPassword("USER"));
        assertFalse(userDetails.isValidPassword("12345667"));
        assertFalse(userDetails.isValidPassword("acc12"));
        assertFalse(userDetails.isValidPassword("ACC12"));
        assertFalse(userDetails.isValidPassword("@123"));
        assertFalse(userDetails.isValidPassword("user@")); 
        assertTrue(userDetails.isValidPassword("Password@1"));
        assertTrue(userDetails.isValidPassword("PASSWORD@1"));
        assertTrue(userDetails.isValidPassword("user123"));
    }

    @Test
    public void testSignUp() {
    	UserDetails userDetails = new UserDetails();
    	assertFalse(userDetails.signUp(null, null));
    	assertFalse(userDetails.signUp("test@gmail.com", null));
        assertFalse(userDetails.signUp(null, "abc123"));
        assertFalse(userDetails.signUp("email", "abc123"));
        assertFalse(userDetails.signUp("email@.", "abc123"));
        assertFalse(userDetails.signUp("email@gmail.", "abc123"));
        assertFalse(userDetails.signUp("email.com@gmail", "abc123"));
        assertFalse(userDetails.signUp("@example.com", "Passw1"));
        assertFalse(userDetails.signUp("email@gmail.com", "123"));
        assertTrue(userDetails.signUp("test@gmail.com", "abc123"));
        assertTrue(userDetails.signUp("test@example.com", "56test@#$%^"));
        assertFalse(userDetails.signUp("test@gmail.com","123"));
        assertFalse(userDetails.signUp("invalidemail.com", "password"));
        assertTrue(userDetails.signUp("test123@example.com", "Passw#ord@1"));
        assertTrue(userDetails.signUp("test@gmail.com", "123abc"));       
    }

    @Test
    public void testLogin() {
        UserDetails userDetails = new UserDetails();
        userDetails.signUp("test@gmail.com", "user@123");
        assertFalse(userDetails.signUp(null, null));
    	assertFalse(userDetails.signUp("test@gmail.com", null));
        assertFalse(userDetails.signUp(null, "abc123"));
        assertTrue(userDetails.login("test@gmail.com", "user@123"));
        assertFalse(userDetails.login("test@gmail.com", "WrongPassword"));
        assertFalse(userDetails.login("wrong@gmail.com", "user@123"));
        assertFalse(userDetails.login("wrong@gmail.com", "incorrect@123"));
    }

    @Test
    public void testResetPassword() {
        UserDetails userDetails = new UserDetails();
        userDetails.signUp("test@example.com", "Password@1");
        assertFalse(userDetails.resetPassword(null, null));
        assertTrue(userDetails.resetPassword("test@example.com", "NewPassword@2"));
        assertTrue(userDetails.login("test@example.com", "NewPassword@2"));
        assertFalse(userDetails.resetPassword("invalidemail.com", "NewPassword@2"));
    }   
}

