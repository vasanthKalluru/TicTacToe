package models;

import strategies.BotPlayingStrategy.BotPlayingStrategy;
import strategies.BotPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(BotDifficultyLevel botDifficultyLevel, Long id, String name, Symbol symbol) {
        super(symbol, name, id, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }
    @Override
    public Move makeMove(Board board) {
        Move newMove = botPlayingStrategy.MakeMove(board);
        newMove.setPlayer(this);
        return newMove;
    }

}
