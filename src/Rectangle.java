import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 21:42 <br>
 * Project: Painter <br>
 */
public abstract class Rectangle {

    Grid topLeft;
    Grid bottomRight;

    public Rectangle(Grid start, Grid end){
        calculateRectangle(start, end);
    }

    public void calculateRectangle(Grid start, Grid end){
        Grid middle1 = new Grid(start.getRow(), end.getCol());
        Grid middle2 = new Grid(end.getRow(), start.getCol());

        List<Grid> list = new ArrayList<>();
        list.add(start);
        list.add(end);
        list.add(middle1);
        list.add(middle2);

        int topLeftGridValue = Integer.MAX_VALUE;
        int bottomRightGridValue = Integer.MIN_VALUE;
        int indexTopLeft = 0;
        int indexBottomRight = 0;

        for (int i = 0; i < list.size(); i++){
            int gridValue = list.get(i).getGridValue();

            if (gridValue < topLeftGridValue){
                topLeftGridValue = gridValue;
                indexTopLeft = i;
            }

            if (gridValue > bottomRightGridValue){
                bottomRightGridValue = gridValue;
                indexBottomRight = i;
            }
        }
        this.topLeft = list.get(indexTopLeft);
        this.bottomRight = list.get(indexBottomRight);
    }

    public abstract void draw(Pixel[][] pixels, Color color, boolean paint);
}