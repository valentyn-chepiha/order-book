package order.book.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operation {
    private long price;
    private long count;

    public String toReport() {
        return price + "," + count;
    }
}
