package coursework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

/**
 *
 * @author LeeWallace
 */
public class CWDetails extends JFrame implements ActionListener, KeyListener {

    CommonCode cc = new CommonCode();

    JPanel pnl = new JPanel(new BorderLayout());
    JTextField txtShowText = new JTextField();
    JTextArea txtDisplayNotes = new JTextArea();
    ArrayList<String> course = new ArrayList<>();
    JComboBox courseList = new JComboBox();
    String crse = "";
    JTextArea txtNewNote = new JTextArea(5,20);
    CWDetailsAllNotes CWallNotes = new CWDetailsAllNotes();
    //JTextArea txtDisplayNotes = new JTextArea();
    
    
    // Using MVC
    public CWDetails() {
        model();
        view();
        controller();
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
        note.addSeparator();
        note.add(makeMenuItem("Close", "Close", "Clear the current note", fnt)); 
        
        menuBar.add(note);
        menuBar.add(makeMenuItem("Exit", "Exit", "Close this program", fnt));
        
        this.setJMenuBar(menuBar);
        
        JToolBar toolBar = new JToolBar();
        // Setting up the button bar
        JButton button = null;
        button = makeButton("Home", "Return2Notes",
                "Return to the notes window.",
                "New");
        toolBar.add(button);

        button = makeButton("Create", "NewNote",
                "Create a new note.",
                "New");
        toolBar.add(button);
        button = makeButton("closed door", "Close",
                "Close this note",
                "Close");
        toolBar.add(button);
        
        toolBar.addSeparator();
        
        add(toolBar, BorderLayout.NORTH);
        
        toolBar.addSeparator();
        button = makeButton("exit", "Exit",
                "Exit from this program",
                "Exit");
        toolBar.add(button);
        
        
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

        setVisible(true); //ensure that items can be seen.
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

        File fyle = new File(imgLocation);
        if (fyle.exists() && !fyle.isDirectory()) {
            //image found
            Icon img;
            img = new ImageIcon(imgLocation);
            button.setIcon(img);
        } else {
            // image not found
            button.setText(altText);
            System.err.println("Resource not found:" + imgLocation);

        }

        return button;

    }

    private void model() {
        /*
        course.add("COMP1752");
        course.add("COMP1713");
        course.add("COMP1589");
        course.add("COMP1681");
        crse = course.get(0);
        */ 
    }

    private void controller() {
        
        addAllNotes();
        
        System.out.println("controller not coded yet.");
    }
    
    /*
    private void addCourse() {
        
        for (String a : course){
            txtDisplayNotes.append(a + "\n");
        }
    }
    */      
    
    
    
    private void addAllNotes() {
        
        String txtNotes = "";
        
        for (Note n : CWallNotes.getAllNotes()) {
            txtNotes += n.getNote() + "\n";
        }
        
        txtDisplayNotes.setText(txtNotes);

        
    }
    
    private void addCourse(String text) {
        CWallNotes.addNote(CWallNotes.getMaxID(), crse, text);
        addAllNotes();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ("Return2Notes".equals(ae.getActionCommand())) {
            
            System.gc();
            java.awt.Window win[] = java.awt.Window.getWindows();
            for (int i =0;i<win.length;i++) {
                win[i].dispose();
                win[i]=null;
            }
            coursework cw = new coursework();
        }
        if ("NewNote".equals(ae.getActionCommand())) {
            /*
            String userInput = JOptionPane.showInputDialog(null, "Please enter a suitable course name");
            if (userInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Input");
                txtDisplayNotes.setText("");      
            } 
            
            course.add(userInput);
            txtDisplayNotes.setText("");
            //addCourse();
            }
            */
            if (txtNewNote.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Cannot input empty text.",
                        "Empty String",
                        JOptionPane.WARNING_MESSAGE);
                txtNewNote.setText("");
            } else {
            addCourse(txtNewNote.getText());
            txtNewNote.setText(""); }   
        }
        
        
            
        
        if ("Close".equals(ae.getActionCommand())) {
            txtShowText.setText("");
        }
        if ("Exit".equals(ae.getActionCommand())) {
            System.exit(0);
        }
        if ("Course".equals(ae.getActionCommand())) {
            crse = courseList.getSelectedItem().toString();
            System.out.println(crse + "\n");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped not coded yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed not coded yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased not coded yet.");
    }
}
