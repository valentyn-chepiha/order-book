package order.book.model.types;

public enum TypeUpdate {
    ASK("ask"), BID("bid");

    private String shortName;

    TypeUpdate(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
