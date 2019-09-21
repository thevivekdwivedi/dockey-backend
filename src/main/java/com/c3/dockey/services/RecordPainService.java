package com.c3.dockey.services;

public class RecordPainService {
    private static RecordPainService self = null;

    private RecordPainService() {
    }

    public static RecordPainService getInstance() {
        if (self == null) {
            self = new RecordPainService();
        }

        return self;
    }
}
