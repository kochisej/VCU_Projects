import java.io.*;
import java.util.*;
 
 public class MemoPadCreator {
   
   public static void main(String[] args) throws FileNotFoundException {
     String dateStamp;
     Scanner console = new Scanner(System.in);
     System.out.print("Enter the name of the output file: ");
     String filename = console.nextLine();
     PrintWriter out = new PrintWriter(filename);
     boolean done = false;
     while (!done){
       System.out.println("Memo topic (enter -1 to end):");
       String topic = console.nextLine();
       if (topic.equals("-1")){
         done = true;
       }
       else {
         System.out.println("Memo text:");
         String message = console.nextLine();
         // Instantiate the Date object (dateStamp) here
         // using the new operator
         Date now = new Date();
         dateStamp = now.toString();
         out.println(topic + "\n" + dateStamp + "\n" + message);
       }
      }
     out.close();
     console.close(); // Close the output file
   }
 }
