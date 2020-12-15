import javax.swing.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-30 <br>
 * Time: 14:14 <br>
 * Project: Painter <br>
 */
public class Painter extends JFrame {

    Window window = new Window();
    Controller controller;

    public Painter(){
        add(window);
        controller = new Controller(window);

        setUpFrame();
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
