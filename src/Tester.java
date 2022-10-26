import java.io.IOException;

public class Tester {

    public static void main(String[] args) throws FileTypeException, IOException {

        FileChanger test = new FileChanger();
        test.setWorkfile("workFile.txt");
        test.setF( (String l) -> {
            StringBuilder string = new StringBuilder(l);
            for (int i = 0; i < string.length() / 2; i++) {     // reverse letters in each element
                char temp = string.charAt(i);
                string.setCharAt(i, string.charAt(l.length() - 1 - i));
                string.setCharAt(string.length() - 1 - i, temp);
            }

            return String.valueOf(string);
        });
        test.setOutputFormat("_reversified.txt");
        test.changeFiles();

        test.setF( (String l) -> l.toUpperCase() + "!!!");
        test.setOutputFormat("_angryified.txt");
        test.changeFiles();




    }

}
