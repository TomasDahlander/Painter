import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-03 <br>
 * Time: 18:58 <br>
 * Project: Painter <br>
 */
public class Controller {

    private Window window;
    private Database database = Database.getInstance();
    private Color color = Color.WHITE;
    private int r = 0;
    private int g = 0;
    private int b = 0;
    private boolean pressed; // If the left mousebutton is pressed or not
    private int slot = 1;
    private boolean eraserMode = true; // When false it will set painted to false.
    private boolean rectangleMode = false; // When true rectangle will be drawn.
    private Grid startGrid;
    private Grid endGrid;

    public Controller(Window window){
        this.window = window;
        setUpColorChooserListener();
        setUpLabelWriterListener();
        setUpClearingButtonListener();
        setUpFillButtonListener();
        setUpSaveAndLoadButtonListener();
        setUpSliderListeners();
        setUpCustomColorButtonListener();
        setUpSpinnerListener();
        setUpEraser();
        setUpRectangleButtonListener();
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
        for (int i = 0; i < window.getRow(); i++){
            for (int j = 0; j < window.getCol(); j++){
                int finalI = i;
                int finalJ = j;
                MouseAdapter writer = new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(rectangleMode){
                            pressed = true;
                            startGrid = new Grid(window.getPixels()[finalI][finalJ].getGrid());
                            System.out.println("Start: " + startGrid.getRow() + " - " + startGrid.getCol());
                        }
                        else {
                            pressed = true;
                            window.getPixels()[finalI][finalJ].getPixel().setBackground(color);
                            window.getPixels()[finalI][finalJ].setPainted(eraserMode);
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(rectangleMode){
                            pressed = false;
                            window.writeRectangle(startGrid, endGrid, color);
                        }
                        else pressed = false;
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (!rectangleMode && pressed){
                            window.getPixels()[finalI][finalJ].getPixel().setBackground(color);
                            window.getPixels()[finalI][finalJ].setPainted(eraserMode);
                        }
                        else if(rectangleMode && pressed){
                            endGrid = new Grid(window.getPixels()[finalI][finalJ].getGrid());
                        }
                    }
                };
                window.getPixels()[finalI][finalJ].getPixel().addMouseListener(writer);
            }
        }
    }

    public void setUpClearingButtonListener(){
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
                Pixel[][] pixelsFromMemory = database.loadFromMemory(slot);
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

    public void setUpRectangleButtonListener(){
        window.getDrawRectangleButton().addActionListener(l -> {
            if (window.getDrawRectangleButton().isSelected()){
                rectangleMode = true;
                window.getDrawRectangleButton().setText("Rectangle mode on");
            }
            else {
                rectangleMode = false;
                window.getDrawRectangleButton().setText("Rectangle mode off");
                System.out.println("Grid is in same place: " + startGrid.equals(endGrid));
            }
        });
    }


}