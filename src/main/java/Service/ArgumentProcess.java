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
        if(args.length <= 1) {
            throw new ArgumentException("There must be at least 2 parameters");
        }

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
                    } else if(args[2].equals(XConstant.KEY_MACK_REGULAR)) {
                        mask = args[3];
                        type = SearchType.Regular;
                    } else {
                        throw new ArgumentException("Third parameter must be -s or -S");
                    }
                }
                default -> throw new ArgumentException("The number of parameters isn't correct");
            }
        } else {
            throw new ArgumentException("First parameter must be -f");
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
