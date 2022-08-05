package ru.GalkinAllan.Currency.models;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "valuteid")
    private String valuteId;

    @Column(name = "numcode")
    private String numCode;

    @Column(name = "charcode")
    private String charCode;

    @Column(name = "nominal")
    private String nominal;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    public Currency() {
    }

    public Currency(String valuteId, String numCode, String charCode, String nominal, String name, String value) {
        this.valuteId = valuteId;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValuteId() {
        return valuteId;
    }

    public void setValuteId(String valuteId) {
        this.valuteId = valuteId;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}




