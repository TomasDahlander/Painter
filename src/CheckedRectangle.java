import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-22 <br>
 * Time: 21:42 <br>
 * Project: Painter <br>
 */
public class CheckedRectangle extends Rectangle {

    public CheckedRectangle(Grid start, Grid end) {
        super(start, end);
    }

    @Override
    public void draw(Pixel[][] pixels, Color color, boolean paint) {
        int rows = 0;
        for (int i = topLeft.getRow(); i <= bottomRight.getRow(); i++){
            int cols = 0;
            for (int j = topLeft.getCol(); j <= bottomRight.getCol(); j++){
                if (rows % 2 == 0 && cols % 2 ==0) pixels[i][j].paint(color,paint);
                else if (rows % 2 == 1 && cols % 2 == 1) pixels[i][j].paint(color,paint);
                cols++;
            }
        rows++;
        }
    }
}
