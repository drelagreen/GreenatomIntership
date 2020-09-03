package game.field;

public enum Sizes {
    ONE(1),TWO(2),THREE(3),FOUR(4);

    private int size;

    Sizes(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
