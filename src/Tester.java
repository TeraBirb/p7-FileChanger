import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws FileTypeException, IOException {

        FileChanger test = new FileChanger();
        test.setWorkfile("/Users/user/IdeaProjects/Project7FileChanger/src/workFile.txt");
        test.setF( (String l) -> l + "...");
        test.setOutputFormat("_modified");

        test.changeFiles();





//        test.changeFiles();

    }

}
