package test.android.com.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Taresh Tank on 9/14/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {


    private ProgressDialog progressDialog;
    Context context;
    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://miniproject.esy.es/login.php";
        String register_url = "http://miniproject.esy.es/register.php";
        if(type.equals("login")) {
            try {
                String pemail = params[1];
                String ppassword = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(pemail, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(ppassword, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else  if(type.equals("register")){
            try {
                String pname = params[1];
                String psurname = params[2];
                String pemail = params[3];
                String ppassword = params[4];
                String pradio = params[5];
                String pmobile = params[6];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("namekey", "UTF-8") + "=" + URLEncoder.encode(pname, "UTF-8") + "&"+
                        URLEncoder.encode("surnamekey", "UTF-8") + "=" + URLEncoder.encode(psurname, "UTF-8") + "&"+
                        URLEncoder.encode("emailkey", "UTF-8") + "=" + URLEncoder.encode(pemail, "UTF-8") + "&"
                        + URLEncoder.encode("passwordkey", "UTF-8") + "=" + URLEncoder.encode(ppassword, "UTF-8") + "&"
                        + URLEncoder.encode("radiokey", "UTF-8") + "=" + URLEncoder.encode(pradio, "UTF-8")+ "&"
                        + URLEncoder.encode("mobilekey", "UTF-8") + "=" + URLEncoder.encode(pmobile, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                String result = "", line = "";
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(final String  result) {
        final String temp_result = result;
        progressDialog.dismiss();
        Handler handler =  new Handler(context.getMainLooper());
        handler.post( new Runnable(){
            public void run(){
                Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
                /*if(temp_result.toLowerCase().contains("welcome".toLowerCase())){
                    Log.d("INSIDE IF CONDITION","SUCCESSFUL");
                    context.startActivity(new Intent(context,InfoAndComplaint.class));
                }*/
            }
        });
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
