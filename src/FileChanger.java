public class FileChanger {

    private String workfile;
    private Function<String> f;
    private String outputFormat;

    public void setWorkfile(String workfile) {
        this.workfile = workfile;
    }

    public void setF(Function<String> f) {
        this.f = f;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public void changeFiles() throws FileTypeException {

    }
}
