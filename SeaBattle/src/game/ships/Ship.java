package game.field;

public class Ship {
    private int size;
    private int health;
    private Pare<Integer,Integer> startCoords;
    Ship(Sizes sizes, Pare<Integer, Integer> startCoords){
        health = sizes.getSize();
        size = health;
        this.startCoords = startCoords;
    }

    public int getHealth() {
        return health;
    }

    int fire(){
        return health == 0 ? health : --health;
    }

    public Pare<Integer, Integer> getStartCoords() {
        return startCoords;
    }

    public int getSize() {
        return size;
    }
}
