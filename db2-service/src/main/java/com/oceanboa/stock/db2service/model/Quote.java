package com.oceanboa.stock.db2service.model;

import javax.persistence.*;

//Cluster > Catalog > Schema > Table > Columns & Rows

@Entity  //tells hibernate to make table out of class
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Quote(){

    }

    public Quote(String userName, String quote) {
        this.userName = userName;
        this.quote = quote;
    }

    @Column(name = "user_name")
    private String userName;

    @Column(name = "quote")
    private String quote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }


}
