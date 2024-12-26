import controllers.GameController;
import exceptions.MoreThanOneBotException;
import exceptions.NotUniquePlayerSymbolException;
import exceptions.PlayerCountMisMatchException;
import models.*;
import strategies.WinningStrategy.ColWinningStrategy;
import strategies.WinningStrategy.WinningStrategy;
import strategies.WinningStrategy.diagnalWinningStrategy;
import strategies.WinningStrategy.rowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws NotUniquePlayerSymbolException, PlayerCountMisMatchException, MoreThanOneBotException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        int dimensions = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player(new Symbol('X'),"Sirish",1L, PlayerType.HUMAN));
        //players.add(new Player(new Symbol('O'),"Girish",2L,PlayerType.HUMAN));
        players.add(new Bot(BotDifficultyLevel.EASY,3L,"Bot",new Symbol('Z') ));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new rowWinningStrategy());
        winningStrategies.add(new diagnalWinningStrategy());

        Game game = gameController.startGame(
                dimensions,
                players,
                winningStrategies
        );

        while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
            //print the board
            gameController.printBoard(game);
            System.out.println("Undo? (y/n)");
            String ans = scanner.next();
            if(ans.equalsIgnoreCase("y")) {
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }

        System.out.println("Game over!");
        GameState gameState = gameController.checkState(game);
        if(gameState.equals(GameState.WIN)){
            System.out.println("Congratulations! winner is: "+gameController.getWinner(game).getName());
        } else {
            System.out.println("Game is draw.");
        }
    }
}
