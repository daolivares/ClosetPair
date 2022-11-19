import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {

    private PrintWriter writer;

    public Writer(String fileName) throws IOException {
        startWriter(fileName);
    }

    private void startWriter(String filename) throws IOException {
        File f = new File(filename);
        f.createNewFile();
        writer = new PrintWriter(filename);
    }

    public void write(String line){
        writer.println(line);
    }

    public void saveFile() throws IOException {
        writer.close();
    }
}
