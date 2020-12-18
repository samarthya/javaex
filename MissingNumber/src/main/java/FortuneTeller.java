import javax.print.DocFlavor;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FortuneTeller {
    private final Logger logger = Logger.getLogger(FortuneTeller.class.getName());

    private final List<String> allData = new LinkedList<String>();
    private final File dbFile;

    public FortuneTeller(File dbFile) {
        this.dbFile = dbFile;
    }

    public FortuneTeller() {
        this(null);
        logger.log(Level.WARNING, "empty initialization");
    }

    public void populateDB() {
        this.populateDB(this.dbFile);
    }

    /**
     * Read the complete data.
     *
     * @param localFile
     */
    public void populateDB(File localFile) {
        if (localFile == null) {
            throw new IllegalArgumentException("NO FILE SPECIFIED: file name is invalid");
        }

        if (localFile.exists()) {
            System.out.println(" File found ");
            try {
                /**
                 * https://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html
                 */
                List<String> allLines = Files.readAllLines(localFile.toPath()).stream().collect(Collectors.toList());

                StringBuffer sb = new StringBuffer();

                for (String str : allLines) {

                    if (str.compareTo("%") != 0) {
                        sb.append(str);

                        if (allLines.indexOf(str) == allLines.size() - 1) {
                            allData.add(sb.toString() + "\n");
                            break;
                        } else {
                            sb.append("\n");
                            continue;
                        }
                    } else {
                        allData.add(sb.toString());
                        sb.setLength(0);
                    }
                }
                return;
            } catch (IOException e) {
                throw new InternalError("something went wrong");
            }
        } else {
            System.out.println("file not found: " + localFile.getPath());
        }
    }

    public String myFortune(int index) {
        if (allData.size() == 0) {
            System.out.println("no data in the system");
            throw new InternalError("no data in the system");
        }
        return allData.get(index % allData.size());
    }
}
