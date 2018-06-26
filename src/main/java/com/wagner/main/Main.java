package com.wagner.main;

import com.wagner.ui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String... args){
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
        });
    }

}