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


/**
 *
 * @author LeeWallace
 */
public class CWDetailsAllNotes extends CommonCode {
    

    private ArrayList<Note> allNotes = new ArrayList<>();
    private String crse = "";
    private int maxID = 0;
    
    
    CWDetailsAllNotes() {
        
        CWcourseEntry();
        readAllNotes();
        
    
}
    
    // The ArrayList showCourses reads from "CourseList.txt" and converts 
    // itself into an Array "arrayCourses", this is because the 
    // JOptionPane cannot accept ArrayLists, only Arrays.
    
    public ArrayList<String> CWshowCourses  = readTextFile("CourseList.txt"); // Reading from CourseList.txt
    Object[] CWarrayCourses = CWshowCourses.toArray();
    
    // Function to create a frame upon program start. Will ask the user to select
    // a course, once selected it will then display within txtDisplayNotes.
    
    public void CWcourseEntry() {
        
        JFrame CWcourseEntryFrame = new JFrame("Input Dialog");
        String CWcourseSelection = (String) JOptionPane.showInputDialog(CWcourseEntryFrame,
                "Open requirements for which course?",
                "Course Entry",
                JOptionPane.QUESTION_MESSAGE,
                null,
                CWarrayCourses,
                CWarrayCourses[0]);
        
        CWuserEntry = CWcourseSelection;
        
          }
    
    public final int getMaxID() {
        maxID++;
          return maxID;      
    }
    
    protected void readAllNotes() {
        ArrayList<String> readNotes = new ArrayList<>();
        
        readNotes = readTextFile(appDir + fileSeparator + CWuserEntry+ "CW.txt");
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
        String path = appDir + fileSeparator + CWuserEntry + "CW.txt";
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
