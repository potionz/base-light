package org.synapsia.david.myapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends ActionBarActivity {

    TextView textline;
    Button btn;
    Button lon;
    Button loff;
    Button ron;
    Button roff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

/*        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/

        textline=(TextView)findViewById(R.id.dateTxt);
        //     date.setText("chiddiousidfsd");



        lon=(Button)findViewById(R.id.lightON);
        lon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {


                        try {
                            Socket socket = new Socket("10.0.0.136", 8008);

                            OutputStream out = socket.getOutputStream();
                            PrintWriter output = new PrintWriter(out);

                            output.write("e0");
                            output.flush();

                            socket.close();


                        } catch (Exception e) {
                            textline.setText(e.toString());
                        }

                    }
                });
            thread.start();
            }

        });


        loff=(Button)findViewById(R.id.lightOFF);
        loff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {


                        try {
                            Socket socket = new Socket("10.0.0.136", 8008);

                            OutputStream out = socket.getOutputStream();
                            PrintWriter output = new PrintWriter(out);

                            output.write("d0");
                            output.flush();

                            socket.close();


                        } catch (Exception e) {
                            textline.setText(e.toString());
                        }

                    }
                });
                thread.start();
            }

        });

        ron=(Button)findViewById(R.id.rightON);
        ron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {


                        try {
                            Socket socket = new Socket("10.0.0.136", 8008);

                            OutputStream out = socket.getOutputStream();
                            PrintWriter output = new PrintWriter(out);

                            output.write("e1");
                            output.flush();

                            socket.close();


                        } catch (Exception e) {
                            textline.setText(e.toString());
                        }

                    }
                });
                thread.start();
            }

        });

        roff=(Button)findViewById(R.id.rightOFF);
        roff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread thread = new Thread(new Runnable(){
                    @Override
                    public void run() {


                        try {
                            Socket socket = new Socket("10.0.0.136", 8008);

                            OutputStream out = socket.getOutputStream();
                            PrintWriter output = new PrintWriter(out);

                            output.write("d1");
                            output.flush();

                            socket.close();


                        } catch (Exception e) {
                            textline.setText(e.toString());
                        }

                    }
                });
                thread.start();
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
