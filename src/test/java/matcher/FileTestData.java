package matcher;


public class FileTestData {
    public static final MatcherFactory.Matcher<String> FILE_MATCHER = MatcherFactory.getMatcher();


    public static final String FILE_PATH = "src/test/resources/test-files.xml";


    public static final String FULL_SEARCH = """
            /file-776194140.xml\r
            /dir-880176375/file-1073842118.java\r
            /dir-880176375/dir-2145307015/file-1498940214.xhtml\r
            """;

    public static final String EXACT_SEARCH = "/dir-880176375/dir-2145307015/file-1498940214.xhtml";

    public static final String MASK_SEARCH = "/dir-880176375/file-1073842118.java";

    public static final String REGEX_SEARCH = """
            /file-776194140.xml\r
            /dir-880176375/file-1073842118.java\r
            /dir-880176375/dir-2145307015/file-1498940214.xhtml\r
            """;

}
