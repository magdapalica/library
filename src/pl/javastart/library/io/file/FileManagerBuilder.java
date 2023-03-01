package pl.javastart.library.io.file;

import pl.javastart.library.exception.NoSuchFileTypeException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;

public class FileManagerBuilder {
    private ConsolePrinter consolePrinter;
    private DataReader dataReader;

    public FileManagerBuilder(ConsolePrinter consolePrinter, DataReader dataReader) {
        this.consolePrinter = consolePrinter;
        this.dataReader = dataReader;
    }

    public FileManager build() {
        consolePrinter.printLine("Choose data format: ");
        FileType fileType = getFileType();
        switch (fileType) {
            case SERIAL:
                return new SerializableFileManager();
            case CSV:
                return  new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Unhandled data type");
        }
    }

    private FileType getFileType() {
        boolean typeOK = false;
        FileType result = null;
        do {
            printTypes();
            String type = dataReader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOK = true;
            } catch (IllegalArgumentException e) {
                consolePrinter.printLine("Invalid data type, try entry again");
            }
        } while (!typeOK);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            consolePrinter.printLine(value.name());

        }
    }

}
