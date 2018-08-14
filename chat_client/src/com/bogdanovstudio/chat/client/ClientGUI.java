package com.bogdanovstudio.chat.client;

import com.bogdanovstudio.network.SocketThread;
import com.bogdanovstudio.network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT,
            new Locale("ru", "RU"));
    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("Антон Богданов");
    private final JPasswordField tfPassword = new JPasswordField("12321");
    private final JButton btnLogin = new JButton("Login");
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("Disconnect");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");
    private final JList<String> userList = new JList<>();
    private boolean IOErrorShown;
    private SocketThread socketThread;

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        log.setEditable(false);
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        String[] users = {"user1_with_an_exceptionally_long_nickname", "user2", "user3", "user4", "user5",
                "user6", "user7", "user8", "user9", "user10"};
        userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100, 0));

        cbAlwaysOnTop.addActionListener(this);
        btnLogin.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(scrollUsers, BorderLayout.EAST);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientGUI());
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0)
            msg = "Empty StackTrace";
        else {
            msg = e.getClass().getCanonicalName() + ": " + e.getMessage() +
                    "\n\t at " + ste[0];
        }
        JOptionPane.showMessageDialog(null, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnLogin || src == tfIPAddress || src == tfLogin || src == tfPassword || src == tfPort) {
            connect();
        } else if (src == btnSend || src == tfMessage) {
            tfMessage.requestFocus();
            if ("".equals(tfMessage.getText())) return;
            sendMessage();
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    private void connect() {
        Socket socket = null;
        try {
            socket = new Socket(tfIPAddress.getText(), Integer.parseInt(tfPort.getText()));
        } catch (IOException e) {
            log.append("Exception: " + e.getMessage());
        }
        socketThread = new SocketThread(this, socket, "Client thread");
    }

    private void sendMessage() {
        String user = tfLogin.getText();
        String time = dateFormat.format(new Date());
        String msg = tfMessage.getText();
        tfMessage.setText("");
        String message = String.format("%s (%s): %s\n", user, time, msg);
        putLog(message);
//        writeLogFile(message);
        socketThread.sendMessage(message);
    }

    private void putLog(String msg) {
        SwingUtilities.invokeLater(() -> {
            log.append(msg);
            log.setCaretPosition(log.getDocument().getLength());
        });
    }

    private void writeLogFile(String msg) {
        try (FileWriter fileWriter = new FileWriter("log.txt", true)) {
            fileWriter.write(new Date() + " - " + msg);
            fileWriter.flush();
        } catch (IOException e) {
            if (!IOErrorShown) {
                IOErrorShown = true;
                JOptionPane.showMessageDialog(null, "Log file write error",
                        "Exception", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Socket thread events
     */

    @Override
    public void onStartSocketThread(SocketThread thread, Socket socket) {
        putLog("Socket thread start\n");
    }

    @Override
    public void onStopSocketThread(SocketThread thread) {
        putLog("Socket thread stop\n");
    }

    @Override
    public void onReceiveMessage(SocketThread thread, Socket socket, String message) {
        putLog(message);
    }

    @Override
    public void onSocketThreadIsReady(SocketThread thread, Socket socket) {
        putLog("Socket thread is ready\n");
    }

    @Override
    public void onSocketThreadException(SocketThread thread, Exception e) {
        putLog("Socket thread exception\n");
    }

}