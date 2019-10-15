package senac.jogos.trabalho1;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trabalho1_Server extends javax.swing.JFrame {

    /* A Fazer:
        Declarar o socket do cliente e do servidor
    */
    private String          texto;
    private Socket          socketCliente;
    private ServerSocket    socketServidor;
    private OutputStream    output;
    private InputStream     inputCliente;
    private BufferedReader  br;
    private JButton         jButton1;
    private JButton         jButton2;
    private JScrollPane     jScrollPane1;
    private JTextArea       jTextArea1;
    private JTextField      jTextField1;
    private BorderLayout    layout; 
   
    public Trabalho1_Server() {
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Enviar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                /* Entra aqui quando ocorre o clique do mouse no botao 1
                    A Fazer:
                    1 - addStrTextArea(jTextField1.getText()); //Captura o texto do campo de texto e o insere na área de texto
                    2 - Envia mensagem jTextField1.getText() via socket
                    3 - Aguarda Resposta do outro socket  (usando o m�todo read do stream do socket)
                    4 - Incluir o texto recebido do socket na AreadeTexto com o método addStrTextArea(mensagem);
                    onde mensagem é a string recebida via socket
                 */
                //1
                //addStrTextArea("SERVIDOR: " + jTextField1.getText());
//                //2
//                PrintWriter pw = new PrintWriter(output, true);
//                pw.println(jTextField1.getText());
                
//                try {
//                    InputStreamReader reader = new InputStreamReader(inputCliente);
//                    br = new BufferedReader(reader);
//                    texto = br.readLine();
//                    addStrTextArea("CLIENTE: " + texto);
//                } catch (IOException ex) {
//                    Logger.getLogger(Trabalho1_Client.class.getName()).log(Level.SEVERE, null, ex);
//                }  
            }
        });

        jButton2.setText("Desconectar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                /* Entra aqui quando ocorre o clique do mouse no botao 2 
                A Fazer:	
                    Fecha o socket
                */ 
            }
        });
			
	layout = new BorderLayout( 5, 5 ); 
        getContentPane().setLayout(layout);
	add( jScrollPane1, BorderLayout.CENTER ); 
	add( jTextField1, BorderLayout.SOUTH ); 
	add( jButton1, BorderLayout.EAST ); 
	add( jButton2, BorderLayout.NORTH ); 		
    }

    public void init() throws IOException{	
	/* A Fazer:
            1 - Criar um socket servidor
            2 - receber a primeira mensagem do cliente  (usando o método read do stream do socket)
            3 - Incluir o texto recebido do socket na AreadeTexto com o método addStrTextArea(mensagem);
            onde mensagem é a string recebida via socket		
	*/
        socketServidor = new ServerSocket(4500);
        socketCliente = socketServidor.accept();
        output = socketCliente.getOutputStream();
        inputCliente = socketCliente.getInputStream();
        
        
//        InputStreamReader reader  = new InputStreamReader(inputCliente);
//        br = new BufferedReader(reader);
//        String textoRecebido = br.readLine();
        //addStrTextArea(textoRecebido);        
    }       

//    public void addStrTextArea(String str) {
//        jTextArea1.append(str.trim() + "\n");
//    }
    
    public static void main(String[] args) throws IOException {
            Trabalho1_Server trab1_server = new Trabalho1_Server();
	    trab1_server.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    trab1_server.setSize( 300, 200 ); // set frame size
            trab1_server.setVisible( true );
            trab1_server.setTitle("Servidor");
            trab1_server.init();
            
            Escuta_Resp escutaServer = new Escuta_Resp(trab1_server.socketCliente, trab1_server.jTextArea1);
            new Thread (escutaServer).start();
    }   
}
