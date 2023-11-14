import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public List<String> readOrders() {
        List<String> orders = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                orders.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void writeOrder(String order) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // Append mode
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(order);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(String order) throws IOException {
    File inputFile = new File(filePath);
    File tempFile = new File("src/temp.txt");

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    String currentLine;
    while ((currentLine = reader.readLine()) != null) {
        if (!currentLine.equals(order)) {
            writer.write(currentLine);
            writer.newLine();
        }
    }

    writer.close();
    reader.close();

    if (inputFile.delete()) {
        tempFile.renameTo(inputFile);
    } else {
        throw new IOException("Failed to delete the original file and rename the temporary file.");
    }
}

}
