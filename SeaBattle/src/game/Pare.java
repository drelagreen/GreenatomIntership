package game;

public class Pare<T,F> {
    private final T y;
    private final F x;
    public Pare(T y, F x){
        this.y = y;
        this.x = x;
    }
    public F getX() {
        return x;
    }

    public T getY() {
        return y;
    }
}
