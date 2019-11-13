package programowanieobiektowe.lab6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    // nazwy kolumn w kolejności takiej, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[] current;
    
    /**
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     * @throws java.io.FileNotFoundException
     */
    
    public CSVReader(String filename, String delimiter, boolean hasHeader) throws FileNotFoundException, IOException {
        reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        
        if(hasHeader) parseHeader();
    }
    
    public CSVReader(String filename, String delimiter) throws IOException {
        this(filename, delimiter, true);
    }
    
    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }
    
    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        
        if (hasHeader) parseHeader();
    }
    
    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line==null) return;
        
        String[] header = line.split(delimiter);
        for (int i=0; i<header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i], i);
        }
    }
    
    boolean next() {
        try {
            current = reader.readLine().split(delimiter);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    List<String> getColumnLabels() {
        return columnLabels;
    }
    
    int getRecordLength() {
        return current.length;
    }
    
    boolean isMissing(int columnIndex) {
        if (columnIndex > current.length) return true;
        else return "".equals(current[columnIndex]);
    }
    
    boolean isMissing(String columnLabel) {
        return columnLabels.contains(columnLabel);
    }
    
    String get(int columnIndex) {
        if (isMissing(columnIndex))
            return "";
        else return current[columnIndex];
    }
    
    String get(String columnLabel) {
        return get(columnLabelsToInt.get(columnLabel));
    }
    
    int getInt(int columnIndex) {
        return Integer.parseInt(get(columnIndex));
    }
    
    int getInt(String columnLabel) {
        return getInt(columnLabelsToInt.get(columnLabel));
    }
}
