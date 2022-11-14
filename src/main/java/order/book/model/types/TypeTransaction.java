package order.book.model.types;

public enum TypeTransaction {
    QUERY("q"), UPDATE("u"), ORDER("o");

    private String shortName;

    TypeTransaction(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
