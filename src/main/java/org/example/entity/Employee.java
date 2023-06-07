package org.example.entity;

public class Employee {
    private Long   id;
    private String name;
    private String lastName;
    private Long   phone;
    private int    codActivity;
    private int    codHotel;

    public Employee(){}

    public Employee(Long id){
        this.id = id;
    }

    public Employee(Long id, String name, String lastName, Long phone, int codActivity, int codHotel) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.codActivity = codActivity;
        this.codHotel = codHotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public int getCodActivity() {
        return codActivity;
    }

    public void setCodActivity(int codActivity) {
        this.codActivity = codActivity;
    }

    public int getCodHotel() {
        return codHotel;
    }

    public void setCodHotel(int codHotel) {
        this.codHotel = codHotel;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", codActivity=" + codActivity +
                ", codHotel=" + codHotel +
                '}';
    }
}
