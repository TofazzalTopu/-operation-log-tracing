package com.asl.pms.viewmodel;

import java.util.Comparator;

public class DateWiseComparator implements Comparator<WorkSessionWiseLog> {
    @Override
    public int compare(WorkSessionWiseLog obj1, WorkSessionWiseLog obj2) {
        return (obj1.getId() < obj2.getId()) ? -1 : (obj1.getId() > obj2.getId()) ? 1 : 0;
    }
}