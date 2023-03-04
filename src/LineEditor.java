import java.io.File;
import java.util.Scanner;

public class LineEditor 
{
    private static LineList lineList = new LineList();
    private static Scanner keyboard = new Scanner(System.in);
    public static void printCommands()
    {
        try {
            File menuFile = new File("./lib/menuCommands.txt");
            System.out.println(menuFile.getAbsolutePath());
            Scanner reader = new Scanner(menuFile);
            while(reader.hasNextLine())
            {
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Something wrong with printCommands...");
        }
    }
    public static void main(String[] args) {
        System.out.println("");
        String commandLine;
        String[] commandArr;
        boolean editorOn = true;
        printCommands();

        do {
            commandLine = keyboard.nextLine();
            commandArr = commandLine.split(" ");
            switch(commandArr[0].toLowerCase())
            {
                case "m":
                    printCommands();
                    break;
                case "load":
                    boolean append = false;
                    if (commandArr[2] == "0") append = false;
                    else if(commandArr[2] == "1") append = true;
                    lineList.load(commandArr[1], append);
                    break;
                case "p":
                    lineList.print();
                    break;
                case "lines":
                    System.out.println("There are "+lineList.countLines()+" lines.");
                    break;
                case "line":
                    System.out.println(lineList.displayNthLine(Integer.parseInt(commandArr[1])));
                    break;
                case "words":
                    System.out.println("There are "+lineList.words()+" words.");
                    break;
                case "del":
                    lineList.delete(Integer.parseInt(commandArr[1]));
                    break;
                case "a":
                    String stringToAppend = "";
                    for(int i = 1; i < commandArr.length; i++)
                    {
                        stringToAppend += commandArr[i] + " ";
                    }
                    lineList.addLine(stringToAppend);
                    break;
                case "i":
                    stringToAppend = "";
                    for(int i = 2; i < commandArr.length; i++)
                    {
                        stringToAppend += commandArr[i] + " ";
                    }
                    lineList.addLine(stringToAppend, Integer.parseInt(commandArr[1]));
                break;
                case "cls":
                    lineList.empty();
                    break;
                case "rep":
                    lineList.replace(commandArr[1], commandArr[2]);
                    break;
                case "s":
                    System.out.println("What would you like to save your file as?");
                    String saveFileName = keyboard.nextLine();
                    lineList.save(saveFileName);
                    break;
                case "quit":
                    editorOn = false;
                    break;
                default:
                    System.out.println("Bad data...");
                    break;
            }
            
        } while (editorOn);
    }    
}
