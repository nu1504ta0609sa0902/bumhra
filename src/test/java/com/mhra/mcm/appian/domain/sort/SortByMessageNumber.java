package com.mhra.mcm.appian.domain.sort;

import java.util.Comparator;

import javax.mail.Message;

/**
 * Created by TPD_Auto on 28/07/2016.
 */
public class SortByMessageNumber implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        int to1 = o1.getMessageNumber();
        int to2 = o2.getMessageNumber();
        return to2 - to1;
    }
}
