package com.driver;

public class Email {

    private String emailId;
    private String password;
    private boolean containsUppercase;
    private boolean containsLowercase;
    private boolean containsDigit;
    private boolean containsSpecialChar;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(password.equals(oldPassword)) {
             if(newPassword.length()<8) return;
             for(int i =0;i<newPassword.length();i++){
                 int val = newPassword.charAt(i);
                 if(val>=65 && val<91){
                     containsUppercase = true;
                 }
                 else if(val>=48 && val<58){
                     containsDigit = true;
                 }
                 else if(val>=97 && val<123){
                     containsLowercase = true;
                 }
                 else{
                     containsSpecialChar = true;
                 }
             }
             if(containsSpecialChar && containsLowercase && containsUppercase && containsDigit){
                 this.password = newPassword;
             }
        }
    }
}
