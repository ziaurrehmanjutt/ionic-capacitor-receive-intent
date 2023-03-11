package com.iibtech.ionic.intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

// import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@CapacitorPlugin(name = "IntentReceived")
public class IntentReceivedPlugin extends Plugin {

    private IntentReceived implementation = new IntentReceived();
    public static final String EVENT_SEND_ACTION_INTENT = "newIntentReceived";

    /**
     * Handle ACTION_VIEW intents to store a URL that was used to open the app
     * 
     * @param intent
     */
    @Override
    protected void handleOnNewIntent(Intent intent) {
        super.handleOnNewIntent(intent);

        // read intent action
        String action = intent.getAction();
        String type = intent.getType();
        // Log.w("Action is", action);
        if (Intent.ACTION_SEND.equals(action)) {
            // Get the extras from the intent
            Bundle bundle = intent.getExtras();

            JSObject extras = new JSObject();
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                extras.put(key, value);
            }

            extras.put("other", readItemAt(intent, type, 0));
            JSObject ret = new JSObject();
            ret.put("extras", extras);

            notifyListeners(EVENT_SEND_ACTION_INTENT, ret, true);
        }
    }

    private JSObject readItemAt(Intent intent, String type, int index) {
        JSObject ret = new JSObject();
        String title = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        Uri uri = null;

        if (intent.getClipData() != null && intent.getClipData().getItemAt(index) != null)
            uri = intent.getClipData().getItemAt(index).getUri();

        String url = null;

        // Handling web links as url
        if ("text/plain".equals(type) && intent.getStringExtra(Intent.EXTRA_TEXT) != null) {
            url = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        // Handling files as url
        else if (uri != null) {
            final Uri copyfileUri = copyfile(uri);
            url = (copyfileUri != null) ? copyfileUri.toString() : null;
        }

        if (title == null && uri != null)
            title = readFileName(uri);

        ret.put("title", title);
        ret.put("description", null);
        ret.put("type", type);
        ret.put("url", url);
        return ret;
    }

    public String readFileName(Uri uri) {
        Cursor returnCursor = getContext().getContentResolver().query(uri, null, null, null, null);
        /*
         * Get the column indexes of the data in the Cursor,
         * move to the first row in the Cursor, get the data,
         * and display it.
         */
        returnCursor.moveToFirst();

        int clmInd = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        if (clmInd >= 0) {
            return returnCursor.getString(clmInd);
        } else {
            return "";
        }

    }

    Uri copyfile(Uri uri) {
        final String fileName = readFileName(uri);
        File file = new File(getContext().getFilesDir(), fileName);

        try (FileOutputStream outputStream = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                InputStream inputStream = getContext().getContentResolver().openInputStream(uri)) {
            // IOUtils.copy(inputStream, outputStream);
            return Uri.fromFile(file);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
