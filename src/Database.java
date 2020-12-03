import javax.swing.*;
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

    public void saveToMemory(List<Pixel> list){
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(new File("Saved painting.ser")))){

            objectOut.writeObject(list);

        }catch(FileNotFoundException e){
            System.out.println("Could not find file.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not write to file");
        }
    }

    public List<Pixel> loadFromMemory(){
        List<Pixel> pixels = new ArrayList<>();
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(new File("Saved painting.ser")))) {

            pixels = (ArrayList)objectIn.readObject();

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
        return pixels;
    }
}
