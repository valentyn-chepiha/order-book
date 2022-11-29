package order.book.model;

public interface Model<T> {
    Model<T> buildOf(String[] data);
}
