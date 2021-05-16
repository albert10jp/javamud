package JavaMUDV002;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);
    public static void printL(String str){
        System.out.println(str);
    }

    public static void pln(Object str){
        System.out.println(str);
    }

    public static int readInt(String prompt, int userChoice){
        int input;
        do{
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            }catch (Exception e) {
                input = -1;
                System.out.println("Please enter an integer!");
            }

        }while (input<1||input>userChoice);
        return input;
    }

    public static int makeAChoice(String[] choices){
        if(choices.length>0){
            for(int i = 1;i<=choices.length;i++){
                pln("(" + i + ") " + choices[i-1]);
            }
            int input = Util.readInt("-> ", choices.length);
            return input;
        }
        return 0;
    }

    public static void clearConsole(){
        String osName = System.getProperty("os.name");
        if(osName.toLowerCase().contains("windows")){
            for(int i=0;i<100;i++)
                System.out.println();
        }else {
            System.out.print("\33[2J\33[1;1H");
            System.out.println("\33[2J\33[1;1H");
        }
    }

    public static void printSeperator(int n){
        for(int i=0;i<n;i++)
            System.out.print("- ");
        System.out.println();
    }

    public static void printHeading(String title){
        printSeperator(30);
        System.out.println(title);
        printSeperator(30);
    }

    public static void anythingToContinue(){
        System.out.println("\n Enter anything to continue...");
        scanner.next();
    }

    public static boolean serializeHero(Hero player){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("gamedb");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(player);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static Hero deserializeHero() {

        File file = new File("gamedb");
        if(!file.exists()){
            return null;
        }
        Hero player = null;
        try {
            FileInputStream fileIn =
                    new FileInputStream("gamedb");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            player = (Hero)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Hero class not found");
            c.printStackTrace();
            return null;
        }
        return player;
    }

}
