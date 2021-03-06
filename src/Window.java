import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.awt.Color.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-30 <br>
 * Time: 14:15 <br>
 * Project: Painter <br>
 */
public class Window extends JPanel {

    private final int row = 85;
    private final int col = 175;
    private final int pixelSize = 6;
    private final Color[]colors = {WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,BLUE,CYAN,GREEN,new Color(135, 86, 56),MAGENTA,PINK,RED,ORANGE,YELLOW};

    // Panels
    private final JPanel northPanel = new JPanel(new FlowLayout());
    private final JPanel centerPanel = new JPanel(new GridLayout(row,col));
    private final JPanel southPanel = new JPanel(new FlowLayout());

    // Labels
    private final JLabel chosenColor = new JLabel("Chosen color",SwingConstants.CENTER);

    // Buttons
    private final JButton clearButton = new JButton("Clear All");
    private final JButton fillButton = new JButton("Fill All Unpainted");
    private final JButton saveButton = new JButton("Quick Save");
    private final JButton loadButton = new JButton("Quick Load");
    private final JButton getCustomColorButton = new JButton("Get Custom Color");
    private final JToggleButton eraserButton = new JToggleButton("Eraser mode off");
    private final JToggleButton drawRectangleButton = new JToggleButton("Rectangle mode off");
    private final JRadioButton fullRectangleButton = new JRadioButton("Full rectangle",true);
    private final JRadioButton hollowRectangleButton = new JRadioButton("Hollow rectangle");
    private final JRadioButton checkedRectangleButton = new JRadioButton("Checked rectangle");
    private final JRadioButton layeredRectangleButton = new JRadioButton("Layered rectangle");
    private final JRadioButton stripedRectangleButton = new JRadioButton("Striped rectangle");
    private final JRadioButton barredRectangleButton = new JRadioButton("Barred rectangle");

    // Spinner
    private final JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));

    // Sliders
    private JSlider sliderRed;
    private JSlider sliderGreen;
    private JSlider sliderBlue;

    // Lists
    private final List<JButton> colorButtons = new ArrayList<>();
    private final Pixel[][] pixels = new Pixel[row][col];

    public Window(){
        this.setLayout(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        setUpSliders();

        addComponentsToNorthPanel();
        addPixelsToCenterPanel();
        addComponentsToSouthPanels();
    }

    private void setUpSliders(){
        sliderRed = new JSlider(0,255,0);
        sliderRed.setMinorTickSpacing(3);
        sliderRed.setSnapToTicks(true);
        sliderRed.setPreferredSize(new Dimension(400,18));

        sliderGreen = new JSlider(0,255,0);
        sliderGreen.setMinorTickSpacing(3);
        sliderGreen.setSnapToTicks(true);
        sliderGreen.setPreferredSize(new Dimension(400,18));

        sliderBlue = new JSlider(0,255,0);
        sliderBlue.setMinorTickSpacing(3);
        sliderBlue.setSnapToTicks(true);
        sliderBlue.setPreferredSize(new Dimension(400,18));
    }

    private void addComponentsToNorthPanel(){
        // Current color label
        chosenColor.setOpaque(true);
        chosenColor.setBackground(WHITE);
        chosenColor.setPreferredSize(new Dimension(100,20));
        chosenColor.setBorder(new EtchedBorder());
        northPanel.add(chosenColor);

        // ColorButtons
        for(int i = 0; i < colors.length; i++){
            colorButtons.add(new JButton());
            colorButtons.get(i).setBackground(colors[i]);
            colorButtons.get(i).setPreferredSize(new Dimension(25,20));
            northPanel.add(colorButtons.get(i));
        }

        // Clean, fill, save and load buttons
        northPanel.add(clearButton);
        northPanel.add(fillButton);
        northPanel.add(saveButton);
        northPanel.add(loadButton);

        northPanel.add(new JLabel("Slot"));
        northPanel.add(spinner);
    }

    private void addPixelsToCenterPanel(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                pixels[i][j] = new Pixel(pixelSize,i,j);
                centerPanel.add(pixels[i][j].getPixel());
            }
        }
        centerPanel.setBorder(new EtchedBorder());
    }

    private void addComponentsToSouthPanels(){
        JPanel southWest = new JPanel(new GridLayout(3,1));
        southWest.add(new JLabel("R"));
        southWest.add(new JLabel("G"));
        southWest.add(new JLabel("B"));
        southPanel.add(southWest);

        JPanel southCenter = new JPanel(new GridLayout(3,1));
        southCenter.add(sliderRed);
        southCenter.add(sliderGreen);
        southCenter.add(sliderBlue);
        southPanel.add(southCenter);

        JPanel southEast = new JPanel(new GridLayout(3,3));
        getCustomColorButton.setBackground(WHITE);
        southEast.add(getCustomColorButton);
        southEast.add(fullRectangleButton);
        southEast.add(layeredRectangleButton);

        southEast.add(eraserButton);
        southEast.add(hollowRectangleButton);
        southEast.add(checkedRectangleButton);

        southEast.add(drawRectangleButton);
        southEast.add(stripedRectangleButton);
        southEast.add(barredRectangleButton);

        southPanel.add(southEast);

        ButtonGroup group = new ButtonGroup();
        group.add(fullRectangleButton);
        group.add(hollowRectangleButton);
        group.add(checkedRectangleButton);
        group.add(layeredRectangleButton);
        group.add(stripedRectangleButton);
        group.add(barredRectangleButton);
    }

    public void clearAll(){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                pixels[i][j].getPixel().setBackground(WHITE);
                pixels[i][j].setPainted(false);
            }
        }
    }

    public void fillAll(Color color){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (!pixels[i][j].isPainted()) {
                    pixels[i][j].getPixel().setBackground(color);
                }
            }
        }
    }

    public void writePixelsFromMemory(Pixel[][] fromMemory){
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                pixels[i][j].getPixel().setBackground(fromMemory[i][j].getPixel().getBackground());
                pixels[i][j].setPainted(fromMemory[i][j].isPainted());
            }
        }
    }

    // Getters
    public List<JButton> getColorButtons() {
        return colorButtons;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getFillButton() {
        return fillButton;
    }

    public JLabel getChosenColor() {
        return chosenColor;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    public JToggleButton getEraserButton() {
        return eraserButton;
    }

    public JButton getGetCustomColorButton() {
        return getCustomColorButton;
    }

    public JSlider getSliderRed() {
        return sliderRed;
    }

    public JSlider getSliderGreen() {
        return sliderGreen;
    }

    public JSlider getSliderBlue() {
        return sliderBlue;
    }

    public JToggleButton getDrawRectangleButton() {
        return drawRectangleButton;
    }

    public JRadioButton getHollowRectangleButton() {
        return hollowRectangleButton;
    }

    public JRadioButton getFullRectangleButton() {
        return fullRectangleButton;
    }

    public JRadioButton getCheckedRectangleButton() {
        return checkedRectangleButton;
    }

    public JRadioButton getLayeredRectangleButton() {
        return layeredRectangleButton;
    }

    public JRadioButton getStripedRectangleButton() {
        return stripedRectangleButton;
    }

    public JRadioButton getBarredRectangleButton() {
        return barredRectangleButton;
    }
}
