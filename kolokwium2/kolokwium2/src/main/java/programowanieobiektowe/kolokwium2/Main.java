package programowanieobiektowe.kolokwium2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // ZADANIE 1
        System.out.println("ZADANIE 1");
        System.out.println("------------------");
        
        // przygotowanie do wczytywania z pliku
        CSVReader reader = new CSVReader("ibuk_wykaz_pozycji.csv", ";", true);
        List<Book> books = new ArrayList<>();
        
        // zapisanie danych o ksążkach w liście książek
        while (reader.next()) {
            // wczytujemy tylko tytuł, autora, wydawcę i rok wydania
            // więcej nie jest konieczne (z treści zadania)
            String title = reader.get("Tytuł");
            String author = reader.get("Autor");
            String publisher = reader.get("Wydawnictwo");
            int year = reader.getInt0("Rok wydania");
            books.add(new Book(title, author, publisher, year));
        }
        
        // a
        // Wypisanie książek, ttórych autorem/współautorem jest osoba o imieniu Marek
        for (Book book : books)
            if (book.author.contains("Marek"))
                System.out.println(book);
        
        // b
        // Stworzenie listy książek wydanych przez Wydawnictwo Naukowe PWN
        List<Book> booksPwn = new ArrayList<>();
        for (Book book : books)
            if ("Wydawnictwo Naukowe PWN".equals(book.publisher))
                booksPwn.add(book);
        
        // komparator, który porówna książki. Zostanie wykorzystany do posortowania
        // według roku wydania
        class Com implements Comparator<Book> {
            @Override
            public int compare(Book b,Book b1) {
                if (b.year > b1.year)
                    return 1;
                else if (b.year < b1.year)
                    return -1;
                return 0;
            }        
        }
        
        // wypisanie posortowanych książek
        System.out.println();
        booksPwn.sort(new Com());
        for (Book book : booksPwn)
            System.out.println(book);
        
        // c
        // stworzenie mapy przypisującej liczbę książek wydanych w danym roku
        // do tego roku
        Map<Integer, Integer> yearToCount = new HashMap<>();
        for (Book book: books) {
            // określenie roku
            Integer year = book.year;
            // ustawienie liczby książek - jeżeli w mapie nie ma klucza: 0,
            // jeżeli jest: wartość z mapy
            Integer count = 0;
            if (yearToCount.containsKey(year))
                count = yearToCount.get(year);
            // zapisanie w mapie roku oraz liczby książek już sprawdzonych i
            // wydanych w danym roku
            yearToCount.put(year, count+1);
        }
        
        // wypisanie
        System.out.println();
        System.out.println(yearToCount);
        
        // d
        // Stworzenie mapy przypisującej wydawnictwu liczbę wydanych przez nie
        // książek
        Map<String, Integer> publisherToCount = new HashMap<>();
        for (Book book: books) {
            // określenie wydawcy
            String publisher = book.publisher;
            // ustawienie liczby książek - jeżeli w mapie nie ma klucza: 0,
            // jeżeli jest: wartość z mapy
            Integer count = 0;
            if (publisherToCount.containsKey(publisher))
                count = publisherToCount.get(publisher);
            // przypisanie wydawnictwu w mapie liczby książek, które już zostały
            // sprawdzone i są wydane przez nie
            publisherToCount.put(publisher, count+1);
        }
        
        // wypisanie liczby książek wydanych przez poszczególne wydawnictwa
        System.out.println();
        System.out.println(publisherToCount);
        
        // ZADANIE 2
        // separator
        System.out.println("------------------");
        System.out.println("ZADANIE 2");
        System.out.println("------------------");
        // wczytanie pliku
        String w_pustyni = readFile("w-pustyni.txt", Charset.forName("cp1250"));
        // a
        // regex z treści zadania - nie odfiltrowuje znaków " oraz wielokropka 
        // (liczonego jako jeden znak)
        String[] words = w_pustyni.split("[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?]+");

        // b
        // Stworzenie mapy przechowującej powiązanie długości słowa ze zbiorem
        // słów tej długości
        Map<Integer, Set<String>> lengthToWords = new HashMap<>();
        for (String word : words) {
            Integer len = word.length();
            Set<String> wordSet = new HashSet<>();
            // sprawdzenie czy istnieje już w mapie dana para klucz => zbiór
            if (lengthToWords.containsKey(len))
                wordSet = lengthToWords.get(len);
            // dodanie słowa do zbioru
            wordSet.add(word);
            lengthToWords.put(len, wordSet);
        }
        
        // c
        Set<Integer> lengths = lengthToWords.keySet();
        Integer minLen = 1000;
        Integer maxLen = 0;
        float meanLen = 0;
        // Znajdź największą, najmniejszą oraz średnią długość
        for (Integer length : lengths) {
            if (length < minLen)
                minLen = length;
            if (length > maxLen)
                maxLen = length;
            meanLen += length;
        }
        meanLen = meanLen / lengths.size();
        
        // Wypisz najkrótsze
        System.out.println(lengthToWords.get(minLen));
        // Wypisz najdłuższe
        System.out.println(lengthToWords.get(maxLen));
        // Zaokrąglenie średniej długości
        Integer meanLenKey = Math.round(meanLen);
        // Wypisz średnie
        System.out.println(lengthToWords.get(meanLenKey));
    }
    
    static String readFile(String name, Charset charset) {
        StringBuilder s = new StringBuilder();
        try (BufferedReader file = new BufferedReader(new InputStreamReader( new FileInputStream(name), charset))) {
            for (;;) {
                int c=file.read();
                if (c<0) break;
                s.append((char) c);
            }
            return s.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
