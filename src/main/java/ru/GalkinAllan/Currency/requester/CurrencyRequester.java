package ru.GalkinAllan.Currency.requester;

import java.io.IOException;

public interface CurrencyRequester {
    String getRatesASXml(String url) throws IOException, InterruptedException;
}
