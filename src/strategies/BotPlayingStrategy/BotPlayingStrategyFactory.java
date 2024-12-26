package strategies.BotPlayingStrategy;

import models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        //TO DO: implement the other difficulty levels (if-else)
        return new EasyBotPlayingStrategy();
    }
}
