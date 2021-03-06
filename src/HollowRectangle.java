import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 21:38 <br>
 * Project: Painter <br>
 */
public class HollowRectangle extends Rectangle {

    public HollowRectangle(Grid start, Grid end) {
        super(start, end);
    }

    @Override
    public void draw(Pixel[][] pixels, Color color, boolean paint) {
        for (int i = topLeft.getRow(); i <= bottomRight.getRow(); i++){
            for (int j = topLeft.getCol(); j <= bottomRight.getCol(); j++){
                if (i == topLeft.getRow() || i == bottomRight.getRow()) pixels[i][j].paint(color,paint);
                else{
                    pixels[i][topLeft.getCol()].paint(color, paint);
                    pixels[i][bottomRight.getCol()].paint(color, paint);
                    break;
                }
            }
        }
    }
}