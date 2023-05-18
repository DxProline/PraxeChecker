package cz.tmobile.ibmd.checker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;


public class GUI extends JFrame {
    JButton buttonConnection, buttonProcess, buttonStartCompare, buttonExport;

    private JLabel labelresult;
    private JLabel labelConnection;
    private JLabel labelProcess;

    private  JLabel labelExport;

    String oldvalue = "0";
    String operation;
    GridBagConstraints g;
    JFileChooser jFileChooserProcess = new JFileChooser();
    JFileChooser jFileChooserConnection = new JFileChooser();

    public GUI(String title) throws HeadlessException {
        super(title);
        setSize(800,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());

        g = new GridBagConstraints();
        buttonConnection = new JButton("Enter ConnectionList to Compare");
        buttonProcess = new JButton("Enter ProcessList to Compare");
        buttonStartCompare = new JButton("Start Compare");


        labelresult = new JLabel();
        labelresult.setText(labelresult.getText()+"Result =");
        labelConnection = new JLabel();
        labelConnection.setText(labelConnection.getText()+" ");
        labelProcess = new JLabel();
        labelProcess.setText(labelProcess.getText()+" ");



        //g.gridx = 2;
        //g.gridy = 1;
        //g.fill = 1;
        //g.gridwidth = 4;
        //pane.add(jFileChooserConnection,g);
        //g.gridx = 3;
        //g.gridy = 1;
        //g.fill = 1;
        //g.gridwidth = 3;
        //pane.add(jFileChooserProcess,g);

        g.gridx = 1;
        g.gridy = 1;
        g.fill = 1;
        g.gridwidth = 1;
        pane.add(buttonConnection,g);

        g.gridx = 2;
        g.gridy = 1;
        g.fill = 1;
        g.gridwidth = 1;
        pane.add(labelConnection,g);


        g.gridx = 2;
        g.gridy = 2;
        g.fill = 1;
        g.gridwidth = 1;
        pane.add(labelProcess,g);

        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth = 1;
        pane.add(buttonProcess,g);

        g.gridx = 2;
        g.gridy = 3;
        g.gridwidth = 1;
        pane.add(buttonStartCompare,g);

        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 1;
        pane.add(labelresult,g);


        labelresult.setText("Result =");

        setContentPane(pane);
        setVisible(true);





        buttonConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // Ukáže uložený Soubor
                int r = j.showOpenDialog(null);
                // Pokud uživatel vybere soubor
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Vybere Soubor ConnectionList který zachytí do paměti
                    labelConnection.setText(j.getSelectedFile().getAbsolutePath());
                }
            }

        });
        buttonProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // Ukáže uložený Soubor
                int r = j.showOpenDialog(null);
                // Pokud uživatel vybere soubor
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Vybere Soubor ProcessList který zachytí do paměti
                    labelProcess.setText(j.getSelectedFile().getAbsolutePath());
                }
            }

        });
        buttonStartCompare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ConnectionListLoader connectionListLoader = new ConnectionListLoader();
                ProcessListLoader processListLoader = new ProcessListLoader();
                Checker checker = new Checker();
                //Načte na soubor Connection List který dostane na vstup.

                ConnectionList connectionList = null;
                try {
                    connectionList = connectionListLoader.load(labelConnection.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(pane, " Došlo k chybě při načítání Connection Listu");
                    return;
                }

                ProcessList processList = null;
                try {
                    processList = processListLoader.load(labelProcess.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(pane, " Došlo k chybě při načítání Process Listu");
                    return;
                }

                //Zachytí výsledek checkeru
                Result result = checker.check(processList, connectionList);
                labelresult.setText("Výsledek: " + result.getMissingServers().size() + " Chybějící,  " + result.getRemovedServers().size() + " Odstraněné");
            }

        });

        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // Ukáže uložený Soubor
                int r = j.showOpenDialog(null);
                // Pokud uživatel vybere soubor
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Vybere Soubor ProcessList který zachytí do paměti
                    labelProcess.setText(j.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter myWriter = new FileWriter("filename.txt");
                        myWriter.write("Files in Java might be tricky, but it is fun enough!");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException ex) {
                        System.out.println("An error occurred.");
                        ex.printStackTrace();
                    }
                }
            }

        });

    }
    public static void main(String[] args) {
        new GUI("ConnectionChecker");
    }
}

