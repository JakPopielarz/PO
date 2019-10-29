package programowanieobiektowe.lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    Section(String title) {
        this.title = title;
    }
    
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();
    
    Section setTitle(String title) {
        this.title = title;
        return this;
    }
    
    Section addParagraph(String paragraphText) {
        Paragraph paragraph = new Paragraph(paragraphText);
        paragraphs.add(paragraph);
        return this;
    }
    
    Section addParagraph(Paragraph p) {
        paragraphs.add(p);
        return this;
    }
    
    void writeHTML(PrintStream out) {
        out.printf("<h2>%s</h2>\n", title);
        for (Paragraph paragraph: paragraphs)
            paragraph.writeHTML(out);
    }
}
