package models;
import exceptions.MoreThanOneBotException;
import exceptions.NotUniquePlayerSymbolException;
import exceptions.PlayerCountMisMatchException;
import strategies.WinningStrategy.WinningStrategy;

import java.util.*;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<WinningStrategy> winningStrategies, List<Player> players ) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder setWinningStrategy(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }
        //TO DO: move all the validation logic in a seperate class
        private void validateBotCount() throws MoreThanOneBotException {
            int botCount =0;
            for(Player player: players){
                if(player.getPlayerType()==PlayerType.BOT){
                    botCount++;
                }
            }
            if(botCount>1){
                throw new MoreThanOneBotException("more than one bot found in the players list.");
            }
        }
        private void validatePlayerCount() throws PlayerCountMisMatchException {
            if(players.size()!=dimension-1){
                throw new PlayerCountMisMatchException();
            }
        }
        private void validateUniquePlayerSymbol() throws NotUniquePlayerSymbolException {
            Set<Symbol> uniquePlayerSymbol = new HashSet<>();
            for(Player player: players){
                if(uniquePlayerSymbol.contains(player.getSymbol())){
                    throw new NotUniquePlayerSymbolException();
                } else {
                    uniquePlayerSymbol.add(player.getSymbol());
                }
            }

        }
        private void validate() throws MoreThanOneBotException, PlayerCountMisMatchException, NotUniquePlayerSymbolException {
            validateBotCount();
            validatePlayerCount();
            validateUniquePlayerSymbol();
        }
        public Game build() throws NotUniquePlayerSymbolException, PlayerCountMisMatchException, MoreThanOneBotException {
            validate();
            return new Game(dimension, winningStrategies, players);
        }

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    private boolean validateMove(Move move) {

    }
    public void makeMove() {
        Player curr_player = players.get(nextMovePlayerIndex);
        System.out.println("It is "+curr_player.getName()+ "'s move please make your move");
        curr_player.makeMove(board);
        //validate the move and proceed ahead.
    }


}
