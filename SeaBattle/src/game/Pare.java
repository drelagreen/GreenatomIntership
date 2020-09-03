package game.field;

public class Pare<T,F> {
    private final T y;
    private final F x;
    Pare(T y, F x){
        this.y = y;
        this.x = x;
    }
    public F getX() {
        return x;
    }

    public T getY() {
        return y;
    }
    public Pare<T,F> clone(){
        return new Pare<>(y,x);
    }


}
