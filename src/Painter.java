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
        for (int i = 0; i < window.getLabels().size(); i++) {
            int finalI = i;
            MouseAdapter writer = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    pressed = true;
                    window.getLabels().get(finalI).setBackground(color);
                    window.getPainted().set(finalI,true);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(pressed) {
                        window.getLabels().get(finalI).setBackground(color);
                        window.getPainted().set(finalI,true);
                    }
                }
            };
            window.getLabels().get(finalI).addMouseListener(writer);
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
            database.saveLabelsToMemory(window.getLabels());
            database.savePaintedToMemory(window.getPainted());
        });

        window.getLoadButton().addActionListener(l -> {
            List<JLabel> labelsFromMemory = database.loadLabelsFromMemory();
            List<Boolean> paintedFromMemory = database.loadPaintedFromMemory();

            window.writeColorsFromMemory(labelsFromMemory);
            window.readPaintedFromMemory(paintedFromMemory);
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
