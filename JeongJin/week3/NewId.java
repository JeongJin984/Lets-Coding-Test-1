import java.util.*;

class Solution {
    
    public String solution(String new_id) {
        String answer = new_id;
        
        new_id = new_id.toLowerCase(Locale.ROOT);
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        new_id = new_id.replaceAll("[.]+", ".");
        new_id = new_id.replaceAll("^[.]|[.]$", "");

        if(new_id.equals("")) new_id = "a";
        if(new_id.length() > 15) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("[.]$", "");
        }

        while(new_id.length() < 3)
            new_id += new_id.charAt(new_id.length()-1);

        return new_id;
    }
}
