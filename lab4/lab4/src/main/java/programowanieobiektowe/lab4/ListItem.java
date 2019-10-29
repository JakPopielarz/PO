package programowanieobiektowe.lab4;

import java.io.PrintStream;

public class ListItem {
    ListItem(String content) {
        this.content = content;
    }
    
    String content;
    
    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n", content);
    }
}
