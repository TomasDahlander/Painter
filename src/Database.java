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

    public void saveLabelsToMemory(List<JLabel> labels){
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(new File("Saved labels.ser")))){

            objectOut.writeObject(labels);

        }catch(FileNotFoundException e){
            System.out.println("Could not find file.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not write to file");
        }
    }

    public void savePaintedToMemory(List<Boolean> painted){
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(new File("Saved painted.ser")))){

            objectOut.writeObject(painted);

        }catch(FileNotFoundException e){
            System.out.println("Could not find file.");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Could not write to file");
        }
    }

    public List<JLabel> loadLabelsFromMemory(){
        List<JLabel> labels = new ArrayList<>();
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(new File("Saved labels.ser")))) {

            labels = (ArrayList)objectIn.readObject();

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
        return labels;
    }

    public List<Boolean> loadPaintedFromMemory(){
        List<Boolean> painted = new ArrayList<>();
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(new File("Saved painted.ser")))) {

            painted = (ArrayList)objectIn.readObject();

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
        return painted;
    }

}
