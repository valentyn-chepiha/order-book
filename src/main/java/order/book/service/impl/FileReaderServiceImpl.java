package order.book.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import order.book.exception.DataProcessingException;
import order.book.service.FileReaderService;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> read(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (Exception e) {
            throw new DataProcessingException("File not found " + fileName, e);
        }
    }
}
