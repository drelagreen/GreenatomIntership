public enum AreaType {
    NOTHING("[ ]"), SHIP("[O]"), NDOT("[*]"), CROSSEDOT("[X]");
    AreaType(String img){
        this.img = img;
    }
    private String img;

    public String getImg() {
        return img;
    }
}
