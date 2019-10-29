/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe.lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ParagraphTest {
    
    public ParagraphTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of setContent method, of class Paragraph.
     */
    @Test
    public void testSetContent() {
        System.out.println("setContent");
        String content = "Abc lorem ipsum";
        Paragraph instance = new Paragraph("sadasd");
        Paragraph expResult = new Paragraph(content);
        instance.setContent(content);
        assertEquals(expResult.content, instance.content);
    }

    /**
     * Test of writeHTML method, of class Paragraph.
     */
    @Test
    public void testWriteHTML() {
        System.out.println("writeHTML");
        
        String content = "Test content";
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        // Utwórz obiekt i zapisz do strumienia
        new Paragraph(content).writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ParagraphTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains(content));
    }
}
