package order.book.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import order.book.model.Model;
import order.book.model.types.TypeTransaction;
import order.book.service.TransactionService;
import order.book.service.strategy.TypeProcessingStrategy;

public class TransactionServiceImpl implements TransactionService {
    private static final String SEPARATOR = ",";
    private static final int TYPE_TRANSACTION_INDEX = 0;
    private final TypeProcessingStrategy<TypeTransaction> transactionProcessingStrategy;
    private final Map<TypeTransaction, Model> map;

    public TransactionServiceImpl(
            TypeProcessingStrategy<TypeTransaction> transactionProcessingStrategy,
            Map<TypeTransaction, Model> map) {
        this.transactionProcessingStrategy = transactionProcessingStrategy;
        this.map = map;
    }

    @Override
    public void processing(List<String> dataList) {
        for (String line: dataList) {
            String[] lineArray = line.split(SEPARATOR);
            Arrays.stream(TypeTransaction.values())
                    .filter(o -> o.getShortName().equals(lineArray[TYPE_TRANSACTION_INDEX]))
                    .findFirst()
                    .ifPresent(typeTransaction -> transactionProcessingStrategy
                            .getHandlerByTypeProcessing(typeTransaction)
                            .processing(map.get(typeTransaction).buildOf(lineArray)));
        }
    }
}
