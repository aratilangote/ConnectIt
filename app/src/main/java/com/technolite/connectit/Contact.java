package com.technolite.connectit;

public class Contact {
    private String mName;
    private String mNumber;

    Contact(String name, String number) {
        mName = name;
        mNumber = number;
    }

    String getName() {
        return mName;
    }

    String getNumber() {
        return mNumber;
    }
}
