import java.util.ArrayList;

public class Board {
    private Box boxes [][]  = {
        {new Box() , new Box() , new Box ()},
        {new Box() , new Box() , new Box ()},
        {new Box() , new Box() , new Box ()}
    };

    public Box[][] getBoxes ( ) {
        return boxes;
    }

    public String toString () {
        String s="";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s +=("["+ boxes[i][j].getBoxStat () + "] " );
            }
            s += "\n";
        }
        return s;
    }

}
