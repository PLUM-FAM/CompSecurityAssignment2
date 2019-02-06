import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Driver
{
    public static void main(String[] args) 
    {
        //Creating a String that is named the name of our file
        String fileString = readFile(args[0]);

        //Creating a regular expression pattern and a matcher based on the file that is our argument
        Pattern pattern = Pattern.compile("%B([A-z]|[1-9])*\\/([A-z])+([0-9]){7}");
        Matcher match = pattern.matcher(fileString);

        //New arraylist of type string to add the strings that match our regular expression
        ArrayList<String> stringArray = new ArrayList<String>();

        //While there is a match in the string that matches our regular expression, add that string to our arraylist
        while (match.find()) 
        {
            stringArray.add(fileString.substring(match.start(), match.end()));
        }
        
        //Grammar rules: if there are more than two entries, use "There are", if there are less than 2, use "There is"
        if(stringArray.size() >= 2)
        {
            System.out.println("There are " + stringArray.size() + " tracks I records in the memory data" + "\n");
        }
        else
        {
            System.out.println("There is " + stringArray.size() + " track I record in the memory data" + "\n");
        }
        
        //Counter for number of strings in our arraylist, pass the count into our outputRecord method,
        //to use for the first line.
        int count = 0;        
        for (String record : stringArray)
        {
            outputRecord(stringArray.get(count), count);
            count++;            
        }        
        
    }

    /*
    * Read file is a helper method to read the characters in a text file and return a string of those characters. 
    * Helpful for our regular expressions to pull the specific data out.
    */            
    public static String readFile(String fileName)
    {
        String fileAsString = "";
        try{//IO exception
            try{//file not found exception
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                StringBuilder sb = new StringBuilder();

                String line = br.readLine();
                while (line != null) 
                {                                               
                    sb.append(line).append("\n");
                    line = br.readLine();
                }
                fileAsString = sb.toString();
                
            }catch(FileNotFoundException e){}
        }catch(IOException e){}
        return fileAsString;
    }

    /*
    *   outputRecord takes a record from the overall data and a record number and prints out the specific
    *   name, card number, expiration date, cvc code etc that we are looking for.
    */        
    public static void outputRecord(String data, int recordNumber)
    {
        System.out.println("<Information for the " + (recordNumber + 1) + " record>");
        String cardNumber = data.substring(2, 18);
        int length = data.length();
        String expDate = data.substring(length-7, length-3);
        String name = data.substring(19, length-8);
        String newName = name.replace("/", "");
        String cvc = data.substring(length-3, length);
        String month = expDate.substring(2,4);
        String year = expDate.substring(0,2);                    
        System.out.println("Cardholder's Name: " + newName);    
        System.out.println("Card Number: " + cardNumber);  
        System.out.println("Expiration Date: " + month + "/20" + year);    
        System.out.println("CVC Number: " + cvc);          
        System.out.println();                                                            
    }        
}