package game.ships;

public enum Sizes {
    ONE(1),TWO(2),THREE(3),FOUR(4);

    public final int size;

    Sizes(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
