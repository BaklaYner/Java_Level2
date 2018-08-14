package com.bogdanovstudio.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketThread extends Thread {
    private final int port;
    private final int timeout;
    private ServerSocketThreadListener listener;

    public ServerSocketThread(ServerSocketThreadListener listener, String name, int port, int timeout) {
        super(name);
        this.listener = listener;
        this.port = port;
        this.timeout = timeout;
        start();
    }

    @Override
    public void run() {
        listener.onStartServerThread(this);
        try (ServerSocket server = new ServerSocket(port)) {
            listener.onCreateServerSocket(this, server);
            server.setSoTimeout(timeout);
            while (!isInterrupted()) {
                Socket socket;
                try {
                    socket = server.accept();
                } catch (SocketTimeoutException e) {
                    listener.onExceptionAcceptTimeout(this, server);
                    continue;
                }
                listener.onAcceptSocket(this, socket);
            }
        } catch (IOException e) {
            listener.onExceptionServerThread(this, e);
        } finally {
            listener.onStopServerThread(this);
        }
    }

}