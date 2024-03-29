import java.io.Serial;
import java.io.Serializable;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-12-16 <br>
 * Time: 09:49 <br>
 * Project: Painter <br>
 */
public class Grid implements Serializable {
    @Serial
    private static final long serialVersionUID = -8077260071112387331L;
    private int row;
    private int col;

    public Grid(int row, int col){
        this.row = row;
        this.col = col;
    }

    public Grid(Grid grid){
        this.row = grid.getRow();
        this.col = grid.getCol();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getGridValue() {
        return row + col;
    }

    public void alterGridBySquare(int alteration){
        this.row += alteration;
        this.col += alteration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grid)) return false;
        Grid grid = (Grid) o;
        return row == grid.row &&
                col == grid.col;
    }
}