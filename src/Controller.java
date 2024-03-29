import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-03 <br>
 * Time: 18:58 <br>
 * Project: Painter <br>
 */
public class Controller {

    private final Window window;
    private final Database database = Database.getInstance();
    private final RectangleFactory factory = new RectangleFactory();
    private Color color = Color.WHITE;
    private int r = 0;
    private int g = 0;
    private int b = 0;
    private boolean mouseIsPressed;
    private int slot = 1;
    private boolean eraserModeOff = true; // When false it will set painted to false.
    private boolean rectangleModeOn = false; // When true rectangle will be drawn.
    private int rectangleMode = 1;

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
        setUpRectangleModeRadioButtonListener();
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
                MouseAdapter writer = createMouseAdapter(i,j);
                window.getPixels()[i][j].getPixel().addMouseListener(writer);
            }
        }
    }

    public MouseAdapter createMouseAdapter(final int i, final int j){
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(rectangleModeOn){
                    mouseIsPressed = true;
                    startGrid = new Grid(window.getPixels()[i][j].getGrid());
                }
                else {
                    mouseIsPressed = true;
                    window.getPixels()[i][j].paint(color, eraserModeOff);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!rectangleModeOn && mouseIsPressed){
                    window.getPixels()[i][j].paint(color, eraserModeOff);
                }
                else if(rectangleModeOn && mouseIsPressed){
                    endGrid = new Grid(window.getPixels()[i][j].getGrid());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(rectangleModeOn){
                    mouseIsPressed = false;
                    Rectangle rectangle = factory.getRectangle(rectangleMode,startGrid, endGrid);
                    rectangle.draw(window.getPixels(),color,eraserModeOff);
                }
                else mouseIsPressed = false;
            }
        };
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
                eraserModeOff = false;
                window.getEraserButton().setText("Eraser mode on");
            }
            else {
                eraserModeOff = true;
                window.getEraserButton().setText("Eraser mode off");
            }
        });
    }

    public void setUpRectangleButtonListener(){
        window.getDrawRectangleButton().addActionListener(l -> {
            if (window.getDrawRectangleButton().isSelected()){
                rectangleModeOn = true;
                window.getDrawRectangleButton().setText("Rectangle mode on");
            }
            else {
                rectangleModeOn = false;
                window.getDrawRectangleButton().setText("Rectangle mode off");
            }
        });
    }

    public void setUpRectangleModeRadioButtonListener(){
        window.getFullRectangleButton().addActionListener(l -> {
            rectangleMode = RectangleFactory.FULL_RECTANGLE;
        });

        window.getHollowRectangleButton().addActionListener(l -> {
            rectangleMode = RectangleFactory.HOLLOW_RECTANGLE;
        });

        window.getCheckedRectangleButton().addActionListener(l -> {
            rectangleMode = RectangleFactory.CHECKED_RECTANGLE;
        });

        window.getLayeredRectangleButton().addActionListener(l -> {
            rectangleMode = RectangleFactory.LAYERED_RECTANGLE;
        });

        window.getStripedRectangleButton().addActionListener(l -> {
            rectangleMode = RectangleFactory.STRIPED_RECTANGLE;
        });

        window.getBarredRectangleButton().addActionListener(l -> {
            rectangleMode = RectangleFactory.BARRED_RECTANGLE;
        });
    }
}