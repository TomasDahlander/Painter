import java.awt.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 21:38 <br>
 * Project: Painter <br>
 */
public class LayeredRectangle extends Rectangle {

    public LayeredRectangle(Grid start, Grid end) {
        super(start, end);
    }

    @Override
    public void draw(Pixel[][] pixels, Color color, boolean paint) {
        Grid top = new Grid(this.topLeft);
        Grid bottom = new Grid(this.bottomRight);

        while(true){
            HollowRectangle rectangle = new HollowRectangle(this.topLeft,this.bottomRight);
            rectangle.draw(pixels, color, paint);
            this.topLeft.alterGridBySquare(2);
            this.bottomRight.alterGridBySquare(-2);
            if(bottomRight.getCol() < topLeft.getCol() || bottomRight.getRow() < topLeft.getRow()) break;
        }

        topLeft = top;
        bottomRight = bottom;
    }
}