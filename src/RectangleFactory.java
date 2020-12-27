/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-21 <br>
 * Time: 22:04 <br>
 * Project: Painter <br>
 */
public class RectangleFactory {

    public final static int HOLLOW_RECTANGLE = 0;
    public final static int FULL_RECTANGLE = 1  ;
    public final static int CHECKED_RECTANGLE = 2;
    public final static int LAYERED_RECTANGLE = 3;
    public final static int STRIPED_RECTANGLE = 4;
    public final static int BARRED_RECTANGLE = 5;

    public Rectangle getRectangle(int type, Grid start, Grid end){
        if(type == HOLLOW_RECTANGLE) return new HollowRectangle(start, end);
        else if(type == FULL_RECTANGLE) return new FullRectangle(start, end);
        else if (type == CHECKED_RECTANGLE) return new CheckedRectangle(start, end);
        else if (type == LAYERED_RECTANGLE) return new LayeredRectangle(start, end);
        else if (type == STRIPED_RECTANGLE) return new StripedRectangle(start, end);
        else if (type == BARRED_RECTANGLE) return new BarredRectangle(start, end);

        else throw new IllegalArgumentException("\nMust contain an int.\n0 = Hollowed\n1 = Full\n2 = Checked\n3 = Layered" +
                    "\n4 = Striped\n5 = Barred");
    }
}