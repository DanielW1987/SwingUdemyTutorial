package com.wagner.demo_app.main;

import com.wagner.demo_app.ui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String... args){
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
        });
    }

}