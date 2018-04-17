/*
 * Copyright (C) 2018 Lorenzo Marietta
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package editdistance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo Marietta
 */
public class Corrector {

    public static void main(String[] args) {
        try {
            Corrector c = new Corrector("src/editdistance/dictionary.txt",
                    "src/editdistance/correctme.txt",
                    "src/editdistance/corrected.txt");
            c.correct();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Corrector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private final String dictionaryPath;
    private final String textPath;
    private final String newTextPath;

    private BufferedReader dictionary;
    private BufferedReader text;
    private PrintWriter newText;

    private ArrayList<String> dictionaryArray;
    private ArrayList<String> textArray;
    
    public static int index;

    public Corrector(String dictionaryPath, String textPath, String newTextPath) {
        this.dictionaryPath = dictionaryPath;
        this.textPath = textPath;
        this.newTextPath = newTextPath;
        dictionaryArray = new ArrayList<>();
        textArray = new ArrayList<>();
    }

    public void correct() throws FileNotFoundException, IOException, InterruptedException {
        openFile();
        
        populateDictionaryArray();
        populateTextArray();
        
        int size = textArray.size()/4;
        int section = size;
        
        Performer p1 = new Performer(textArray, dictionaryArray,0,section);
        Performer p2 = new Performer(textArray, dictionaryArray,section+1,section*2);
        Performer p3 = new Performer(textArray, dictionaryArray,section*2+1,section*3);
        Performer p4 = new Performer(textArray, dictionaryArray,section*3+1,section*4+size%4-1);
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        
        p1.join();
        p2.join();
        p3.join();
        p4.join();
        
        for(int i=p1.getCorrectedText().size()-1;i>=0;i--){
            newText.write("("+p1.getCorrectedText().get(i)+")");
        }
        for(int i=p2.getCorrectedText().size()-1;i>=0;i--){
            newText.write("("+p2.getCorrectedText().get(i)+")");
        }
        for(int i=p3.getCorrectedText().size()-1;i>=0;i--){
            newText.write("("+p3.getCorrectedText().get(i)+")");
        }
        for(int i=p4.getCorrectedText().size()-1;i>=0;i--){
            newText.write("("+p4.getCorrectedText().get(i)+")");
        }
        closeFile();
    }
    
    
    private void populateDictionaryArray() {
        String s;
        while ((s = readDictionary()) != null) {
            dictionaryArray.add(s);
        }
    }

    private void populateTextArray() {
        String s;
        while ((s = readWord()) != null) {
            if (!s.equals("")) {
                textArray.add(s);
            }
        }
    }

    private void openFile() throws FileNotFoundException {
        dictionary = new BufferedReader(new FileReader(dictionaryPath));
        text = new BufferedReader(new FileReader(textPath));
        newText = new PrintWriter(newTextPath);
    }

    private void closeFile() throws IOException {
        dictionary.close();
        text.close();
        newText.close();
    }

    private String readDictionary() {
        try {
            return dictionary.readLine();
        } catch (IOException ex) {
            System.out.print("IO exeption, terminating program");
            System.exit(1);
        }
        return null;
    }

    private String readWord() {
        int ch;
        String result = "";
        while (((ch = readChar()) >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
            result = result + (char) ch;
        }
        if (ch == -1) {
            return null;
        } else {
            return result;
        }
    }

    private int readChar() {
        try {
            return text.read();
        } catch (IOException ex) {
            System.out.print("IO exeption, terminating program");
            System.exit(1);
        }
        return -1;
    }
    
}
