/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 22:04 <br>
 * Project: Painter <br>
 */
public class RectangleFactory {

    public final static int HOLLOW_RECTANGLE = 0;
    public final static int FULL_RECTANGLE = 1  ;

    public Rectangle getRectangle(int type, Grid start, Grid end){
        if(type == 0) return new HollowRectangle(start, end);
        else if(type == 1) return new FullRectangle(start, end);

        else throw new IllegalArgumentException("Must contain an int for which rectangle needed.");
    }
}