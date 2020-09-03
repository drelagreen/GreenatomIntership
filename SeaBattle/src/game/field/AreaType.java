package game.field;

public enum AreaType {
    NOTHING("[ ]"), SHIP("[O]"), N_DOT("[*]"), CROSS_DOT("[X]");

    AreaType(String img){
        this.img = img;
    }
    private final String img;

    public String getImg() {
        return img;
    }
}
