package com.fussoft.monopoly;

import java.util.List;

public class PropertiesRecord {

    List<MonopolyBoardField> properties;
    int sumPrice;

    public PropertiesRecord(final List<MonopolyBoardField> properties, int sumPrice) {
        this.properties = properties;
        this.sumPrice = sumPrice;
    }

    public List<MonopolyBoardField> getProperties() {
        return properties;
    }

    public void setProperties(List<MonopolyBoardField> properties) {
        this.properties = properties;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }
}
