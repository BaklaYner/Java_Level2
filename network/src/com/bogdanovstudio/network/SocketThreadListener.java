package com.bogdanovstudio.network;

import java.net.Socket;

public interface SocketThreadListener {
    void onStartSocketThread(SocketThread thread, Socket socket);
    void onStopSocketThread(SocketThread thread);

    void onReceiveMessage(SocketThread thread, Socket socket, String message);
    void onSocketThreadIsReady(SocketThread thread, Socket socket);

    void onSocketThreadException(SocketThread thread, Exception e);
}