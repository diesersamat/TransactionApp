package me.sgayazov.transactionapp.data.provider;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import me.sgayazov.transactionapp.R;
import me.sgayazov.transactionapp.data.model.Rate;

public class RateDataProvider extends BaseDataProvider {

    @Inject
    public RateDataProvider(Context context, Gson gson) {
        super(gson, context);
    }

    public List<Rate> getRateList(boolean isUseSecond) throws IOException {
        return gson.fromJson(readFile(isUseSecond ? R.raw.second_rates : R.raw.first_rates),
                new TypeToken<List<Rate>>() {
                }.getType());
    }

}
