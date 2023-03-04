import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class LineList{
   
   private Node head;
   
   public LineList(){   
      head=null;
   }
  
  
   public void empty(){
      	// delete all lines in the editor
         head=null;
   }

   public int countLines()
   {
         Node tempNode = head;
         int lineCounter = 0;

         while (tempNode != null) {
            tempNode = tempNode.getNext();
            lineCounter++;
         }

         return lineCounter;
   }
   public void load(String fileName, boolean append){
      	// append is true, read lines and add to existing list, 
      	// otherwise, create new list.
	      // Each line is stored in a Node.
	      // You may need to handle exception within this method
	      // or throw runtime exception only.

         try {
            Scanner scanner = new Scanner(new File("./lib/"+fileName));

            if (append)
            {
               while(scanner.hasNextLine())
               {
                  addLine(scanner.nextLine());
               }
            }
            else
            {
               empty();
               while(scanner.hasNextLine())
               {
                  addLine(scanner.nextLine());
               }
            }

            scanner.close();

         } catch (Exception e) {
            System.out.println("Something went wrong loading the file...");
         }

   }
   
   public void save(String fileName){
      	// Save all lines represented with Nodes to a file. 
         // Each line (Node) occupies a line in the saved file.
         // You may need to handle exception within this method
         // or throw runtime exception only.
         try {
            Node writerNode = head;
            FileWriter writer = new FileWriter(new File(fileName));
            
            while(writerNode != null)
            {
               writer.write(writerNode.getLine()+"\n");
               writerNode = writerNode.getNext();
            }

            writer.close();
         } catch (Exception e) {
            System.out.println("Something went wrong saving the file...");
         }

   }
   
   public void addLine(String line){
      	//append the line to the end of the list
         Node newLine = new Node(line);
         if (head == null)
         {
            head = newLine;
         }
         else if (head.getNext() == null)
         {
            head.setNext(newLine);
         }
         else
         {
            Node newNode = head;
            head = head.getNext();
            addLine(line);
            head = newNode;
         }

   }

   public void addLine(String line, int n){
      	//insert new line to nth line, if n > total number of line, 
      	//append to the end of the list.
         if (lines() < n) {
            addLine(line);
         }
         else {
            Node nodeCopy = head;
            int oldSize = lines();
            empty();
            for (int i = 0; i < (n - 1); i++) {
               String grabbedLine = nodeCopy.getLine();
               nodeCopy = nodeCopy.getNext();
               addLine(grabbedLine);
            }
            addLine(line);
            oldSize++;
            for (int i = n; i < oldSize; i++) {
               String grabbedLine = nodeCopy.getLine();
               nodeCopy = nodeCopy.getNext();
               addLine(grabbedLine);
            }
         }
   }
   
   public int words(){
      	// count number of words, word may be separated with 
      	// \t,;.?!-@spaces ' * and "
         int wordCount = 0;
         Node tempNode = head;
         while (tempNode != null) {
            String line = tempNode.getLine();
            String lineArr[] = line.split("[^a-zA-Z0-9]+");
            wordCount += lineArr.length;
            tempNode = tempNode.getNext();
         }

         return wordCount;
   }

   public int lines(){ 
      	// count number of lines, which is the same as the number of
      	// nodes in the list.
         Node newNode = head;
         int count = 0;
         if (newNode == null)
            return count;
         else {
            while (newNode != null){
               count++;
               newNode = newNode.getNext();
            }
         }

         return count;
   }

   public void delete(int n){
      	//delete nth line if exists. Otherwise do nothing.
         Node nodeCopy = head;
         int oldSize = lines();
         empty();
         for (int i = 0; i < (n - 1); i++) {
            String grabbedLine = nodeCopy.getLine();
            nodeCopy = nodeCopy.getNext();
            addLine(grabbedLine);
         }
         nodeCopy = nodeCopy.getNext();
         for (int i = n; i < oldSize; i++) {
            String grabbedLine = nodeCopy.getLine();
            nodeCopy = nodeCopy.getNext();
            addLine(grabbedLine);
         }
   }

   public String displayNthLine(int n)
   {
      Node newNode = head;
         if (newNode == null)
            return "null";
         else {
            for (int i = 0; i < n-1; i++)
            {
               newNode = newNode.getNext();
            }
         }

         return newNode.getLine();
   }
   public String toString(){
      	// Return all lines represented by Nodes in the list. All lines
      	// are in the same order as their corresponding nodes in the list.
      	// All lines are separated with \n. No newline character should be 
	      // added after the last line.
         Node newNode = head;
         String nodeString = "";

         if (newNode == null)
            return nodeString;

         for (int i = 0; i < lines() - 1; i++) {
            nodeString = nodeString + newNode.getLine() + "\n";
            newNode = newNode.getNext();
         }
         nodeString = nodeString + newNode.getLine();
         return nodeString;
     
   }

   public void print(){
      	// Print each line. Each line is proceeded with its corresponding line 
      	// numbers. Please refer to sample output.
         Node tempNode = head;
         int lineNumber = 1;
         System.out.println("---------------------");
         while (tempNode != null) {
            System.out.println(lineNumber + ". " + tempNode.getLine());
            tempNode = tempNode.getNext();
            lineNumber++;
         }
         System.out.println("---------------------");

   }
   
   public void replace(String s1, String s2){
      	// Replace all occurrences of s1 with s2.
         Node nodeCopy = head;
         int size = lines();
         empty();
         for (int i = 0; i < size; i++) {
            nodeCopy.replace(s1, s2);
            addLine(nodeCopy.getLine());
            nodeCopy = nodeCopy.getNext();
         }

   }
   
   public void line(int n){
      	// Print nth line in the document. (The nth node in the list)
      	// If nth line does not exists, print �Line n does not exist.�
         Node newNode = head;
         if (n > lines()) {
            System.out.print("Line ");
            System.out.println(n + " does not exist.");
         }
         else {
            for (int i = 0; i < (n - 1); i++) {
               newNode = newNode.getNext();
            }
            System.out.println(newNode.getLine());
         }
   

   }
}
