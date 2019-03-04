package dev.app.flikit.data;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetRequestTask extends AsyncTask<String, Integer, ApiResponse>
{
    private static final String TAG = HTTPGetRequestTask.class.getSimpleName();

    private HttpCallback callbackListener;
    private int statusCode;


    @Override
    protected void onPreExecute()
    {
        callbackListener.onPreExecute();
    }


    @Override
    protected ApiResponse doInBackground(String... params)
    {
        BufferedReader reader = null;

        try
        {
            URL url = new URL(params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            try
            {

                this.statusCode = connection.getResponseCode();
                InputStream inputStream = connection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null)
                {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0)
                {
                    return null;
                }

                return JSONParser.convertResponse(buffer.toString());
            }

            catch (Exception e)
            {
                e.printStackTrace();
            }

            finally
            {
                if (reader != null)
                {
                    try
                    {
                        reader.close();
                    }

                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(ApiResponse response)
    {
        callbackListener.onPostExecute(response, statusCode);
    }



    public interface HttpCallback
    {
        void onPostExecute(ApiResponse response, int statusCode);
        void onPreExecute();
    }


    public void setHttpListener(final HttpCallback callbackListener)
    {
        this.callbackListener = callbackListener;
    }
}