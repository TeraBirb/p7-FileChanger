import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileChanger {

    private String workfile;
    private Function<String> f;
    private String outputFormat;
    private String relativePath;

    public void setWorkfile(String workfile) {      // relative path
        relativePath = new File("").getAbsolutePath().concat("/");
        this.workfile = relativePath.concat(workfile);
        System.out.println(workfile + " workfile set.");
    }

    public void setF(Function<String> f) {
        this.f = f;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public void changeFiles() throws FileTypeException, IOException {

        System.out.println("Attempting to open workfile....");
        Path path = Paths.get(workfile);
        List<String> lines = Files.readAllLines(path);
        ArrayList<String> arrayListLines = new ArrayList<>(lines);

        if (arrayListLines.get(0).equals(":filelist")) {

            for (String line : arrayListLines.subList( 1, arrayListLines.size() )) {    // skip first index

                System.out.println("Trying to work on " + line);
                try {
                    Path loopPath = Paths.get(relativePath.concat(line));
                    List<String> loopLines = Files.readAllLines(loopPath);
                    ArrayList<String> loopArrayList = new ArrayList<>(loopLines);

                    // outputfile
                    FileWriter fileWriter = new FileWriter(line.substring(0, line.length() - 4) + outputFormat);
                    fileWriter.write("");

                    for (String loopLine : loopArrayList) {
                        fileWriter.append(f.apply(loopLine) + "\n");
                    }

                    fileWriter.close();
                    System.out.println("Successfully worked on " + line);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        else if (!arrayListLines.get(0).equals(":filelist")) {
            throw new FileTypeException("First line of " + workfile + " is not \":filelist\"!");
        }
        else {
            throw new IOException("Something went wrong!");
        }


    }
}
