package order.book.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import order.book.exception.DataProcessingException;
import order.book.service.FileWriterService;

public class FileWriterServiceImpl implements FileWriterService {
    private String fileName;

    public FileWriterServiceImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(report);
        } catch (Exception e) {
            throw new DataProcessingException("File can't write " + fileName, e);
        }
    }
}
