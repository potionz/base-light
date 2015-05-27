package org.synapsia.david.myapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button boff;
    TextView tTemp;
    EditText hostxx;
    EditText portxx;

   // private String portxxx = Integer.getInteger(portxx.getText().toString());

    private class SvetloAsyncT extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... ip){
            try {
                Socket socket = new Socket(ip[0], Integer.parseInt(ip[1]));

                OutputStream out = socket.getOutputStream();
                PrintWriter output = new PrintWriter(out);

                output.write(ip[2]);
                output.flush();

            //    System.out.println(hostxx.getText().toString());
                socket.close();


            } catch (Exception e) {
              //  textline.setText(e.toString());
            }
            return null;
        }


    }



    public void svetlo (final String command){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {


                try {
                    Socket socket = new Socket("10.0.0.136", 8008);

                    OutputStream out = socket.getOutputStream();
                    PrintWriter output = new PrintWriter(out);

                    output.write(command);
                    output.flush();

              //      System.out.println(hostxx.getText().toString());
                    socket.close();


                } catch (Exception e) {
                 //   textline.setText(e.toString());
                }

            }
        });
        thread.start();
    }


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


        hostxx = (EditText)findViewById(R.id.hostx);
        portxx = (EditText)findViewById(R.id.portx);

        tTemp=(TextView)findViewById(R.id.textTemp);
        tTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                tTemp.setText("Temperature: kokot!");

            }
        });



        lon=(Button)findViewById(R.id.lightON);
        lon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvetloAsyncT task = new SvetloAsyncT();
                String ip = hostxx.getText().toString();
                String port = portxx.getText().toString();
                task.execute(new String[] {ip, port, "e0"});

            //    svetlo("e0");
            }

        });


        loff=(Button)findViewById(R.id.lightOFF);
        loff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvetloAsyncT task = new SvetloAsyncT();
                String ip = hostxx.getText().toString();
                String port = portxx.getText().toString();
                task.execute(new String[] {ip, port, "d0"});
            //    svetlo("d0");

            }
   /*             Thread thread = new Thread(new Runnable(){
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
            }*/

        });

        ron=(Button)findViewById(R.id.rightON);
        ron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvetloAsyncT task = new SvetloAsyncT();
                String ip = hostxx.getText().toString();
                String port = portxx.getText().toString();
                task.execute(new String[] {ip, port, "e1"});
              //  svetlo("e1");
            }

    /*            Thread thread = new Thread(new Runnable(){
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
            }*/

        });

        roff=(Button)findViewById(R.id.rightOFF);
        roff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvetloAsyncT task = new SvetloAsyncT();
                String ip = hostxx.getText().toString();
                String port = portxx.getText().toString();
                task.execute(new String[] {ip, port, "d1"});
            //    svetlo ("d1");
            }

   /*             Thread thread = new Thread(new Runnable(){
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
*/
        });

        boff=(Button)findViewById(R.id.bothOFF);
        boff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SvetloAsyncT task = new SvetloAsyncT();
                String ip = hostxx.getText().toString();
                String port = portxx.getText().toString();
                task.execute(new String[]{ip, port, "d0"});
                SvetloAsyncT task1 = new SvetloAsyncT();
                task1.execute(new String[]{ip, port, "d1"});
                //    svetlo ("d1");
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
