package order.book.service.strategy.handler;

import order.book.model.Model;

public interface ProcessingHandler {
    void processing(Model model);
}
