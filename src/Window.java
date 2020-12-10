import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;
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

    private final int row = 80;
    private final int col = 175;
    private final int pixelSize = 6;
    private final Color[]colors = {WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,BLUE,CYAN,GREEN,new Color(135, 86, 56),MAGENTA,PINK,RED,ORANGE,YELLOW};

    // Panels
    private final JPanel buttonPanel = new JPanel(new FlowLayout());
    private final JPanel labelPanel = new JPanel(new GridLayout(row,col));
    private final JPanel southPanel = new JPanel(new FlowLayout());

    // Labels
    private final JLabel chosenColor = new JLabel("Chosen color",SwingConstants.CENTER);
    private final JLabel customColorLabel = new JLabel("Choose between 0-255 of Red, Green and Blue:");
    private final JLabel saveSlot = new JLabel("Slot");

    // Textfields R G B
    private final JTextField colorRed = new JTextField("0",3);
    private final JTextField colorGreen = new JTextField("0",3);
    private final JTextField colorBlue = new JTextField("0",3);

    // Buttons
    private final JButton cleanButton = new JButton("Clear All");
    private final JButton fillButton = new JButton("Fill All Unpainted");
    private final JButton saveButton = new JButton("Quick Save");
    private final JButton loadButton = new JButton("Quick Load");
    private final JButton setCustomColor1 = new JButton("Set Color 1");
    private final JButton getCustomColor1 = new JButton("Get Color 1");
    private final JButton setCustomColor2 = new JButton("Set Color 2");
    private final JButton getCustomColor2 = new JButton("Get Color 2");
    private final JToggleButton eraser = new JToggleButton("Eraser mode off"); // Lägg till knapp som sätter målat till false

    // Spinner
    private JSpinner spinner;

    // Lists
    private final List<JButton> colorButtons = new ArrayList<>();

    private final List<Pixel> pixels = new ArrayList<>();

    public Window(){
        this.setLayout(new BorderLayout());

        add(labelPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);

        addLabelsToPanel();
        addButtonsToPanel();
        addComponentsToSouthPanels();
        setUpSpinner();
    }

    private void addLabelsToPanel(){
        for(int i = 0; i < (row*col); i++){
            pixels.add(new Pixel(pixelSize));
            labelPanel.add(pixels.get(i).getPixel());
        }
        labelPanel.setBorder(new EtchedBorder());
    }

    private void addButtonsToPanel(){
        // Current color label
        chosenColor.setOpaque(true);
        chosenColor.setBackground(WHITE);
        chosenColor.setPreferredSize(new Dimension(100,20));
        chosenColor.setBorder(new EtchedBorder());
        buttonPanel.add(chosenColor);

        // ColorButtons
        for(int i = 0; i < colors.length; i++){
            colorButtons.add(new JButton());
            colorButtons.get(i).setBackground(colors[i]);
            colorButtons.get(i).setPreferredSize(new Dimension(25,20));
            buttonPanel.add(colorButtons.get(i));
        }

        // Clean, fill, save and load buttons
        buttonPanel.add(cleanButton);
        buttonPanel.add(fillButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
    }

    private void addComponentsToSouthPanels(){
        southPanel.add(customColorLabel);
        colorRed.setHorizontalAlignment(SwingConstants.CENTER);
        southPanel.add(colorRed);
        colorGreen.setHorizontalAlignment(SwingConstants.CENTER);
        southPanel.add(colorGreen);
        colorBlue.setHorizontalAlignment(SwingConstants.CENTER);
        southPanel.add(colorBlue);

        getCustomColor1.setBackground(WHITE);
        getCustomColor2.setBackground(WHITE);

        southPanel.add(setCustomColor1);
        southPanel.add(getCustomColor1);
        southPanel.add(setCustomColor2);
        southPanel.add(getCustomColor2);

        southPanel.add(eraser);
    }

    public void clearAll(){
        for (var l : pixels){
            l.getPixel().setBackground(WHITE);
            l.setPainted(false);
        }
    }

    public void fillAll(Color color){
        for (var l : pixels){
            if (!l.isPainted()) l.getPixel().setBackground(color);
        }
    }

    public void writePixelsFromMemory(List<Pixel> fromMemory){
        for (int i = 0; i < pixels.size(); i++){
            pixels.get(i).getPixel().setBackground(fromMemory.get(i).getPixel().getBackground());
            pixels.get(i).setPainted(fromMemory.get(i).isPainted());
        }
    }

    public void setUpSpinner(){
        buttonPanel.add(saveSlot);
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 8, 1));
        buttonPanel.add(spinner);
    }

    // Getters
    public List<JButton> getColorButtons() {
        return colorButtons;
    }

    public List<Pixel> getPixels() {
        return pixels;
    }

    public JButton getCleanButton() {
        return cleanButton;
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

    public JTextField getColorRed() {
        return colorRed;
    }

    public JTextField getColorGreen() {
        return colorGreen;
    }

    public JTextField getColorBlue() {
        return colorBlue;
    }

    public JButton getSetCustomColor1() {
        return setCustomColor1;
    }

    public JButton getGetCustomColor1() {
        return getCustomColor1;
    }

    public JButton getSetCustomColor2() {
        return setCustomColor2;
    }

    public JButton getGetCustomColor2() {
        return getCustomColor2;
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    public JToggleButton getEraser() {
        return eraser;
    }
}
