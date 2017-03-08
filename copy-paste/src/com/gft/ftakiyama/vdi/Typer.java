package com.gft.ftakiyama.vdi;

import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_NUMPAD0;
import static java.awt.event.KeyEvent.VK_NUMPAD1;
import static java.awt.event.KeyEvent.VK_NUMPAD2;
import static java.awt.event.KeyEvent.VK_NUMPAD3;
import static java.awt.event.KeyEvent.VK_NUMPAD4;
import static java.awt.event.KeyEvent.VK_NUMPAD5;
import static java.awt.event.KeyEvent.VK_NUMPAD6;
import static java.awt.event.KeyEvent.VK_NUMPAD7;
import static java.awt.event.KeyEvent.VK_NUMPAD8;
import static java.awt.event.KeyEvent.VK_NUMPAD9;

import java.awt.AWTException;
import java.awt.Robot;

public class Typer {
    
    private Robot robot;
    
    public Typer(int interval) {
        try {
            this.robot = new Robot();
            this.robot.setAutoDelay(interval);
        } catch (AWTException e) {
            System.err.println("The platform configuration does not allow low-level input control.");
        }
    }
    
    public void type(CharSequence seq) {
        seq.chars().forEach(c -> type(c));
        typeKey('\n');
    }
    
    public void type(int charCode) {
        char[] charCodeStr = Integer.toString(charCode).toCharArray();
        robot.keyPress(VK_ALT);
        robot.waitForIdle();
        typeKey('0');
        for (int i = 0; i < charCodeStr.length; i++) {
            typeKey(charCodeStr[i]);
        }
        robot.keyRelease(VK_ALT);
    }

    private void typeKey(char c) {
        switch (c) {
            case '0': typeKey(VK_NUMPAD0); break;
            case '1': typeKey(VK_NUMPAD1); break;
            case '2': typeKey(VK_NUMPAD2); break;
            case '3': typeKey(VK_NUMPAD3); break;
            case '4': typeKey(VK_NUMPAD4); break;
            case '5': typeKey(VK_NUMPAD5); break;
            case '6': typeKey(VK_NUMPAD6); break;
            case '7': typeKey(VK_NUMPAD7); break;
            case '8': typeKey(VK_NUMPAD8); break;
            case '9': typeKey(VK_NUMPAD9); break;
            case '\n': typeKey(VK_ENTER); break;
        }
    }
    
    private void typeKey(int code) {
        try {
            robot.keyPress(code);
            robot.waitForIdle();
            robot.keyRelease(code);
        } catch (IllegalArgumentException e) {
            System.err.println("Key code " + code + " is not valid." + e.getMessage());
        }
        
    }

    public void releaseAll() {
        robot.keyRelease(VK_ALT);
    }
}
