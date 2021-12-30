package edu.touro.mco152.bm.ui;

import edu.touro.mco152.bm.App;
import edu.touro.mco152.bm.Util;
import edu.touro.mco152.bm.persist.DiskRun;
import edu.touro.mco152.bm.persist.DiskRun.BlockSequence;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.touro.mco152.bm.App.dataDir;
import static java.awt.Font.PLAIN;

/**
 *
 */
@SuppressWarnings("rawtypes")
public final class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    DecimalFormat df = new DecimalFormat("###.###");
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JCheckBoxMenuItem autoRemoveCheckBoxMenuItem;
    private JCheckBoxMenuItem autoResetCheckBoxMenuItem;
    private JComboBox blockSizeCombo;
    private JButton chooseButton;
    private JMenuItem clearLogsItem;
    private JMenuItem clearRunsItem;
    private JPanel controlsPanel;
    private JMenuItem deleteDataMenuItem;
    private JScrollPane eventScrollPane;
    private JMenu fileMenu;
    private JLabel fileSizeLabel;
    private JMenu helpMenu;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JPanel jPanel2;
    private JPopupMenu.Separator jSeparator1;
    private JPanel locationPanel;
    private JTextField locationText;
    private JComboBox modeCombo;
    private JPanel mountPanel;
    private JTextArea msgTextArea;
    private JCheckBoxMenuItem multiFileCheckBoxMenuItem;
    private JComboBox numBlocksCombo;
    private JComboBox numFilesCombo;
    private JButton openLocButton;
    private JMenu optionMenu;
    private JComboBox<BlockSequence> orderComboBox;
    private JPanel progressPanel;
    private JLabel rAvgLabel;
    private JLabel rMaxLabel;
    private JLabel rMinLabel;
    private JButton resetButton;
    private JMenuItem resetSequenceMenuItem;
    private RunPanel runPanel;
    private JCheckBoxMenuItem showMaxMinCheckBoxMenuItem;
    private JButton startButton;
    private JTabbedPane tabbedPane;
    private JProgressBar totalTxProgBar;
    private JLabel wAvgLabel;
    private JLabel wMaxLabel;
    private JLabel wMinLabel;
    private JCheckBoxMenuItem writeSyncCheckBoxMenuItem;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        ChartPanel chartPanel = Gui.createChartPanel();
        mountPanel.setLayout(new BorderLayout());
        chartPanel.setSize(mountPanel.getSize());
        chartPanel.setSize(mountPanel.getWidth(), 200);
        mountPanel.add(chartPanel);
        totalTxProgBar.setStringPainted(true);
        totalTxProgBar.setValue(0);
        totalTxProgBar.setString("");
        setTitle(getTitle() + " " + App.getVersion());

        // auto scroll the text area.
        DefaultCaret caret = (DefaultCaret) msgTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // init order combo box
        orderComboBox.addItem(BlockSequence.SEQUENTIAL);
        orderComboBox.addItem(BlockSequence.RANDOM);
    }

    public JPanel getMountPanel() {
        return mountPanel;
    }

    /**
     * This method is called when the gui needs to be updated after a new config
     * has been loaded.
     */
    public void refreshConfig() {
        if (App.locationDir != null) { // set the location dir if not null
            setLocation(App.locationDir.getAbsolutePath());
        }
        multiFileCheckBoxMenuItem.setSelected(App.multiFile);
        autoRemoveCheckBoxMenuItem.setSelected(App.autoRemoveData);
        autoResetCheckBoxMenuItem.setSelected(App.autoReset);
        showMaxMinCheckBoxMenuItem.setSelected(App.showMaxMin);
        writeSyncCheckBoxMenuItem.setSelected(App.writeSyncEnable);

        String modeStr = "unset";
        if (!App.readTest && App.writeTest) {
            modeStr = "write";
        } else if (App.readTest && !App.writeTest) {
            modeStr = "read";
        } else if (App.readTest && App.writeTest) {
            modeStr = "write&read";
        } else {
            msg("WARNING: invalid mode detected");
        }
        modeCombo.setSelectedItem(modeStr);

        //String blockOrderStr = App.randomEnable ? "random":"sequential";
        orderComboBox.setSelectedItem(App.blockSequence);

        numFilesCombo.setSelectedItem(String.valueOf(App.numOfMarks));
        numBlocksCombo.setSelectedItem(String.valueOf(App.numOfBlocks));
        blockSizeCombo.setSelectedItem(String.valueOf(App.blockSizeKb));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new JButton();
        jPanel2 = new JPanel();
        mountPanel = new JPanel();
        controlsPanel = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        numBlocksCombo = new JComboBox();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        blockSizeCombo = new JComboBox();
        startButton = new JButton();
        jLabel8 = new JLabel();
        numFilesCombo = new JComboBox();
        jLabel4 = new JLabel();
        modeCombo = new JComboBox();
        jLabel9 = new JLabel();
        fileSizeLabel = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        resetButton = new JButton();
        jLabel13 = new JLabel();
        orderComboBox = new JComboBox<BlockSequence>();
        jLabel14 = new JLabel();
        wMinLabel = new JLabel();
        wMaxLabel = new JLabel();
        wAvgLabel = new JLabel();
        rMinLabel = new JLabel();
        rMaxLabel = new JLabel();
        rAvgLabel = new JLabel();
        tabbedPane = new JTabbedPane();
        runPanel = new RunPanel();
        eventScrollPane = new JScrollPane();
        msgTextArea = new JTextArea();
        locationPanel = new JPanel();
        chooseButton = new JButton();
        locationText = new JTextField();
        openLocButton = new JButton();
        jLabel15 = new JLabel();
        progressPanel = new JPanel();
        jLabel7 = new JLabel();
        totalTxProgBar = new JProgressBar();
        jMenuBar1 = new JMenuBar();
        fileMenu = new JMenu();
        jMenuItem1 = new JMenuItem();
        optionMenu = new JMenu();
        clearRunsItem = new JMenuItem();
        clearLogsItem = new JMenuItem();
        deleteDataMenuItem = new JMenuItem();
        resetSequenceMenuItem = new JMenuItem();
        jSeparator1 = new JPopupMenu.Separator();
        multiFileCheckBoxMenuItem = new JCheckBoxMenuItem();
        autoRemoveCheckBoxMenuItem = new JCheckBoxMenuItem();
        autoResetCheckBoxMenuItem = new JCheckBoxMenuItem();
        showMaxMinCheckBoxMenuItem = new JCheckBoxMenuItem();
        writeSyncCheckBoxMenuItem = new JCheckBoxMenuItem();
        helpMenu = new JMenu();
        jMenuItem2 = new JMenuItem();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("jDiskMark");

        mountPanel.setBackground(new Color(0, 51, 153));
        mountPanel.setMaximumSize(new Dimension(503, 200));

        GroupLayout mountPanelLayout = new GroupLayout(mountPanel);
        mountPanel.setLayout(mountPanelLayout);
        mountPanelLayout.setHorizontalGroup(
                mountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        mountPanelLayout.setVerticalGroup(
                mountPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        controlsPanel.setPreferredSize(new Dimension(250, 420));

        jLabel1.setText("Write Min");

        jLabel2.setText("Write Max");

        jLabel3.setText("Write Avg");

        numBlocksCombo.setEditable(true);
        numBlocksCombo.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048", "4096", "8192"}));
        numBlocksCombo.setSelectedIndex(6);
        numBlocksCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numBlocksComboActionPerformed(evt);
            }
        });

        jLabel5.setText("No. Blocks");

        jLabel6.setText("Block (KB)");

        blockSizeCombo.setEditable(true);
        blockSizeCombo.setModel(new DefaultComboBoxModel(new String[]{"2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048"}));
        blockSizeCombo.setSelectedIndex(8);
        blockSizeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockSizeComboActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("No. Marks");

        numFilesCombo.setEditable(true);
        numFilesCombo.setModel(new DefaultComboBoxModel(new String[]{"25", "50", "75", "100", "150", "200", "250"}));
        numFilesCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numFilesComboActionPerformed(evt);
            }
        });

        jLabel4.setText("IO Mode");

        modeCombo.setModel(new DefaultComboBoxModel(new String[]{"write", "read", "write&read"}));
        modeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeComboActionPerformed(evt);
            }
        });

        jLabel9.setText("Mark Size (KB)");

        fileSizeLabel.setText("- -");

        jLabel10.setText("Read Min");

        jLabel11.setText("Read Max");

        jLabel12.setText("Read Avg");

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel13.setText("Tx Rates (MB/s)");

        orderComboBox.setMaximumSize(new Dimension(106, 32767));
        orderComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setText("Block Order");

        wMinLabel.setText("- -");

        wMaxLabel.setText("- -");

        wAvgLabel.setText("- -");

        rMinLabel.setText("- -");

        rMaxLabel.setText("- -");

        rAvgLabel.setText("- -");

        GroupLayout controlsPanelLayout = new GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
                controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(controlsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup()
                                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel10)
                                                        .addComponent(jLabel11)
                                                        .addComponent(jLabel12))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(rMaxLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                                        .addComponent(rMinLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wAvgLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wMaxLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wMinLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(rAvgLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(20, 20, 20))
                                        .addComponent(jLabel13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, controlsPanelLayout.createSequentialGroup()
                                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel14)
                                                        .addComponent(resetButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(fileSizeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(controlsPanelLayout.createSequentialGroup()
                                                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(blockSizeCombo, GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                                                        .addComponent(numBlocksCombo, GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                                                        .addComponent(numFilesCombo, GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                                                        .addComponent(orderComboBox, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(modeCombo, GroupLayout.Alignment.LEADING, 0, 94, Short.MAX_VALUE)
                                                                        .addComponent(startButton, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                                                                .addContainerGap())))))
        );
        controlsPanelLayout.setVerticalGroup(
                controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(controlsPanelLayout.createSequentialGroup()
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(startButton)
                                        .addComponent(resetButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(modeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(orderComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(numFilesCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(numBlocksCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(blockSizeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(fileSizeLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(wMinLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(wMaxLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(wAvgLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(rMinLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(rMaxLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(rAvgLabel))
                                .addGap(70, 70, 70))
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(mountPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(mountPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(controlsPanel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 6, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Previous Runs", runPanel);

        msgTextArea.setEditable(false);
        msgTextArea.setColumns(20);
        msgTextArea.setFont(new Font("Monospaced", PLAIN, 11)); // NOI18N
        msgTextArea.setRows(5);
        msgTextArea.setTabSize(4);
        eventScrollPane.setViewportView(msgTextArea);

        tabbedPane.addTab("Event Logs", eventScrollPane);

        chooseButton.setText("Browse");
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        locationText.setEditable(false);

        openLocButton.setText("Open");
        openLocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openLocButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("/jDiskMarkData");

        GroupLayout locationPanelLayout = new GroupLayout(locationPanel);
        locationPanel.setLayout(locationPanelLayout);
        locationPanelLayout.setHorizontalGroup(
                locationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(locationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(locationText, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(chooseButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(openLocButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        locationPanelLayout.setVerticalGroup(
                locationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(locationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(locationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(locationText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chooseButton)
                                        .addComponent(openLocButton)
                                        .addComponent(jLabel15))
                                .addContainerGap(140, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Data Location", locationPanel);

        jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
        jLabel7.setText("Total Tx (KB)");

        GroupLayout progressPanelLayout = new GroupLayout(progressPanel);
        progressPanel.setLayout(progressPanelLayout);
        progressPanelLayout.setHorizontalGroup(
                progressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, progressPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalTxProgBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        progressPanelLayout.setVerticalGroup(
                progressPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, progressPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalTxProgBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addComponent(jLabel7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        fileMenu.setText("File");

        jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        jMenuBar1.add(fileMenu);

        optionMenu.setText("Options");

        clearRunsItem.setText("Clear Previous Runs");
        clearRunsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearRunsItemActionPerformed(evt);
            }
        });
        optionMenu.add(clearRunsItem);

        clearLogsItem.setText("Clear Event Logs");
        clearLogsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearLogsItemActionPerformed(evt);
            }
        });
        optionMenu.add(clearLogsItem);

        deleteDataMenuItem.setText("Delete Data Dir");
        deleteDataMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDataMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(deleteDataMenuItem);

        resetSequenceMenuItem.setText("Reset Sequence");
        resetSequenceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSequenceMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(resetSequenceMenuItem);
        optionMenu.add(jSeparator1);

        multiFileCheckBoxMenuItem.setSelected(true);
        multiFileCheckBoxMenuItem.setText("Multi Data File");
        multiFileCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiFileCheckBoxMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(multiFileCheckBoxMenuItem);

        autoRemoveCheckBoxMenuItem.setSelected(true);
        autoRemoveCheckBoxMenuItem.setText("Auto Remove Data Dir");
        autoRemoveCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoRemoveCheckBoxMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(autoRemoveCheckBoxMenuItem);

        autoResetCheckBoxMenuItem.setSelected(true);
        autoResetCheckBoxMenuItem.setText("Auto Reset");
        autoResetCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoResetCheckBoxMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(autoResetCheckBoxMenuItem);

        showMaxMinCheckBoxMenuItem.setSelected(true);
        showMaxMinCheckBoxMenuItem.setText("Show Max Min");
        showMaxMinCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMaxMinCheckBoxMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(showMaxMinCheckBoxMenuItem);

        writeSyncCheckBoxMenuItem.setSelected(true);
        writeSyncCheckBoxMenuItem.setText("Write Sync");
        writeSyncCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeSyncCheckBoxMenuItemActionPerformed(evt);
            }
        });
        optionMenu.add(writeSyncCheckBoxMenuItem);

        jMenuBar1.add(optionMenu);

        helpMenu.setText("Help");

        jMenuItem2.setText("About...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem2);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(progressPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabbedPane)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(progressPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        if (App.locationDir != null && App.locationDir.exists()) {
            Gui.selFrame.setInitDir(App.locationDir);
        }
        Gui.selFrame.setVisible(true);
    }//GEN-LAST:event_chooseButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        if (App.state == App.State.DISK_TEST_STATE) {
            App.cancelBenchmark();
        } else if (App.state == App.State.IDLE_STATE) {
            applyTestParams();
            App.startBenchmark();
        }

    }//GEN-LAST:event_startButtonActionPerformed

    private void blockSizeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockSizeComboActionPerformed
        App.blockSizeKb = Integer.valueOf((String) blockSizeCombo.getSelectedItem());
        fileSizeLabel.setText(String.valueOf(App.targetMarkSizeKb()));
        totalTxProgBar.setString(String.valueOf(App.targetTxSizeKb()));
    }//GEN-LAST:event_blockSizeComboActionPerformed

    private void numBlocksComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numBlocksComboActionPerformed
        App.numOfBlocks = Integer.valueOf((String) numBlocksCombo.getSelectedItem());
        fileSizeLabel.setText(String.valueOf(App.targetMarkSizeKb()));
        totalTxProgBar.setString(String.valueOf(App.targetTxSizeKb()));
    }//GEN-LAST:event_numBlocksComboActionPerformed

    private void numFilesComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numFilesComboActionPerformed
        App.numOfMarks = Integer.valueOf((String) numFilesCombo.getSelectedItem());
        fileSizeLabel.setText(String.valueOf(App.targetMarkSizeKb()));
        totalTxProgBar.setString(String.valueOf(App.targetTxSizeKb()));
    }//GEN-LAST:event_numFilesComboActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        App.resetTestData();
        Gui.resetTestData();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void modeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeComboActionPerformed
        String modeStr = (String) modeCombo.getSelectedItem();
        App.readTest = modeStr.contains("read");
        App.writeTest = modeStr.contains("write");
    }//GEN-LAST:event_modeComboActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(Gui.mainFrame,
                "jDiskMark " + App.getVersion(), "About...", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void openLocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openLocButtonActionPerformed
        try {
            Desktop.getDesktop().open(App.locationDir);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_openLocButtonActionPerformed

    private void clearLogsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearLogsItemActionPerformed
        clearMessages();
    }//GEN-LAST:event_clearLogsItemActionPerformed

    private void multiFileCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiFileCheckBoxMenuItemActionPerformed
        App.multiFile = multiFileCheckBoxMenuItem.getState();
        App.saveConfig();
    }//GEN-LAST:event_multiFileCheckBoxMenuItemActionPerformed

    private void autoRemoveCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoRemoveCheckBoxMenuItemActionPerformed
        App.autoRemoveData = autoRemoveCheckBoxMenuItem.getState();
        App.saveConfig();
    }//GEN-LAST:event_autoRemoveCheckBoxMenuItemActionPerformed

    private void deleteDataMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDataMenuItemActionPerformed
        Util.deleteDirectory(dataDir);
    }//GEN-LAST:event_deleteDataMenuItemActionPerformed

    private void autoResetCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoResetCheckBoxMenuItemActionPerformed
        App.autoReset = autoResetCheckBoxMenuItem.getState();
        App.saveConfig();
    }//GEN-LAST:event_autoResetCheckBoxMenuItemActionPerformed

    private void resetSequenceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSequenceMenuItemActionPerformed
        App.resetSequence();
    }//GEN-LAST:event_resetSequenceMenuItemActionPerformed

    private void showMaxMinCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMaxMinCheckBoxMenuItemActionPerformed
        App.showMaxMin = showMaxMinCheckBoxMenuItem.getState();
    }//GEN-LAST:event_showMaxMinCheckBoxMenuItemActionPerformed

    private void orderComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderComboBoxActionPerformed
        App.blockSequence = (BlockSequence) orderComboBox.getSelectedItem();
    }//GEN-LAST:event_orderComboBoxActionPerformed

    private void writeSyncCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeSyncCheckBoxMenuItemActionPerformed
        App.writeSyncEnable = writeSyncCheckBoxMenuItem.getState();
        App.saveConfig();
    }//GEN-LAST:event_writeSyncCheckBoxMenuItemActionPerformed

    private void clearRunsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearRunsItemActionPerformed
        App.msg("Clearing previous runs.");
        App.clearSavedRuns();
    }//GEN-LAST:event_clearRunsItemActionPerformed
    // End of variables declaration//GEN-END:variables

    public void setLocation(String path) {
        locationText.setText(path);
    }

    public void msg(String message) {
        msgTextArea.append(message + '\n');
    }

    public void applyTestParams() {
        String modeStr = (String) modeCombo.getSelectedItem();
        App.readTest = modeStr.contains("read");
        App.writeTest = modeStr.contains("write");
        App.blockSequence = (BlockSequence) orderComboBox.getSelectedItem();
        App.numOfMarks = Integer.valueOf((String) numFilesCombo.getSelectedItem());
        App.numOfBlocks = Integer.valueOf((String) numBlocksCombo.getSelectedItem());
        App.blockSizeKb = Integer.valueOf((String) blockSizeCombo.getSelectedItem());
        fileSizeLabel.setText(String.valueOf(App.targetMarkSizeKb()));
        totalTxProgBar.setString(String.valueOf(App.targetTxSizeKb()));
    }

    public void refreshWriteMetrics() {
        String value;
        value = App.wMin == -1 ? "- -" : df.format(App.wMin);
        wMinLabel.setText(value);
        value = App.wMax == -1 ? "- -" : df.format(App.wMax);
        wMaxLabel.setText(value);
        value = App.wAvg == -1 ? "- -" : df.format(App.wAvg);
        wAvgLabel.setText(value);
    }

    public void refreshReadMetrics() {
        String value;
        value = App.rMin == -1 ? "- -" : df.format(App.rMin);
        rMinLabel.setText(value);
        value = App.rMax == -1 ? "- -" : df.format(App.rMax);
        rMaxLabel.setText(value);
        value = App.rAvg == -1 ? "- -" : df.format(App.rAvg);
        rAvgLabel.setText(value);
    }

    public JProgressBar getProgressBar() {
        return totalTxProgBar;
    }

    public void clearMessages() {
        msgTextArea.setText("");
    }

    public void adjustSensitivity() {
        if (App.state == App.State.DISK_TEST_STATE) {
            startButton.setText("Cancel");
            orderComboBox.setEnabled(false);
            blockSizeCombo.setEnabled(false);
            numBlocksCombo.setEnabled(false);
            numFilesCombo.setEnabled(false);
            modeCombo.setEnabled(false);
            resetButton.setEnabled(false);
        } else if (App.state == App.State.IDLE_STATE) {
            startButton.setText("Start");
            orderComboBox.setEnabled(true);
            blockSizeCombo.setEnabled(true);
            numBlocksCombo.setEnabled(true);
            numFilesCombo.setEnabled(true);
            modeCombo.setEnabled(true);
            resetButton.setEnabled(true);
        }
    }

}
