package Service;
import Exception.ArgumentException;

import Comparator.SearchType;
import Constant.XConstant;

import java.io.File;

public class ArgumentProcess {
    private String inputFileName;
    private String mask;
    SearchType type;

    public String getInputFileName() {
        return inputFileName;
    }

    public String getMask() {
        return mask;
    }

    public SearchType getType() {
        return type;
    }

    public ArgumentProcess(String[] args) {
        validator(args);
    }

    private void validator(String[] args) {
        if(args[0].equals(XConstant.KEY_INPUT_FILE)) {
           inputFileName = fileExists(args[1]);
            switch (args.length) {
                case 2 -> type = SearchType.Full;

                case 4 -> {
                    if(args[2].equals(XConstant.KEY_MACK)) {
                        mask = args[3];
                        if(mask.matches(".\\*\\.\\w+.")) {
                            type = SearchType.Mask;
                        } else {
                            type = SearchType.Equals;
                        }
                    }

                    if(args[2].equals(XConstant.KEY_MACK_REGULAR)) {
                        mask = args[3];
                        type = SearchType.Regular;
                    }

                    if(mask.isEmpty() && type == null) {
                        throw new ArgumentException("invalid flag " + args[2]);
                    }
                }
                default -> throw new ArgumentException("invalid flag " + args[0]);
            }
        }
    }

    private static String fileExists(String fileName) {
        String path = System.getProperty("user.dir");
        File f = new File(path + File.separator + fileName);
        if(f.exists() && f.isFile()){
            return f.getAbsolutePath();
        } else{
            throw new ArgumentException("input file doesn't exists");
        }
    }
}
