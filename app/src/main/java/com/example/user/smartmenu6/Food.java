package com.example.user.smartmenu6;

public class Food {



    private String name;
    private String cost;
    private String tableNo;
    private String country;

    public String getOrderTime() {
        return orderTime;
    }

    public int getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(int orderCnt) {
        this.orderCnt = orderCnt;
    }

    private int orderNo;
    private String orderTime;
    private int orderCnt;
    public int getOrderNo() {
        return orderNo;
    }



    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    private String orderSts;

    public Food(String name, String cost, String country, String tableNo) {

        this.name=name;
        this.cost=cost;
        this.country=country;
        this.tableNo=tableNo;

    }

    // korea 클래스를 보고 getKorean 을 찬고 한다.
    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", cost:'" + cost + '\'' +
                ", country:'" + country + '\'' +
                ", tableNo:'" + tableNo + '\'' +

                '}';
    }




    public Food(String name, String cost, String country) {

        this.name=name;
        this.cost=cost;
        this.country=country;
        this.tableNo=tableNo;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String  getTableNo(){
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo=tableNo;
    }

    public String getOrderSts() {
        return orderSts;
    }

    public void setOrderSts(String orderSts) {
        this.orderSts = orderSts;
    }
}
