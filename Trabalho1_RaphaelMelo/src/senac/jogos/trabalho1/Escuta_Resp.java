/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senac.jogos.trabalho1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author raphael.omelo
 */
public class Escuta_Resp implements Runnable {
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private BufferedReader br;
    private String texto;
    private JTextArea areaText;
    
    
    public Escuta_Resp(Socket socket, JTextArea areaText) throws IOException{
        this.socket = socket;  
        input = socket.getInputStream();
        output = socket.getOutputStream();
        this.areaText = areaText;
    }

    @Override
    public void run() {

        while (true) {
            try {
                InputStreamReader reader = new InputStreamReader(input);
                br = new BufferedReader(reader);
                texto = br.readLine();
                PrintWriter pw = new PrintWriter(output, true);
                pw.println(areaText.getText());
                areaText.append("TEXT \n");

                
                //areaText.setText(null);
            } catch (IOException ex) {
                Logger.getLogger(Trabalho1_Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
