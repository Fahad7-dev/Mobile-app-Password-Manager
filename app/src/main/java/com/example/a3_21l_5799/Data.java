package com.example.a3_21l_5799;

public class Data {

    int id;
    String username;
    String password;
    String url;

    Data()
    {}
    Data(int id,String username,String password, String url)
    {
        this.id=id;
        this.username=username;
        this.password=password;
        this.url=url;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getUrl() {
        return url;
    }


}
