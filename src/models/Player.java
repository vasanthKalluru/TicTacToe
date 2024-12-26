package models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private Long id;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Symbol symbol, String name, Long id, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.id = id;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in); //or use BufferedReader
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        System.out.println("please enter the row and column where you want to insert the Symbol.");
        int Row = scanner.nextInt();
        int Column = scanner.nextInt();
//        Cell cell = board.getBoard().get(Row).get(Column);
        Cell cell = new Cell(Row, Column);
        return new Move(cell,this);
    }

}
