package order.book.service.impl;

import java.util.Arrays;
import java.util.List;
import order.book.model.types.TypeTransaction;
import order.book.service.TransactionService;
import order.book.service.strategy.TypeProcessingStrategy;
import order.book.service.strategy.handler.ProcessingHandler;

public class TransactionServiceImpl implements TransactionService {
    private static final String SEPARATOR_PARAM = ",";
    private static final int INDEX_TYPE_TRANSACTION = 0;
    private final TypeProcessingStrategy<TypeTransaction> transactionProcessingStrategy;

    public TransactionServiceImpl(TypeProcessingStrategy<TypeTransaction>
                                          transactionProcessingStrategy) {
        this.transactionProcessingStrategy = transactionProcessingStrategy;
    }

    @Override
    public void processing(List<String> dataList) {
        for (String line: dataList) {
            String[] lineArray = line.split(SEPARATOR_PARAM);
            TypeTransaction typeTransaction = Arrays.stream(TypeTransaction.values())
                    .filter(o -> o.getShortName().equals(lineArray[INDEX_TYPE_TRANSACTION]))
                    .findFirst()
                    .orElse(null);
            if (typeTransaction == null) {
                continue;
            }
            ProcessingHandler handler = transactionProcessingStrategy.getHandlerByTypeProcessing(
                    typeTransaction);
            handler.processing(Arrays.copyOfRange(lineArray, 1, lineArray.length));
        }
    }
}
