import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-03 <br>
 * Time: 18:58 <br>
 * Project: Painter <br>
 */
public class Controller {

    private Window window;
    private Database database = new Database();
    private Color color = Color.WHITE;
    private ColorValidater colorValidater = new ColorValidater();
    private boolean pressed;
    private int slot = 1;
    private boolean eraserMode = true; // True = eraser is not on. False = it will set painted to false.

    public Controller(Window window){
        this.window = window;
        setUpColorChooserListener();
        setUpLabelWriterListener();
        setUpCleaningButtonListener();
        setUpFillButtonListener();
        setUpSaveAndLoadButtonListener();
        setUpCustomColorButtonSetterListener();
        setUpCustomColorButtonGetterListener();
        setUpSpinnerListener();
        setUpEraser();
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
                    l.setPainted(eraserMode);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    pressed = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (pressed) {
                        l.getPixel().setBackground(color);
                        l.setPainted(eraserMode);
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
            database.saveToMemory(window.getPixels(),slot);
        });

        window.getLoadButton().addActionListener(l -> {
            try {
                List<Pixel> pixelsFromMemory = database.loadFromMemory(slot);
                window.writePixelsFromMemory(pixelsFromMemory);
            }catch(Exception e){
                // File could not be found and this is handled in the database.
            }
        });
    }

    public void setUpCustomColorButtonSetterListener(){
        window.getSetCustomColor1().addActionListener(l -> {

            int r = colorValidater.checkInput(window.getColorRed());
            int g = colorValidater.checkInput(window.getColorGreen());
            int b = colorValidater.checkInput(window.getColorBlue());

            window.getGetCustomColor1().setBackground(colorValidater.checkColor(r,g,b));
        });
        window.getSetCustomColor2().addActionListener(l -> {

            int r = colorValidater.checkInput(window.getColorRed());
            int g = colorValidater.checkInput(window.getColorGreen());
            int b = colorValidater.checkInput(window.getColorBlue());

            window.getGetCustomColor2().setBackground(colorValidater.checkColor(r,g,b));
        });
    }

    public void setUpCustomColorButtonGetterListener(){
        window.getGetCustomColor1().addActionListener(l -> {
            color = window.getGetCustomColor1().getBackground();
            window.getChosenColor().setBackground(color);
        });

        window.getGetCustomColor2().addActionListener(l -> {
            color = window.getGetCustomColor2().getBackground();
            window.getChosenColor().setBackground(color);
        });
    }

    public void setUpSpinnerListener(){
        window.getSpinner().addChangeListener(l -> {
            slot = Integer.parseInt(window.getSpinner().getValue()+"");
        });
    }

    public void setUpEraser(){
        window.getEraser().addActionListener(l -> {
            if(window.getEraser().isSelected()){
                eraserMode = false;
                window.getEraser().setText("Eraser mode on");
            }
            else {
                eraserMode = true;
                window.getEraser().setText("Eraser mode off");
            }
        });
    }
}