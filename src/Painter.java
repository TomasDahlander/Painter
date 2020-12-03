import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-30 <br>
 * Time: 14:14 <br>
 * Project: Painter <br>
 */
public class Painter extends JFrame {

    Database database = new Database();
    Window window = new Window();
    ColorController controller = new ColorController();
    Color color = Color.WHITE;
    boolean pressed = false;

    public Painter(){
        add(window);

        setUpColorChooserListener();
        setUpLabelWriterListener();
        setUpCleaningButtonListener();
        setUpFillButtonListener();
        setUpSaveAndLoadButtonListener();
        setUpCustomColorButtonSetterListener();
        setUpCustomColorButtonGettersListener();
        setUpFrame();
    }

    public void setUpColorChooserListener(){
        for (var e : window.getColorButtons()) {
            e.addActionListener(l -> {
                color = e.getBackground();
                window.getChosenColor().setBackground(color);
            });
        }
    }

    public void setUpLabelWriterListener(){
        for (var l : window.getPixels()){
            MouseAdapter writer = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                    l.getPixel().setBackground(color);
                    l.setPainted(true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (pressed) {
                        l.getPixel().setBackground(color);
                        l.setPainted(true);
                    }
                }
            };
            l.getPixel().addMouseListener(writer);
        }
    }

    public void setUpCleaningButtonListener(){
        window.getCleanButton().addActionListener(l -> {
            window.clearAll();
            window.revalidate();
            window.repaint();
        });
    }

    public void setUpFillButtonListener(){
        window.getFillButton().addActionListener(l -> {
            window.fillAll(color);
        });
    }

    public void setUpSaveAndLoadButtonListener(){
        window.getSaveButton().addActionListener(l -> {
            database.saveToMemory(window.getPixels());
        });

        window.getLoadButton().addActionListener(l -> {
            List<Pixel> pixelsFromMemory = database.loadFromMemory();
            window.writePixelsFromMemory(pixelsFromMemory);
        });
    }

    public void setUpCustomColorButtonSetterListener(){
        window.getSetCustomColor1().addActionListener(l -> {

            int r = controller.checkInput(window.getColorRed());
            int g = controller.checkInput(window.getColorGreen());
            int b = controller.checkInput(window.getColorBlue());

            window.getGetCustomColor1().setBackground(controller.checkColor(r,g,b));
        });
        window.getSetCustomColor2().addActionListener(l -> {

            int r = controller.checkInput(window.getColorRed());
            int g = controller.checkInput(window.getColorGreen());
            int b = controller.checkInput(window.getColorBlue());

            window.getGetCustomColor2().setBackground(controller.checkColor(r,g,b));
        });
    }

    public void setUpCustomColorButtonGettersListener(){
        window.getGetCustomColor1().addActionListener(l -> {
            color = window.getGetCustomColor1().getBackground();
            window.getChosenColor().setBackground(color);
        });

        window.getGetCustomColor2().addActionListener(l -> {
            color = window.getGetCustomColor2().getBackground();
            window.getChosenColor().setBackground(color);
        });
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
