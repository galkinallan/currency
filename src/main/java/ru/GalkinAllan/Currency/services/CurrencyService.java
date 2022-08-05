package ru.GalkinAllan.Currency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.GalkinAllan.Currency.models.Currency;
import ru.GalkinAllan.Currency.parser.CurrencyParser;
import ru.GalkinAllan.Currency.repositories.CurrencyRepository;
import ru.GalkinAllan.Currency.requester.CurrencyRequesterImpl;

import java.io.IOException;
import java.util.List;



@Service
@Transactional(readOnly = true)
public class CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyParser currencyParser;
    private final CurrencyRequesterImpl currencyRequester;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, CurrencyParser currencyParser, CurrencyRequesterImpl currencyRequester) {
        this.currencyRepository = currencyRepository;
        this.currencyParser = currencyParser;
        this.currencyRequester = currencyRequester;
    }

    public Page<Currency> findall(int pageNumber) {
        Pageable pageable = PageRequest.of( pageNumber - 1, 5);
        return currencyRepository.findAll(pageable);
    }

    public Currency findOne(String charCode) {
        return currencyRepository.findByCharCode(charCode);
    }
    @Transactional
    public void save()  {
        List<Currency> currencies = null;
        try {
            currencies = currencyParser.parse(currencyRequester.getRatesASXml("https://www.cbr.ru/scripts/XML_daily.asp"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Currency currency : currencies) {
            currency.setId(currencyRepository.findByCharCode(currency.getCharCode()).getId());
        }
        currencyRepository.saveAll(currencies);
    }
}
