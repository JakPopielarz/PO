package programowanieobiektowe.lab4;

import java.io.PrintStream;

public class Paragraph {
    Paragraph(String content) {
        this.content = content;
    }
    
    String content;
    
    Paragraph setContent(String content) {
        this.content = content;
        return this;
    }
    
    void writeHTML(PrintStream out) {
        out.printf("<p>%s</p>\n", content);
    }
}
