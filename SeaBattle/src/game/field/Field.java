package game.field;

import game.Pare;
import game.ships.Ship;
import game.ships.ShipKeeper;
import game.ships.Sizes;

public class Field{
    ShipKeeper shipKeeper;
    public Field(ShipKeeper shipKeeper){
        init();
        this.shipKeeper = shipKeeper;
    }
    AreaType[][] field = new AreaType[14][14];

    private void init(){
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 13; j++) {
                field[i][j] = AreaType.NOTHING;
            }
        }
    }

    AreaType getElement(int y, int x){
        return field[y][x];
    }
    void setElement(int y, int x, AreaType type){
        field[y][x] = type;
    }

    public boolean put(AreaType type, Sizes size, Pare<Integer, Integer> startCoords, boolean horizontalOrientation) {
        if (startCoords.getY()==0||startCoords.getX()==0) return false;
        switch (type) {
            case SHIP:
                for (int i = 0; i < size.getSize(); i++) {
                    if (horizontalOrientation) {
                        if (!(getElement(startCoords.getY(),startCoords.getX()+i)==AreaType.NOTHING&&
                                getElement(startCoords.getY(),startCoords.getX()+i-1)==AreaType.NOTHING&&
                                getElement(startCoords.getY(),startCoords.getX()+i+1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+1,startCoords.getX()+i)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+1,startCoords.getX()+i-1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+1,startCoords.getX()+i+1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()-1,startCoords.getX()+i)==AreaType.NOTHING&&
                                getElement(startCoords.getY()-1,startCoords.getX()+i-1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()-1,startCoords.getX()+i+1)==AreaType.NOTHING
                        )) {
                            return false;
                        }
                    } else {
                        if (!(getElement(startCoords.getY()+i,startCoords.getX())==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i,startCoords.getX()-1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i,startCoords.getX()+1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i+1,startCoords.getX())==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i+1,startCoords.getX()-1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i+1,startCoords.getX()+1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i-1,startCoords.getX())==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i-1,startCoords.getX()-1)==AreaType.NOTHING&&
                                getElement(startCoords.getY()+i-1,startCoords.getX()+1)==AreaType.NOTHING
                        )) {
                            return false;
                        }
                    }
                }
                shipKeeper.createNewShip(size, startCoords, horizontalOrientation);
                for (int i = 0; i < size.getSize(); i++) {
                    setElement(startCoords.getY()+ (horizontalOrientation ? 0 : i), startCoords.getX()+(horizontalOrientation ? i : 0), AreaType.SHIP);
                    shipKeeper.putShip(startCoords.getY()+ (horizontalOrientation ? 0 : i), startCoords.getX()+(horizontalOrientation ? i : 0));
                }
                return true;

            case N_DOT:
                if (getElement(startCoords.getY(),startCoords.getX()) == AreaType.NOTHING) {
                    field[startCoords.getY()][startCoords.getX()] = AreaType.N_DOT;
                    return true;
                } else
                    return false;
            case CROSS_DOT:
                if (getElement(startCoords.getY(),startCoords.getX()) == AreaType.SHIP) {
                    setElement(startCoords.getY(),startCoords.getX(),AreaType.CROSS_DOT);
                    return true;
                } else
                    return false;
            default:
                return false;
        }
    }
    //***********************************************************************
    public byte fire(Pare<Integer,Integer> coordinates){
        switch (getElement(coordinates.getY(),coordinates.getX())){
            case NOTHING:
                put(AreaType.N_DOT,null,coordinates,false);
                return 0;
            case SHIP:
                put(AreaType.CROSS_DOT,null,coordinates,false);
                Ship s = shipKeeper.getShip(coordinates);
                int health = s.fire();
                if(health == 0){
                    autoDotting(shipKeeper.getShip(coordinates));
                }
                return 1;
            default:
                return 2;
        }
    }
    //***********************************************************************
    void autoDotting(Ship ship){
        int y = ship.getStartCoords().getY();
        int x = ship.getStartCoords().getX();
        for (int i = -1; i < ship.getSize()+1; i++) {
            if(ship.isHorizontalOrientation()) {
                if (i == -1 || i == ship.getSize()) put(AreaType.N_DOT, null, new Pare<>(y, x + i), false);
                put(AreaType.N_DOT, null, new Pare<>(y + 1, x + i), false);
                put(AreaType.N_DOT, null, new Pare<>(y - 1, x + i), false);
            } else {
                if (i == -1 || i == ship.getSize()) put(AreaType.N_DOT, null, new Pare<>(y + i, x), false);
                put(AreaType.N_DOT, null, new Pare<>(y + i, x - 1), false);
                put(AreaType.N_DOT, null, new Pare<>(y + i, x + 1), false);
            }
        }
    }
}
