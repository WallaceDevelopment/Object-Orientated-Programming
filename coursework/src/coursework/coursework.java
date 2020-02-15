/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JComboBox;


/**
 *
 * @author lw7966u
 */

public class coursework extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();
    JPanel pnl = new JPanel(new BorderLayout());
    JTextArea txtNewNote = new JTextArea(5,20);
    JTextArea txtDisplayNotes = new JTextArea();
    JTextField search = new JTextField();
    ArrayList<String> course = new ArrayList<>();
    ArrayList<String> note = new ArrayList<>();
    JComboBox courseList = new JComboBox();
    String crse = "";
    AllNotes allNotes = new AllNotes();
    
    
    
    
    public static void main(String[] args) {
        
        
        
        coursework prg = new coursework();

    }
    
    // Using MVC 
    public coursework() {
        
        model();
        view();
        controller();

    }
    
    
    private void model() {
        
        //Coursework additional code
        /*
        course.add("COMP1752");
        course.add("COMP1713");
        course.add("COMP1589");
        course.add("COMP1681");
        crse = course.get(0);
        */
        
     }

    private void view() {
        Font fnt = new Font("Georgia", Font.PLAIN, 24);

        
        JMenuBar menuBar = new JMenuBar();       
        JMenu note = new JMenu();
        
        JMenu courses = new JMenu();

        note = new JMenu("File");
        note.setToolTipText("Note tasks");
        note.setFont(fnt);

        note.add(makeMenuItem("New", "NewNote", "Create a new note", fnt));
        note.add(makeMenuItem("Open Coursework Window", "Coursework", "Open the Coursework Window", fnt));
        note.addSeparator();
        note.add(makeMenuItem("Close", "Close", "Clear the current note", fnt)); 
        
        menuBar.add(note);
        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program", fnt));
        

        this.setJMenuBar(menuBar);
        
        JToolBar toolBar = new JToolBar();
        // Setting up the button bar
        JButton button = null;
        button = makeButton("Document", "Requirements",
                "Open the requirements window.",
                "Coursework");
        toolBar.add(button);
        button = makeButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);
        button = makeButton("edit", "Edit",
                "Add a New Course",
                "Edit");
        toolBar.add(button);
        
        
        toolBar.addSeparator();
        
        //This forces anything after it to the right
        toolBar.add(Box.createHorizontalGlue());
        
        search.setMaximumSize(new Dimension(6900, 30));
        search.setFont(fnt);
        toolBar.add(search);
        toolBar.addSeparator();
        button = makeButton("Search", "SearchKeyword",
                "Search for this text.",
                "Search");
        toolBar.add(button);
        button = makeButton("Refresh", "SearchReset",
                "Reset the Search Box",
                "Search");
        toolBar.add(button);
        

        add(toolBar, BorderLayout.NORTH);
        
        toolBar.addSeparator();
        button = makeButton("exit", "Exit",
                "Exit from this program",
                "Exit");
        toolBar.add(button);

        
        //<editor-fold desc="Code to add a top panel">
        /*
        
        Code to add a top Panel.
        
        JPanel pnlTop = new JPanel();
        pnlTop.add(menuBar);
        add(pnlTop, BorderLayout.NORTH);
        
        
        */
        //</editor-fold>
        
        //West Panel with Button
        
        JPanel pnlWest = new JPanel();
        pnlWest.setLayout(new BoxLayout(pnlWest, BoxLayout.Y_AXIS));
        pnlWest.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        txtNewNote.setFont(fnt);
        pnlWest.add(txtNewNote);
        
        JButton btnAddNote = new JButton("Add Note");
        btnAddNote.setActionCommand("NewNote");
        btnAddNote.addActionListener(this);
        pnlWest.add(btnAddNote);
        
        add(pnlWest, BorderLayout.WEST);
        
        //Center Panel with Button
        
        JPanel cen = new JPanel();
        cen.setLayout(new BoxLayout(cen, BoxLayout.Y_AXIS));
        cen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtDisplayNotes.setFont(fnt);
        cen.add(txtDisplayNotes);
        
        add (cen, BorderLayout.CENTER);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Coursework - Lee Wallace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        for (String crse : course) {
            courseList.addItem(crse);
        }
        courseList.setFont(fnt);
        courseList.setMaximumSize(courseList.getPreferredSize());
        courseList.addActionListener(this);
        courseList.setActionCommand("Course");
        menuBar.add(courseList);
        */
        
        
        // <editor-fold desc="Old Code, colour change">
        
        /* 
        JButton btnShowText = new JButton("Show Note");

        btnShowText.setActionCommand("NewNote");
        btnShowText.setForeground(Color.blue); // This code changes the button label colour.
        btnShowText.addActionListener(this);

        pnlWest.add(btnShowText);
        pnlWest.add(txtShowText);
        
        String html = "<html><body>Hello <b> Lee</b></body></html>";
        JLabel cenLbl = new JLabel(html);
        cenLbl.setFont(fnt);
        cen.add(cenLbl);
        add(BorderLayout.CENTER, cen); 
        */

         // </editor-fold> 
        

        setVisible(true); //ensure that items can be seen.
    }
    
    private void controller() {
        addAllNotes();
    }
    
    protected JButton makeButton(
    String imageName,
    String actionCommand,
    String toolTipText,
    String altText) {
        
        // Operating System directory route
        String os = System.getProperty("os.name");
        int firstIndex = os.indexOf("Mac");
                        
        String osType = "";
        
        if (firstIndex == (0)) {
            osType = "//";
        } else {
            osType = "\\";
        }
        
        String slsh = (osType + "icons" + osType);
        
        //Create and initialize the button
        
        JButton button = new JButton();
        button.setToolTipText(toolTipText);
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        
        //Look for the image
        
        String imgLocation = System.getProperty("user.dir")
                + slsh
                + imageName
                + ".png";
        
        File fyle = new File (imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            //image found
            Icon img;
            img = new ImageIcon (imgLocation);
            button.setIcon(img);
        } else {
            // image not found
            button.setText(altText);
            System.err.println("Resource not found:" + imgLocation);
            
        }
        
        return button;
           
    }

    protected JMenuItem makeMenuItem(
            String txt,
            String actionCommand,
            String toolTipText,
            Font fnt) {

        JMenuItem mnuItem = new JMenuItem();
        mnuItem.setText(txt);
        mnuItem.setActionCommand(actionCommand);
        mnuItem.setToolTipText(toolTipText);
        mnuItem.setFont(fnt);
        mnuItem.addActionListener(this);

        return mnuItem;
    }
    
    /*
    
    private void addNote(String text) {
        note.add(txtNewNote.getText());
        addAllNotes();
    }
    
    */
    
    private void addAllNotes() {
        String txtNotes = "";
        
        for (Note n : allNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }
        
        txtDisplayNotes.setText(txtNotes);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if ("Coursework".equals(ae.getActionCommand())) {
            System.gc();
            java.awt.Window win[] = java.awt.Window.getWindows();
            for (int i =0;i<win.length;i++) {
                win[i].dispose();
                win[i]=null;
            }
            CWDetails cw = new CWDetails();
        }
        if ("NewNote".equals(ae.getActionCommand())) { 
            if (txtNewNote.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Cannot input empty text.",
                        "Empty String",
                        JOptionPane.WARNING_MESSAGE);
                txtNewNote.setText("");
            } else {
            addNote(txtNewNote.getText());
            txtNewNote.setText(""); }   
        }
        if ("Edit".equals(ae.getActionCommand())) {
            try {
                cc.appendFile(); 
            } catch (IOException ex) {
                Logger.getLogger(coursework.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("SearchKeyword".equals(ae.getActionCommand())) {
            String lyst = allNotes.searchAllNotesByKeyword("", 0, search.getText());
            txtDisplayNotes.setText(lyst);
        }
        if ("SearchReset".equals(ae.getActionCommand())) {
            String lyst2 = allNotes.searchAllNotesByKeyword("", 0, "");
            txtDisplayNotes.setText(lyst2);
            search.setText("");
        }
        
    }
    
    private void addNote(String text) {
        allNotes.addNote(allNotes.getMaxID(), crse, text);
        addAllNotes();
    }
    
    

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
