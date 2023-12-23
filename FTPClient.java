import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class One extends JFrame implements ActionListener {
    /* ctrl space */
    public JButton b, b1;
    public JLabel l;
    public JLabel l1, lmsg1, lmsg2;

    One() {
        b = new JButton("Upload");
        l = new JLabel("Uplaod a file : ");
        lmsg1 = new JLabel("");
        b1 = new JButton("Download");
        l1 = new JLabel("Downlaod a file");
        lmsg2 = new JLabel("");
        setLayout(new GridLayout(2, 3, 10, 10));
        add(l);
        add(b);
        add(lmsg1);
        add(l1);
        add(b1);
        add(lmsg2);
        b.addActionListener(this);
        b1.addActionListener(this);
        setVisible(true);
        setSize(600, 500);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try {
            /*
             * String s=e.getActionCommand();
             * if(s.equals("Upload"))
             */
            if (b.getModel().isArmed()) {
                Socket s = new Socket("localhost", 1010);
                System.out.println("Client connected to server");
                JFileChooser j = new JFileChooser();
                int val;
                val = j.showOpenDialog(One.this);
                String filename = j.getSelectedFile().getName();
                String path = j.getSelectedFile().getPath();
                PrintStream out = new PrintStream(s.getOutputStream());
                out.println("Upload");
                out.println(filename);
                FileInputStream fis = new FileInputStream(path);
                int n = fis.read();
                while (n != -1) {
                    out.print((char) n);
                    n = fis.read();
                }
                fis.close();
                out.close();
                lmsg1.setText(filename + "is uploaded");
                // s.close();
                repaint();
            }
            if (b1.getModel().isArmed()) {
                Socket s = new Socket("localhost", 1010);
                System.out.println("Client connected to server");
                String remoteadd = s.getRemoteSocketAddress().toString();
                System.out.println(remoteadd);
                JFileChooser j1 = new JFileChooser(remoteadd);
                int val;
                val = j1.showOpenDialog(One.this);
                String filename = j1.getSelectedFile().getName();
                String filepath = j1.getSelectedFile().getPath();
                System.out.println("File name:" + filename);
                PrintStream out = new PrintStream(s.getOutputStream());
                out.println("Download");
                out.println(filepath);
                FileOutputStream fout = new FileOutputStream(filename);
                DataInputStream fromserver = new DataInputStream(s.getInputStream());
                int ch;
                while ((ch = fromserver.read()) != -1) {
                    fout.write((char) ch);
                }
                fout.close();// s.close();
                lmsg2.setText(filename + "is downlaoded");
                repaint();
            }
        } catch (Exception ee) {
            // TODO: handle exception
            System.out.println(ee);
        }
    }
}

public class FTPClient {
    public static void main(String[] args) {
        new One();
    }
}