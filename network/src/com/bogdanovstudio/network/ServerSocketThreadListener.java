package com.bogdanovstudio.network;

import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketThreadListener {
    void onStartServerThread(ServerSocketThread thread);

    void onCreateServerSocket(ServerSocketThread thread, ServerSocket server);

    void onAcceptSocket(ServerSocketThread thread, Socket socket);

    void onExceptionAcceptTimeout(ServerSocketThread thread, ServerSocket server);

    void onExceptionServerThread(ServerSocketThread thread, Exception e);

    void onStopServerThread(ServerSocketThread thread);
}