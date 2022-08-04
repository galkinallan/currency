package ru.GalkinAllan.Currency.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.GalkinAllan.Currency.models.Currency;

import java.awt.print.Pageable;

public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long> {
    Currency findByCharCode(String charCode);
}
