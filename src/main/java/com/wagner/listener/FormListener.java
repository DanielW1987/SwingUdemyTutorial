package com.wagner.listener;

import com.wagner.event.FormEvent;

import java.util.EventListener;

public interface FormListener extends EventListener {

    void formEventOccured(FormEvent event);
}
