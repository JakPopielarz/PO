package programowanieobiektowe.kolokwium2;

public class Book {
    // klasa przechowuje tylko tytuł, autora, wydawcę i rok wydania
    // więcej nie jest konieczne (z treści zadania)
    String title;
    String author;
    String publisher;
    int year;
    
    Book(String title, String author, String publisher, int year) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }
    
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        
        buf.append(title).append(", ");
        buf.append("by: ").append(author).append("; Published: ");
        buf.append(publisher).append(", ");
        buf.append(year);
        
        return buf.toString();
    }
}
