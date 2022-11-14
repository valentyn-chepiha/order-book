package order.book.model.types;

public enum TypeOrder {
    BUY("buy"), SELL("sell");

    private String shortName;

    TypeOrder(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
