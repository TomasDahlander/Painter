import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-30 <br>
 * Time: 17:23 <br>
 * Project: Painter <br>
 */
public class Database {

    private static final Database INSTANCE = new Database();

    private Database(){}

    public static Database getInstance(){
        return INSTANCE;
    }

    public void saveToMemory(Pixel[][] array, int slot){
        String fileName = "Savefiles\\Saved painting nr " + slot + ".ser";
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(new File(fileName)))){

            objectOut.writeObject(array);

        }catch(FileNotFoundException e){
            System.out.println("Could not find file.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not write to file");
        }
    }

    public Pixel[][] loadFromMemory(int slot){
        Pixel[][] pixels;
        String fileName = "Savefiles\\Saved painting nr " + slot + ".ser";
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(new File(fileName)))) {

            pixels = (Pixel[][])objectIn.readObject();
            return pixels;
        }catch(FileNotFoundException e) {
            System.out.println("Could not find file.");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.out.println("Could not find classType.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not write to file");
            e.printStackTrace();
        }
        return null;
    }
}