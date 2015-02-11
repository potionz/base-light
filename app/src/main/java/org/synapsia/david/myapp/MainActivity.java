package org.synapsia.david.myapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Socket;
import java.util.Date;

public class MainActivity extends ActionBarActivity {

    TextView date;
    Button btn;
    Button lon;
    Button loff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

/*        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        date=(TextView)findViewById(R.id.dateTxt);
        //     date.setText("chiddiousidfsd");

        btn=(Button)findViewById(R.id.datebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                date.setText(new Date().toString());
            }
        });

        lon=(Button)findViewById(R.id.lightON);
        lon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Socket socket = new Socket("10.0.78.75", 50505);

                OutputStream out = socket.getOutputStream();
                PrintWriter output = new PrintWriter(out);

                mStatusText.setText("Sending Data to PC");
                output.println("Hello from Android");
                mStatusText.setText("Data sent to PC");

                socket.close();
                mStatusText.setText("Socket closed");


            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_exit) {
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}
