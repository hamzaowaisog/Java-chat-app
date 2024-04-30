/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat.app.event;

import java.io.File;

/**
 *
 * @author MIR HAMZA
 */
public interface EventFileReceiver {
    public void onReceiving(double percentage);
    public void onStartReceiving();
    public void onFinish(File file);
}
