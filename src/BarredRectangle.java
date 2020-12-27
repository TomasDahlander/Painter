import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 21:38 <br>
 * Project: Painter <br>
 */
public class BarredRectangle extends Rectangle {

    public BarredRectangle(Grid start, Grid end) {
        super(start, end);
    }

    @Override
    public void draw(Pixel[][] pixels, Color color, boolean paint) {
        int rows = 0;
        for (int i = topLeft.getRow(); i <= bottomRight.getRow(); i++){
            int cols = 0;
            for (int j = topLeft.getCol(); j <= bottomRight.getCol(); j++){
                if (i == bottomRight.getRow()) pixels[i][j].paint(color,paint);
                else if (j == bottomRight.getCol()) pixels[i][j].paint(color,paint);
                else if (rows % 2 == 0 || cols % 2 == 0) pixels[i][j].paint(color,paint);
                cols++;
            }
            rows++;
        }
    }
}