package strategies.BotPlayingStrategy;

import models.Board;
import models.Cell;
import models.CellState;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move MakeMove(Board board) {
        for(List<Cell> row: board.getBoard()) {
            for(Cell c: row) {
                if(c.getCellState()== CellState.EMPTY){
                    return new Move(c,null);
                }
            }
        }
        return null;
    }
}
