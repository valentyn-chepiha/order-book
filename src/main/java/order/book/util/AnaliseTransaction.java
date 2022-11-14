package order.book.util;

import java.util.Map;
import java.util.Set;
import order.book.model.Operation;

public class AnaliseTransaction {
    public static Operation getBestBid(Set<Map.Entry<Long, Long>> data) {
        Operation operation = null;
        for (Map.Entry<Long, Long> bid : data) {
            if (operation == null) {
                operation = new Operation();
                setOperation(operation, bid);
                continue;
            }
            if (operation.getPrice() < bid.getKey() && bid.getValue() > 0) {
                setOperation(operation, bid);
            }
        }
        return operation;
    }

    public static Operation getBestAsk(Set<Map.Entry<Long, Long>> data) {
        Operation operation = null;
        for (Map.Entry<Long, Long> ask : data) {
            if (operation == null) {
                operation = new Operation();
                setOperation(operation, ask);
                continue;
            }
            if (operation.getPrice() > ask.getKey() && ask.getValue() > 0) {
                setOperation(operation, ask);
            }
        }
        return operation;
    }

    private static void setOperation(Operation operation, Map.Entry<Long, Long> data) {
        operation.setPrice(data.getKey());
        operation.setCount(data.getValue());
    }
}
