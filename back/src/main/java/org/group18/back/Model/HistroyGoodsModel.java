package org.group18.back.Model;

import org.group18.back.Entity.Goods;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistroyGoodsModel {

    private List<Goods> goodsList;
    private Date date;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
