package order.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private long price;
    private long count;

    public String toReport() {
        return price + "," + count;
    }
}
