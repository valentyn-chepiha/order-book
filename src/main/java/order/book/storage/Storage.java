package order.book.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import order.book.model.types.TypeUpdate;

public class Storage {
    public static final Map<TypeUpdate, NavigableMap<Long, Long>> data = new HashMap<>();
}
