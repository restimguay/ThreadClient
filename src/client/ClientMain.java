/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fftmpc
 */
public class ClientMain {

    public ClientMain() {

    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Socket client = new Socket("localhost", 9000);
            ObjectInputStream inputStream = null;
            ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
            outStream.writeObject(new String(br.readLine()));
            inputStream = new ObjectInputStream(client.getInputStream());
            while (true) {
                String msg = br.readLine();
                if ("exit" == msg) {
                    System.exit(0);
                }
                outStream.writeObject(msg);
                print("Server says: " + inputStream.readObject().toString());
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void print(Object msg) {
        System.out.println(msg);
    }
}
