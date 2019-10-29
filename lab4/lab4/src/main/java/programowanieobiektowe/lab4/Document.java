package programowanieobiektowe.lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document {
    Document(String title) {
        this.title = title;
    }
    
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();
    
    Document setTitle(String title) {
        this.title = title;
        return this;
    }
    
    Document setPhoto(String photoUrl) {
        Photo newPhoto = new Photo(photoUrl);
        photo = newPhoto;
        return this;
    }
    
    Section addSection(String sectionTitle) {
        Section section = new Section(sectionTitle);
        sections.add(section);
        
        return section;
    }
    
    Document addSection(Section s) {
        return this;
    }
    
    void writeHTML(PrintStream out) {
        out.printf("<head>\n<title>%s's CV</title>\n</head>\n", title);
        out.printf("<body>\n<h1>%s</h1>\n", title);
        photo.writeHTML(out);
        for (Section section: sections)
            section.writeHTML(out);
        out.println("</body>");
    }
}
