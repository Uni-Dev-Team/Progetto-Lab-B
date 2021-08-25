package util;
/**
     * Generator Class
     *
     * @category Random
     * @author AndrewF17
     */
public class Generator {
     /**
     * generate AlphaNumericString
     * 
     * @param int dimention of the random string
     * @return a String
     * @author AndrewF17
     */
    static String getAlphaNumericString(int length) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(length); 
  
        for (int i = 0; i < length; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
}
