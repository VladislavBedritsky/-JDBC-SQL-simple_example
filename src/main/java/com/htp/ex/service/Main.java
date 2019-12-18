package com.htp.ex.service;

import com.htp.ex.service.dao.Database;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();
        database.connection();

    }
}
