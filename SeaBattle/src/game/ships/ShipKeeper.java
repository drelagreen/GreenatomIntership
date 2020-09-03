package game.ships;

import game.Pare;

public class ShipKeeper {
    private final Ship[][] shipField;
    private Ship currentShip;

    public ShipKeeper(){
        shipField = new Ship[14][14];
    }
    public void createNewShip(Sizes size, Pare<Integer,Integer> startCoords,boolean isHorizontalOrientation){
        currentShip = new Ship(size,startCoords, isHorizontalOrientation);
    }
    public void putShip(int y, int x){
        shipField[y][x] = currentShip;
    }

    public Ship getShip(Pare<Integer,Integer> pare){
        return shipField[pare.getY()][pare.getX()];
    }

}
