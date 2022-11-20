package order.book.util;

import java.util.Map;
import java.util.Set;
import order.book.model.Operation;

public class AnaliseTransaction {
    public static Operation getBestBid(Set<Map.Entry<Long, Long>> data) {
        return getBestBidMoreNeedCount(data, 1L);
    }

    public static Operation getBestBidMoreNeedCount(Set<Map.Entry<Long, Long>> data, Long count) {
        Map.Entry<Long, Long> entry = data.stream()
                .filter(e -> e.getValue() >= count)
                .min((a, b) -> (int) (b.getKey() - a.getKey()))
                .orElse(null);
        return entry != null ? new Operation(entry.getKey(), entry.getValue()) : null;
    }

    public static Operation getBestAsk(Set<Map.Entry<Long, Long>> data) {
        return getBestAskMoreNeedCount(data, 1L);
    }

    public static Operation getBestAskMoreNeedCount(Set<Map.Entry<Long, Long>> data, Long count) {
        Map.Entry<Long, Long> entry = data.stream()
                .filter(e -> e.getValue() >= count)
                .min((a, b) -> (int) (a.getKey() - b.getKey()))
                .orElse(null);
        return entry != null ? new Operation(entry.getKey(), entry.getValue()) : null;
    }
}
