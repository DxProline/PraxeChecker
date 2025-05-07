package cz.ibmd.checker;

import cz.ibmd.checker.data.ConnectionList;
import cz.ibmd.checker.data.ProcessList;
import cz.ibmd.checker.data.Result;
import cz.ibmd.checker.loaders.ConnectionListLoader;
import cz.ibmd.checker.loaders.ProcessListLoader;
import cz.ibmd.checker.processing.Checker;
import cz.ibmd.checker.processing.Exporter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.IOException;


/*
Spouštění programu v GUI (Grafické uživatelské rozhraní)
 */
public class GUI extends JFrame {
    JButton buttonConnection, buttonProcess, buttonStartCompare, buttonExport;

    private JLabel labelresult;
    private JLabel labelConnection;
    private JLabel labelProcess;

    private Result result;
    GridBagConstraints g;

    public GUI(String title) throws HeadlessException {
        super(title);
        setSize(600,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());

        g = new GridBagConstraints();
        buttonConnection = new JButton("Vlož ConnectionList pro kontrolu");
        buttonProcess = new JButton("Vlož ProcessList pro kontrolu");
        buttonStartCompare = new JButton("Začni Porovnávat");
        buttonExport = new JButton("Začni exportovat");


        labelresult = new JLabel();
        labelresult.setText(labelresult.getText()+"Result =");
        labelConnection = new JLabel();
        labelConnection.setText(labelConnection.getText()+" ");
        labelProcess = new JLabel();
        labelProcess.setText(labelProcess.getText()+" ");
        //Nastavení Ikony pro okno
        try {
            //Vytvoření ikony pro aplikaci
            this.setIconImage(ImageIO.read( ClassLoader.getSystemResource( "ConnectionCheckerLogo.png" ) ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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


        g.gridx = 1;
        g.gridy = 6;
        g.fill = 1;
        g.gridwidth = 1;
        pane.add(buttonExport,g);


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


        labelresult.setText("Výsledek =");

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
                    // Label connection ukazuje cestu kde se soubor nachází
                    connectionList = connectionListLoader.load(labelConnection.getText());

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(pane, " Nepodařilo se nalézt nebo otevřít soubor ConnectionList");
                    return;
                    //Zachytí všechny zbývající druhy chyb kromě IO = Input output
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pane, " Došlo k chybě při načítání Connection Listu na řádku: " + connectionListLoader.getLineCounter());
                    return;
                }

                ProcessList processList = null;
                try {
                    processList = processListLoader.load(labelProcess.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(pane, "Nepodařilo se nalézt nebo otevřít soubor Process List");
                    return;
                    //Zachytí všechny zbývající druhy chyb kromě IO = Input output
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pane, "Došlo k chybě při načítání Process Listu na řádku: " + processListLoader.getLineCounter());
                    return;
                }

                //Zachytí výsledek checkeru
                result = checker.check(processList, connectionList);
                // Size vrací počet prvků v seznamu
                labelresult.setText("Výsledek: " + result.getMissingServers().size() + " Chybějící,  " + result.getRemovedServers().size() + " Odstraněné");
            }

        });

        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (result == null ){
                    JOptionPane.showMessageDialog(pane, "Nejprve klikněte na porovnávat");
                    return;
                }
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // Zpřístupní uživateli vybrat Soubor
                int r = j.showSaveDialog(null);
                // Pokud uživatel vybere soubor
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Vybere Soubor ProcessList který zachytí do paměti

                    try {
                        //absolutní cesta kam se exportuje daný soubor
                        String filename = j.getSelectedFile().getAbsolutePath();
                        Exporter exporter = new Exporter();
                        exporter.export(result, filename);
                        // Dialog k oznámení chyby či výsledku
                        JOptionPane.showMessageDialog(pane, " Soubor byl úspěšně vyexportován");
                    } catch (IOException ex) {
                        // Dialog k oznámení chyby či výsledku
                        JOptionPane.showMessageDialog(pane, " Došlo k chybě při exportu");
                        return;

                    }
                }
            }

        });

    }
    public static void main(String[] args) {
        new GUI("ConnectionChecker");
    }
}

