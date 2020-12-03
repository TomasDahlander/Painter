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

    public Pixel(int labelSideSize){
        pixel = new JLabel();
        painted = false;
        pixel.setOpaque(true);
        pixel.setBackground(Color.WHITE);
        pixel.setPreferredSize(new Dimension(labelSideSize,labelSideSize));

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
}
