package programowanieobiektowe.kolokwium2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public CSVReader(String filename, String delimiter, boolean hasHeader) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        
        if(hasHeader) {
            try {
                parseHeader();
            } catch (IOException ex) {
                Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public CSVReader(String filename, String delimiter) {
        this(filename, delimiter, true);
    }
    
    public CSVReader(String filename) throws IOException {
        this(filename, ",", true);
    }
    
    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        
        if (hasHeader) {
            try {
                parseHeader();
            } catch (IOException ex) {
                Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
            String line = reader.readLine();
            if (line != null) {
                current = line.split(delimiter);
                
                // sprawdzenie czy niepotrzebnie nie rozdzieliliśmy tytułu
                for (int i=0; i<current.length; i++) {
                    if (current[i].startsWith("\"")) {
                        // jeżeli tak - złączenie części tytułu i przesunięcie
                        // elementów tablicy w lewo (aby nie było pustego miejsca
                        // w środku rekordu danych)
                        current[i] = current[i] + current[i+1];
                        for (int j=i+2; j<current.length; j++) {
                            current[j-1] = current[j];
                                    
                        }
                    }
                }
                
                return true;
            } else return false;
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
        if (columnIndex >= current.length) return true;
        else return "".equals(current[columnIndex]);
    }
    
    boolean isMissing(String columnLabel) {
        return !columnLabels.contains(columnLabel);
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
        String value = get(columnIndex);
        return Integer.parseInt(get(columnIndex));
    }
    
    int getInt(String columnLabel) {
        return getInt(columnLabelsToInt.get(columnLabel));
    }
    
    long getLong(int columnIndex) {
        return Long.parseLong(get(columnIndex));
    }
            
    long getLong(String columnLabel) {
        return getLong(columnLabelsToInt.get(columnLabel));
    }

    double getDouble(int columnIndex) {
        return Double.parseDouble(get(columnIndex));
    }
            
    double getDouble(String columnLabel) {
        return getDouble(columnLabelsToInt.get(columnLabel));
    }
    
    int getInt0(int columnIndex) {
        if (isMissing(columnIndex)) return 0;
        String value = get(columnIndex);
        return Integer.parseInt(get(columnIndex));
    }
    
    int getInt0(String columnLabel) {
        if (isMissing(columnLabel)) return 0;
        return getInt0(columnLabelsToInt.get(columnLabel));
    }
    
    long getLong0(int columnIndex) {
        if (isMissing(columnIndex)) return 0;
        return Long.parseLong(get(columnIndex));
    }
            
    long getLong0(String columnLabel) {
        if (isMissing(columnLabel)) return 0;
        return getLong0(columnLabelsToInt.get(columnLabel));
    }

    double getDouble0(int columnIndex) {
        if (isMissing(columnIndex)) return 0;
        return Double.parseDouble(get(columnIndex));
    }
            
    double getDouble0(String columnLabel) {
        if (isMissing(columnLabel)) return 0;
        return getDouble0(columnLabelsToInt.get(columnLabel));
    }
}
