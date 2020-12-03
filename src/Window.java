import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.FileInputStream;
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

    Properties properties = new Properties();

    private int row = 75;
    private int col = 160;
    private int pixelSize = 6;
    private final Color[]colors = {WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,BLUE,CYAN,GREEN,new Color(135, 86, 56),MAGENTA,PINK,RED,ORANGE,YELLOW};

    // Panels
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel labelPanel = new JPanel(new GridLayout(row,col));
    private JPanel southPanel = new JPanel(new FlowLayout());

    // Labels
    private JLabel chosenColor = new JLabel("Chosen color",SwingConstants.CENTER);
    private JLabel customColorLabel = new JLabel("Choose between 0-255 of Red, Green and Blue:");

    // Textfields R G B
    private JTextField colorRed = new JTextField("0",3);
    private JTextField colorGreen = new JTextField("0",3);
    private JTextField colorBlue = new JTextField("0",3);

    // Buttons
    private JButton cleanButton = new JButton("Clear All");
    private JButton fillButton = new JButton("Fill All Unpainted");
    private JButton saveButton = new JButton("Quick Save");
    private JButton loadButton = new JButton("Quick Load");
    private JButton setCustomColor1 = new JButton("Set Color 1");
    private JButton getCustomColor1 = new JButton("Get Color 1");
    private JButton setCustomColor2 = new JButton("Set Color 2");
    private JButton getCustomColor2 = new JButton("Get Color 2");

    // Lists
    private List<JButton> colorButtons = new ArrayList<>();
    private List<JLabel> labels = new ArrayList<>();
    private List<Boolean> painted = new ArrayList<>();

    private List<Pixel> pixels = new ArrayList<>();

    public Window(){
        this.setLayout(new BorderLayout());

        add(labelPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);

        addLabelsToPanel();
        addButtonsToPanel();
        addComponentsToSouthPanels();
    }

    private void addLabelsToPanel(){
        for(int i = 0; i < (row*col); i++){
            labels.add(new JLabel());
            labels.get(i).setOpaque(true);
            labels.get(i).setBackground(WHITE);
            labels.get(i).setPreferredSize(new Dimension(pixelSize,pixelSize));
            labelPanel.add(labels.get(i));
            painted.add(false);
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
    }

    public void clearAll(){
        for (int i = 0; i < labels.size(); i++){
            labels.get(i).setBackground(WHITE);
            painted.set(i,false);
        }
    }

    public void fillAll(Color color){
        for (int i = 0; i < labels.size(); i++){
            if(!painted.get(i)) labels.get(i).setBackground(color);
        }
    }

    public void writeColorsFromMemory(List<JLabel> fromMemory){
        for (int i = 0; i < labels.size(); i++){
            labels.get(i).setBackground(fromMemory.get(i).getBackground());
        }
    }

    public void readPaintedFromMemory(List<Boolean> fromMemory){
        for (int i = 0; i < painted.size(); i++){
            painted.set(i,fromMemory.get(i));
        }
    }

    public List<JButton> getColorButtons() {
        return colorButtons;
    }

    public List<JLabel> getLabels() {
        return labels;
    }

    public List<Boolean> getPainted() {
        return painted;
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
}
