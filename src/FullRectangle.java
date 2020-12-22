import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 21:48 <br>
 * Project: Painter <br>
 */
public class FullRectangle extends Rectangle {


    public FullRectangle(Grid start, Grid end) {
        super(start, end);
    }

    @Override
    public void draw(Pixel[][] pixels, Color color, boolean paint) {
        for (int i = topLeft.getRow(); i <= bottomRight.getRow(); i++){
            for (int j = topLeft.getCol(); j <= bottomRight.getCol(); j++){
                pixels[i][j].paint(color, paint);
            }
        }
    }

}
