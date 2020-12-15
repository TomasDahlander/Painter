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
    private int r = 0;
    private int g = 0;
    private int b = 0;
    private boolean pressed;
    private int slot = 1;
    private boolean eraserMode = true; // When false it will set painted to false.

    public Controller(Window window){
        this.window = window;
        setUpColorChooserListener();
        setUpLabelWriterListener();
        setUpCleaningButtonListener();
        setUpFillButtonListener();
        setUpSaveAndLoadButtonListener();
        setUpSliderListeners();
        setUpCustomColorButtonListener();
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
        window.getClearButton().addActionListener(l -> {
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

    public void setUpSliderListeners(){
        window.getSliderRed().addChangeListener(l ->{
            r = window.getSliderRed().getValue();
            window.getGetCustomColorButton().setBackground(new Color(r,g,b));
            window.getSliderRed().repaint();
        });
        window.getSliderGreen().addChangeListener(l ->{
            g = window.getSliderGreen().getValue();
            window.getGetCustomColorButton().setBackground(new Color(r,g,b));
            window.getSliderGreen().repaint();
        });
        window.getSliderBlue().addChangeListener(l ->{
            b = window.getSliderBlue().getValue();
            window.getGetCustomColorButton().setBackground(new Color(r,g,b));
            window.getSliderBlue().repaint();
        });

    }

    public void setUpCustomColorButtonListener(){
        window.getGetCustomColorButton().addActionListener(l->{
            color = window.getGetCustomColorButton().getBackground();
            window.getChosenColor().setBackground(color);
        });
    }

    public void setUpSpinnerListener(){
        window.getSpinner().addChangeListener(l -> {
            slot = Integer.parseInt(String.valueOf(window.getSpinner().getValue()));
        });
    }

    public void setUpEraser(){
        window.getEraserButton().addActionListener(l -> {
            if(window.getEraserButton().isSelected()){
                eraserMode = false;
                window.getEraserButton().setText("Eraser mode on");
            }
            else {
                eraserMode = true;
                window.getEraserButton().setText("Eraser mode off");
            }
        });
    }
}