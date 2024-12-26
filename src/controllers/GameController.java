package controllers;

import exceptions.MoreThanOneBotException;
import exceptions.NotUniquePlayerSymbolException;
import exceptions.PlayerCountMisMatchException;
import models.Game;
import models.GameState;
import models.Player;
import strategies.WinningStrategy.WinningStrategy;

import java.util.List;

public class GameController {
    // private Game game;
    // Declaring the game like this ties GameController to game object created above. This becomes stateful API and is not correct.

    public Game startGame(int dimenstionsOfBoard,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) throws NotUniquePlayerSymbolException, PlayerCountMisMatchException, MoreThanOneBotException {
        return Game.getBuilder()
                .setDimension(dimenstionsOfBoard)
                .setPlayers(players)
                .setWinningStrategy(winningStrategies)
                .build();

    }

    public void makeMove(Game game) {
        game.makeMove();
    }
    
    public GameState checkState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) {
        game.undo();
    }
}
