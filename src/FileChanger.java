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

    public void setWorkfile(String workfile) {
        this.workfile = workfile;
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
        ArrayList<String> arrayListLines = new ArrayList<>(lines);  //may be redundant

        if (arrayListLines.get(0).equals(":filelist")) {

            for (String line : arrayListLines.subList( 1, arrayListLines.size() )) {    // skip first index

                System.out.println("Trying to open " + line);
                try {
                    Path loopPath = Paths.get(line);
                    List<String> loopLines = Files.readAllLines(loopPath);
                    ArrayList<String> loopArrayList = new ArrayList<>(loopLines);

                    for (String loopLine : loopArrayList) {
                        f.apply(loopLine);
                    }

                    System.out.println("Success!");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        else {
            throw new FileTypeException("First line of " + workfile + " is not \":filelist\"!");
        }


    }
}
