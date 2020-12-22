import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-03 <br>
 * Time: 17:30 <br>
 * Project: Painter <br>
 */
public class Pixel implements Serializable {

    private JLabel pixel;
    private boolean painted;
    private Grid grid;

    public Pixel(int labelSideSize, int row, int col){
        this.pixel = new JLabel();
        this.grid = new Grid(row,col);

        painted = false;
        pixel.setOpaque(true);
        pixel.setBackground(Color.WHITE);
        pixel.setPreferredSize(new Dimension(labelSideSize,labelSideSize));
    }

    public void paint(Color color, boolean painted){
        this.painted = painted;
        pixel.setBackground(color);
    }

    public JLabel getPixel() {
        return pixel;
    }

    public boolean isPainted() {
        return painted;
    }

    public void setPainted(boolean painted) {
        this.painted = painted;
    }

    public Grid getGrid() {
        return grid;
    }
}
