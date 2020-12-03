import javax.swing.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-30 <br>
 * Time: 14:14 <br>
 * Project: Painter <br>
 */
public class Painter extends JFrame {

    Window window = new Window();
    ActionController actionController;

    public Painter(){
        add(window);
        actionController = new ActionController(window);
        setUpListeners();

        setUpFrame();
    }

    public void setUpListeners(){
        actionController.setUpColorChooserListener();
        actionController.setUpLabelWriterListener();
        actionController.setUpCleaningButtonListener();
        actionController.setUpFillButtonListener();
        actionController.setUpSaveAndLoadButtonListener();
        actionController.setUpCustomColorButtonSetterListener();
        actionController.setUpCustomColorButtonGettersListener();
    }

    public void setUpFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Painter();
    }
}
