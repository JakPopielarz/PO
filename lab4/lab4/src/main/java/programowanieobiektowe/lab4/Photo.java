package programowanieobiektowe.lab4;

import java.io.PrintStream;

public class Photo {
    Photo(String url) {
        this.url = url;
    }
    
    String url;
    void writeHTML(PrintStream out) {
        out.printf("<img src=\"%s\" alt=\"My face\" height=\"42\" width=\"42\"/>\n", url);
    }
}
