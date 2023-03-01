package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("File " + FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataImportException("Error reading to the file " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Incompatible data type in file  " + FILE_NAME);
        }
    }

    @Override
    public void exportData(Library library) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("File " + FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataExportException("Error writing to the file " + FILE_NAME);
        }
    }
}
