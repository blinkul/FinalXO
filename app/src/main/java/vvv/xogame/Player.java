package vvv.xogame;

/**
 * Created by ciprian.anghel on 8/18/2017.
 */

public class Player {
    private String name;
    private Character mark;
    private int[] playerBoard = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    public Player(String name, Character mark) {
        setName(name);
        setMark(mark);
    }

    public int[] getPlayerBoard(){
        int [] temp = playerBoard.clone();
        return temp;
    }

    public void refreshBoard(){
        int[] empty = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerBoard = empty.clone();
    }

    public boolean addMark(int location) {

        boolean status = false;

            if(playerBoard[location] == 0) {
                playerBoard[location] = 1;
                status = true;
            }

        return status;
    }

    public String getName(){
        return name;
    }

    public Character getMark(){
        return mark;
    }

    private void setName(String name) {
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(name.trim().substring(0, 1).toUpperCase());
        nameBuilder.append(name.trim().substring(1).toLowerCase());
        this.name = nameBuilder.toString();
    }

    private void setMark(Character mark) {
        this.mark = mark;
    }
}