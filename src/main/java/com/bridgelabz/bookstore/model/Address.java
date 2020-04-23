package com.bridgelabz.bookstore.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

    @Id
    @Column(name = "addressId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long addressId;

    @Column(name = "Name")
    private String Name;

    @Column(name = "mobileNumber")
    private String mobileNumber;

    @Column(name = "pinCode")
    private int pinCode;

    @Column(name = "address")
    private String address;

    @Column(name = "locality")
    private String locality;

    @Column(name = "city")
    private String city;

    @Column(name = "landMark")
    private String landMark;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
