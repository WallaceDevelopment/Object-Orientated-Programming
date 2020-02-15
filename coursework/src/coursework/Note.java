/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

/**
 *
 * @author LeeWallace
 */
public class Note extends CommonCode {
    private int noteID = 0;
    private String course = "";
    private String dayte = "";
    private String note = "";
    
    
    public Note() {
        
    }
    
    public Note(int nid, String crs, String dt, String nt) {
        setNoteID(nid);
        setCourse(crs);
        setDayte(dt);
        setNote(nt);
    }
    
    public Note (int max, String crs, String nt) {
        setNoteID(max);
        setCourse(crs);
        setDayte();
        setNote(nt);
    }
    
    public void setNoteID(int n) {
        int nid = n;
        //Any Validation goes here
        noteID = nid;
    }
    
    public void setCourse(String c) {
        String crse = c;
        //Any Validation goes here
        course = crse;
    }
    
    public int getNoteID() {
        //Any checking goes here
        return noteID;
    }
    
    public String getCourse() {
        //Any checking goes here
        return course;
    }
    
    public void setDayte() {
        dayte = orderedDate;
    }
    
    public final void setDayte(String dt) {
        dayte = dt;
    }
    
    public String getDayte() {
        return dayte;
    }
    
    public void setNote(String n) {
        //Any validation goes here
        note = n;
    }
    
    public String getNote() {
        //Any checking goes here
        return note;
    }
    
    

    private void storeThisNote() {
        System.out.println("storeThisNote Not Coded Yet"); //To change body of generated methods, choose Tools | Templates.
    }
}

