package programowanieobiektowe.lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jakub
 */
public class PhotoTest {
    
    public PhotoTest() {
    }
    
    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() {
    }
    
    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() {
    }
    
    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
    }
    
    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
    }

    /**
     * Test of writeHTML method, of class Photo.
     */
    @org.junit.jupiter.api.Test
    public void testWriteHTML() throws Exception {
        String imageUrl = "jan-kowalski.png";
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        new Photo(imageUrl).writeHTML(ps);
        String result = null;
        // Pobierz jako String
        result = os.toString("ISO-8859-2");
        
        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("src="));
        assertTrue(result.contains(imageUrl));
    }
    
}
