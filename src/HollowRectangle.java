import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 21:38 <br>
 * Project: Painter <br>
 */
public class HollowRectangle extends AbstractRectangle {

    public HollowRectangle(Grid start, Grid end) {
        super(start, end);
    }

    @Override
    public void draw(Pixel[][] pixels, Color color, boolean paint) {
        for (int i = topLeft.getRow(); i <= bottomRight.getRow(); i++){
            for (int j = topLeft.getCol(); j <= bottomRight.getCol(); j++){
                if (i == topLeft.getRow() || i == bottomRight.getRow()){
                    pixels[i][j].getPixel().setBackground(color);
                    pixels[i][j].setPainted(paint);
                }
                else{
                    pixels[i][topLeft.getCol()].getPixel().setBackground(color);
                    pixels[i][topLeft.getCol()].setPainted(paint);
                    pixels[i][bottomRight.getCol()].getPixel().setBackground(color);
                    pixels[i][bottomRight.getCol()].setPainted(paint);
                    break;
                }
            }
        }
    }
}
