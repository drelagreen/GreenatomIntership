package game.ships;

import game.Pare;

public class Ship {
    private final int size;
    private int health;
    private final boolean horizontalOrientation;
    private final Pare<Integer,Integer> startCoords;
    Ship(Sizes sizes, Pare<Integer, Integer> startCoords, boolean horizontalOrientation){
        health = sizes.getSize();
        size = health;
        this.startCoords = startCoords;
        this.horizontalOrientation = horizontalOrientation;
    }

    public int fire(){
        return health == 0 ? health : --health;
    }

    public Pare<Integer, Integer> getStartCoords() {
        return startCoords;
    }

    public int getSize() {
        return size;
    }

    public boolean isHorizontalOrientation() {
        return horizontalOrientation;
    }

}
