public enum Column{
    NAME("name"),UUID("uuid"),EMAIL("email"),REG_DATE("reg_date"),ONLINE("online"),LAST_ONLINE("last_online");
    Column(String str){
        this.str = str;
    }
    private final String str;
    @Override
    public String toString(){
        return str;
    }
}