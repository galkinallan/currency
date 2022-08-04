package ru.GalkinAllan.Currency.parser;

import ru.GalkinAllan.Currency.models.Currency;

import java.util.List;

public interface CurrencyParser {
    List<Currency> parse(String ratesAsString);
}
