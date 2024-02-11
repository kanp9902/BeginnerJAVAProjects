import java.util.Random;
import java.util.Scanner;

public class OTPGenerator 
{
    public static void main(String[] args) 
    {
        // Generate a random 6-digit OTP
        String otp = generateOTP();

        // Display the generated OTP
        System.out.println("Your OTP is: " + otp);

        // Ask the user to enter the OTP
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the OTP you received: ");
        String userEnteredOTP = scanner.nextLine();

        // Check if the entered OTP is valid
        boolean isValid = checkOTP(otp, userEnteredOTP);

        if (isValid) 
        {
            System.out.println("OTP is valid. Access granted!");
        } 
        else 
        {
            System.out.println("Invalid OTP. Access denied!");
        }
    }

    // Method to generate a random 6-digit OTP
    private static String generateOTP() 
    {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 6; i++) 
        {
            otp.append(random.nextInt(10)); // Generates a random digit (0-9)
        }

        return otp.toString();
    }

    // Method to check if the entered OTP is valid
    private static boolean checkOTP(String generatedOTP, String enteredOTP) 
    {
        return generatedOTP.equals(enteredOTP);
    }
}