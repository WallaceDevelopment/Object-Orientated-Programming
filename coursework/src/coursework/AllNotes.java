/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//This code fully works with a seperate courselist file loading up before the whole file, all read and write methods work.

/**
 *
 * @author LeeWallace
 */
public class AllNotes extends CommonCode {
    
    private ArrayList<Note> allNotes = new ArrayList<>();
    private ArrayList<Note> courseNotes = new ArrayList<>();
    private String crse = "";
    private int maxID = 0;
    
    
    
    AllNotes() {
        
        courseEntry();
        //readCourse();
        readAllNotes();
        
    
}
    
    // The ArrayList showCourses reads from "CourseList.txt" and converts 
    // itself into an Array "arrayCourses", this is because the 
    // JOptionPane cannot accept ArrayLists, only Arrays.
    
    public ArrayList<String> showCourses  = readTextFile("CourseList.txt"); // Reading from "CourseList.txt"
    Object[] arrayCourses = showCourses.toArray();
        
    // Function to create a frame upon program start. Will ask the user to select
    // a course, once selected it will then display within txtDisplayNotes.
    
    public void courseEntry() {
        
        JFrame courseEntryFrame = new JFrame("Input Dialog");
        String courseSelection = (String) JOptionPane.showInputDialog(courseEntryFrame,
                "Please select a course",
                "Course Entry",
                JOptionPane.QUESTION_MESSAGE,
                null,
                arrayCourses,
                arrayCourses[0]);
        
        userEntry = courseSelection;
        
        //System.out.println("Selected Course " +" "+ courseSelection);
        
          }
    
   
    public String courseSelect = userEntry;
    
        
    
    public final int getMaxID() {
        maxID++;
          return maxID;      
    }
    
    protected void readAllNotes() {
        ArrayList<String> readNotes = new ArrayList<>();
        
        readNotes = readTextFile(appDir + fileSeparator + userEntry + ".txt");
        System.out.println(readNotes.get(0));
        
        if (!"File not found".equals(readNotes.get(0))) {
            allNotes.clear();
            for (String str : readNotes) {
                String[] tmp = str.split("\t");
                
                int nid = Integer.parseInt(tmp[0]);
                Note n = new Note(nid, tmp[1], tmp[2], tmp[3]);
                allNotes.add(n);
                
                
                if (nid > maxID) {
                    maxID = nid;
                }
                
            }
        }
        maxID++;
    }
    
    
    public void addNote(int maxID, String course, String note) {
        Note myNote = new Note(maxID, course, note);
        allNotes.add(myNote);
        writeAllNotes();
    }
    
    
    
    public String searchAllNotesByKeyword(String noteList, int i, String s) {
        if (i == allNotes.size()) {
            return noteList;
        }
        
        if (allNotes.get(i).getNote().contains(s)) {
            noteList += allNotes.get(i).getNote() + "\n";
        }
        return searchAllNotesByKeyword(noteList, i + 1, s);
    }

    /*
    public void addNote(int noteID, String course, String note) {
        Note myNote = new Note();
        myNote.setNoteID(noteID);
        myNote.setCourse(course);
        myNote.setDayte();
        myNote.setNote(note);
        allNotes.add(myNote);
        writeAllNotes();
    }
*/
    
    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }
    
    
    
    private void writeAllNotes() {
        String path = appDir + fileSeparator + userEntry +".txt";
        ArrayList<String> writeNote = new ArrayList<>();
        
        for (Note n : allNotes) {
            String tmp = n.getNoteID() + "\t";
            tmp += n.getCourse() + "\t";
            tmp += n.getDayte() + "\t";
            tmp += n.getNote();
            writeNote.add(tmp);
            
        }
        try {
            writeTextFile(path, writeNote);
        } catch (IOException ex) {
            System.out.println("Problem!" + path);
            
        }
    }
    
    
}
