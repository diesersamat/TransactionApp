package me.sgayazov.transactionapp.data.provider;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

class BaseDataProvider {

    protected final Gson gson;
    protected Context context;

    BaseDataProvider(Gson gson, Context context) {
        this.gson = gson;
        this.context = context;
    }

    String readFile(int rawFile) throws IOException {
        Resources res = context.getResources();
        InputStream inputStream = res.openRawResource(rawFile);

        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);
        inputStream.close();
        return new String(b);
    }
}
