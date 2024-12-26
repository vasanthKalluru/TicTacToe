package strategies.WinningStrategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class rowWinningStrategy implements WinningStrategy {
    Map<Integer, Map<Symbol,Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        if(!counts.containsKey(row)) {
            counts.put(row, new HashMap<>());
        }
        Map<Symbol,Integer> rowMap = counts.get(row);
        if(!rowMap.containsKey(symbol)) {
            rowMap.put(symbol, 0);
        }
        rowMap.put(symbol, rowMap.get(symbol) + 1);
        counts.put(row, rowMap);
        if(rowMap.get(symbol) == board.getSize()) {
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row= move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        Map<Symbol,Integer> map = counts.get(row);
        map.put(symbol, map.get(symbol) -1);
        return;
    }
}
