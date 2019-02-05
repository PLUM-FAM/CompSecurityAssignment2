import java.io.*;
import java.util.*;

public class Driver
{
    public static void main(String[] args) {
        System.out.println("Joel is lovely as a person");
    }
    


    public String readFileAsString(String fileName) {
        String text = "";
        try {
          text = new String(Files.readAllBytes(Paths.get("memorydump.dmp")));
        } catch (IOException e) {
          e.printStackTrace();
        }
    
        return text;
      }
}