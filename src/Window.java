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
    private int col = 150;
    private int pixelSize = 6;
    private final Color[]colors = {WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,BLUE,CYAN,GREEN,new Color(135, 86, 56),MAGENTA,PINK,RED,ORANGE,YELLOW};

    // Panels
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel labelPanel = new JPanel(new GridLayout(row,col));

    // Labels
    private JLabel chosenColor = new JLabel("Chosen color",SwingConstants.CENTER);

    // Buttons
    private JButton cleanButton = new JButton("Clear All");
    private JButton fillButton = new JButton("Fill All Unpainted");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");

    // Lists
    private List<JButton> colorButtons = new ArrayList<>();
    private List<JLabel> labels = new ArrayList<>();
    private List<Boolean> painted = new ArrayList<>();

    public Window(){
        setProperties();
        this.setLayout(new BorderLayout());

        add(labelPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        addLabelsToPanel();
        addButtonsToPanel();
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

    public void setProperties() {
        try {
            properties.load(new FileInputStream("src/Properties.properties"));
        } catch (Exception e) {
            System.out.println("Could not load properties file");
            e.printStackTrace();
        }
        row = Integer.parseInt(properties.getProperty("row", "75"));
        col = Integer.parseInt(properties.getProperty("col", "150"));
        pixelSize = Integer.parseInt(properties.getProperty("pixel", "6"));
    }
}
