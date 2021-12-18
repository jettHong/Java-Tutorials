import info.monitorenter.cpdetector.CharsetPrinter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TestMain {
    public static void main(String args[]) throws IOException {
        String[] files = {
                "README-BIG5.md",
                "README-GBK.md",
                "README-UTF8.md"
        };
        CharsetPrinter charsetPrinter = new CharsetPrinter();
        Arrays.stream(files).forEach(i -> {
            try {
                System.out.println((charsetPrinter.guessEncoding(new File(i))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
    }
}
