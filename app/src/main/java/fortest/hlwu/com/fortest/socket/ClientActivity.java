package fortest.hlwu.com.fortest.socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import fortest.hlwu.com.fortest.R;

/**
 * Created by hlwu on 4/4/18.
 */

public class ClientActivity extends Activity {
    private static final int MSG_RECEIVE_MSG = 1;
    private static final int MSG_CONNECTED = 2;
    private Button btn;
    private EditText et;
    private TextView tv;
    private PrintWriter pw;
    private Socket clientSocket;

    private Handler mH = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RECEIVE_MSG: {
                    tv.setText((String) msg.obj);
                    break;
                }
                case MSG_CONNECTED: {
                    btn.setEnabled(true);
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_socket_client);
        btn = (Button) findViewById(R.id.socket_button);
        et = (EditText) findViewById(R.id.socket_edittext);
        tv = (TextView) findViewById(R.id.socket_textview);
        btn.setEnabled(false);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = et.getText().toString();
                if (!TextUtils.isEmpty(msg) && pw != null) {
                    new Thread() {
                        @Override
                        public void run() {
                            pw.println(msg);
                        }
                    }.start();
                    String time = new SimpleDateFormat("(HH:mm:ss)").format(new Date(System.currentTimeMillis()));
                    String msgToShow = "self " + time + ": " + msg + "\n";
                    tv.setText(msgToShow);
                }
            }
        });

        startService(new Intent(this, TCPServerService.class));
        new Thread() {
            @Override
            public void run() {
                connectToTCPServer();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        if (clientSocket != null) {
            try {
                clientSocket.shutdownInput();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    private void connectToTCPServer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 6842);
                clientSocket = socket;
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                mH.sendEmptyMessage(MSG_CONNECTED);
                Log.d("flaggg", "client connect to server success");
            } catch (IOException e) {
                e.printStackTrace();
                SystemClock.sleep(1000);
                Log.d("flaggg", "connect server failed, retry...");
            }
        }

        try {
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!ClientActivity.this.isFinishing()) {
                String msg = fromServer.readLine();
                Log.d("flaggg", "client receive: " + msg);
                if (msg != null) {
                    String time = new SimpleDateFormat("(HH:mm:ss)").format(new Date(System.currentTimeMillis()));
                    String msgToShow = "server " + time + ": " + msg + "\n";
                    mH.sendMessage(mH.obtainMessage(MSG_RECEIVE_MSG, msgToShow));
                }
            }
            Log.d("flaggg", "quit...");
            pw.close();
            fromServer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
