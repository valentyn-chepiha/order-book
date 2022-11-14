package order.book.model.types;

public enum TypeQuery {
    BEST_BID("best_bid"), BEST_ASK("best_ask"), SIZE("size");

    private String shortName;

    TypeQuery(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
