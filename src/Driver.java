import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Driver
{
    public static void main(String[] args) 
    {
        String fileString = readFile(args[0]);
        Pattern pattern = Pattern.compile("%B([A-z]|[1-9])*\\/([A-z])+([0-9]){7}");
        Matcher match = pattern.matcher(fileString);

        ArrayList<String> stringArray = new ArrayList<String>();
        while (match.find()) 
        {
            stringArray.add(fileString.substring(match.start(), match.end()));
        }
        
        if(stringArray.size() >= 2)
        {
            System.out.println("There are " + stringArray.size() + " tracks I records in the memory data" + "\n");
        }

        else
        {
            System.out.println("There is " + stringArray.size() + " track I record in the memory data" + "\n");
        }
        int count = 0;        
        for (String record : stringArray)
        {
            outputRecord(stringArray.get(count), count);
            count++;            
        }        
        
    }

    
    public static String readFile(String fileName)
    {
        String fileAsString = "";
        try{
            try{
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