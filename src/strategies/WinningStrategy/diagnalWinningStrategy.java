package strategies.WinningStrategy;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class diagnalWinningStrategy implements WinningStrategy {
    private Map<Symbol,Integer> leftDiagnal = new HashMap<>();
    private Map<Symbol,Integer> rightDiagnal = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row==col){
            //element on leftdiagnal
            if(!leftDiagnal.containsKey(symbol)){
                leftDiagnal.put(symbol,0);
            }
            leftDiagnal.put(symbol,leftDiagnal.get(symbol)+1);
        }

        if(row+col==board.getSize()-1) {
            if(!rightDiagnal.containsKey(symbol)){
                rightDiagnal.put(symbol,0);
            }
            rightDiagnal.put(symbol,rightDiagnal.get(symbol)+1);
        }

        if(row==col){
            if(leftDiagnal.get(symbol) == board.getSize()){
                return true;
            }
        }
        if(row+col == board.getSize()-1){
            if(rightDiagnal.get(symbol) == board.getSize()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row==col){
            leftDiagnal.put(symbol,leftDiagnal.get(symbol)-1);
        }
        if(row+col==board.getSize()-1){
            rightDiagnal.put(symbol,rightDiagnal.get(symbol)-1);
        }
        return;
    }
}
