package fortest.hlwu.com.fortest.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by hlwu on 4/4/18.
 */

public class TCPServerService extends Service {

    private boolean mServiceDestroyed = false;
    private static String[] DEFAULT_MESSAGES = new String[] {
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbbbbbbbbbbb",
            "cccccccccccccccccccccccccccccc", "dddddddddddddd"
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mServiceDestroyed = true;
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        new Thread(new TCPServer()).start();
        super.onCreate();
    }

    private class TCPServer implements Runnable {
        @Override
        public void run() {
            ServerSocket socket = null;
            try {
                socket = new ServerSocket(6842);
            } catch (IOException e) {
                Log.d("flaggg", "new socket failed : " + e);
                e.printStackTrace();
            }

            while (!mServiceDestroyed) {
                try {
                    final Socket client = socket.accept();
                    Log.d("flaggg", "server accepted client: " + client);
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
        toClient.println("welcome to server");
        while (!mServiceDestroyed) {
            String str = fromClient.readLine();
            Log.d("flaggg", "message from client: " + str);
            if (str == null) {
                //client disconnected
                break;
            }
            int i = new Random().nextInt(DEFAULT_MESSAGES.length);
            String msg = DEFAULT_MESSAGES[i];
            toClient.println(msg);
            Log.d("flaggg", "server send msg: " + msg);
        }
        Log.d("flaggg", "client disconnected.");
        fromClient.close();
        toClient.close();
        client.close();
    }
}
