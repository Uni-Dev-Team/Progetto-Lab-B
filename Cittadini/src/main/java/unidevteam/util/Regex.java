package unidevteam.util;

public class Regex {
    public static Boolean check(String s, String regex) {
        return s.matches(regex);
    }

    // public static Boolean checkPsw(String s){
    //     // aggiungere regex default per pass
    //     String regex = "";
    //     return s.matches(regex);
    // }
}