package com.wagner.demo_app.listener;

import com.wagner.demo_app.event.FormEvent;

import java.util.EventListener;

public interface FormListener extends EventListener {

    void formEventOccured(FormEvent event);
}
