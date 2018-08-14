package com.bogdanovstudio.chat.server.core;

import com.bogdanovstudio.network.ServerSocketThread;
import com.bogdanovstudio.network.ServerSocketThreadListener;
import com.bogdanovstudio.network.SocketThread;
import com.bogdanovstudio.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {
    private final int timeout = 3000;
    private ServerSocketThread server;
    private Vector<SocketThread> userList = new Vector<>();
    private final DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT,
            new Locale("ru", "RU"));

    public void start(int port) {
        if (server != null && server.isAlive()) {
            putLog("Server is already running");
        } else {
            server = new ServerSocketThread(this, "Chat server", port, timeout);
        }
    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            putLog("Server is already stopped or not running");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        msg = dateFormat.format(new Date()) + " - " + Thread.currentThread().getName() + ": " + msg;
        System.out.println(msg);
    }

    /**
     * Server socket thread events
     */

    @Override
    public void onStartServerThread(ServerSocketThread thread) {
        putLog("Server socked thread started");
    }

    @Override
    public void onCreateServerSocket(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket created");
    }

    @Override
    public void onAcceptSocket(ServerSocketThread thread, Socket socket) {
        putLog("Socket accepted");
        String name = "SocketThread " + socket.getInetAddress() + ": " + socket.getPort();
        new SocketThread(this, socket, name);
    }

    @Override
    public void onExceptionAcceptTimeout(ServerSocketThread thread, ServerSocket server) {
        putLog("Accept timeout");
    }

    @Override
    public void onExceptionServerThread(ServerSocketThread thread, Exception e) {
        putLog("Server thread exception");
    }

    @Override
    public void onStopServerThread(ServerSocketThread thread) {
        putLog("Server socket thread stopped");
    }

    /**
     * Socket thread events
     */

    @Override
    public synchronized void onStartSocketThread(SocketThread thread, Socket socket) {
        putLog("Start socket thread");
    }

    @Override
    public synchronized void onStopSocketThread(SocketThread thread) {
        putLog("Stop socket thread");
    }

    @Override
    public synchronized void onReceiveMessage(SocketThread thread, Socket socket, String message) {
        for (SocketThread socketThread : userList) {
            socketThread.sendMessage(message);
        }
    }

    @Override
    public synchronized void onSocketThreadIsReady(SocketThread thread, Socket socket) {
        userList.add(thread);
        putLog("Socket thread is ready");
    }

    @Override
    public synchronized void onSocketThreadException(SocketThread thread, Exception e) {
        userList.remove(thread);
        putLog("Socket thread exception");
    }

}