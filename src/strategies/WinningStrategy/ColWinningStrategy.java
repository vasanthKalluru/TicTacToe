package strategies.WinningStrategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {
    Map<Integer, Map<Symbol,Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!counts.containsKey(col)) {
            counts.put(col, new HashMap<>());
        }
        Map<Symbol,Integer> colMap = counts.get(col);
        if(!colMap.containsKey(symbol)) {
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol) + 1);
        counts.put(col, colMap);
        if(colMap.get(symbol) == board.getSize()) {
            return true;
        }

        return false;
    }
}
