package order.book.storage;

import java.util.HashMap;
import java.util.Map;
import order.book.model.types.TypeUpdate;

public class Storage {
    public static final Map<TypeUpdate, Map<Long, Long>> data = new HashMap<>();
}
