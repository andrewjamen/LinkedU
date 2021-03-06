/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.UnivDAO;

/**
 *
 * @author Andrew
 */
public class UnivBean{
    
    private String username;
    private String password;
    private String name;
    private String confirm;
    private String email;
    private String address;
    private String city;
    private String state;
    private int avgAct;
    private double avgGpa;
    private String request;
    
    public UnivBean(){
    }

    public UnivBean(String name, String username, String password, String email, String address, String city, String state, int avgAct, double avgGpa, String request) {
        
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.avgAct = avgAct;
        this.avgGpa = avgGpa;
        this.request = request;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAvgAct() {
        return avgAct;
    }

    public void setAvgAct(int avgAct) {
        this.avgAct = avgAct;
    }

    public double getAvgGpa() {
        return avgGpa;
    }

    public void setAvgGpa(double avgGpa) {
        this.avgGpa = avgGpa;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    
    
    


    
}

