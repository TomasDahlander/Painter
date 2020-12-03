import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-03 <br>
 * Time: 17:13 <br>
 * Project: Painter <br>
 */
public class ColorController {

    public int checkInput(JTextField field){
        try{
            int x = Integer.parseInt(field.getText());
            System.out.println(x);
            return x;
        }catch(NumberFormatException e){
            System.out.println(0);
            return 0;
        }
    }

    public Color checkColor(int r, int g, int b){
        if (r > 255) r = 255;
        else if (r < 0) r = 0;
        if (g > 255) g = 255;
        else if (g < 0) g = 0;
        if (b > 255) b = 255;
        else if (b < 0) b = 0;

        return new Color(r,g,b);
    }
}
