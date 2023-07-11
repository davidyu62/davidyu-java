package org.algo.davidyu.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {

    public static void main(String[] args) throws IOException, InterruptedException {
    	File destFolder = new File("./ServerFiles");
    	if(!destFolder.exists()) {
    	    destFolder.mkdir(); 
    	}
    	
    	ThreadClass tc = new ThreadClass(); 
        Thread th = new Thread(tc); 
        th.start(); 
    	
	    InputStream in = System.in;
	    InputStreamReader reader = new InputStreamReader(in);
	    BufferedReader br = new BufferedReader(reader);
	    String str;
	    	    
	    while (true)
        {
	    	str = br.readLine();
	    	
			if (str.equals("QUIT"))
			{
				tc.listener.close();
				th.join();
				break;
			}
        }	    
    }

}

class ThreadClass implements Runnable { // Runnable Interface ���� 
	public ServerSocket listener;   
    public void run() {
    	final int BUF_SIZE = 4096;
    	int recvLen;   	
    	byte[] buffer = new byte[BUF_SIZE];
    	
        listener = null;
 		try {
			listener = new ServerSocket(27015);

			while (true) {
                Socket s = listener.accept();
                DataInputStream is = new DataInputStream(s.getInputStream());
                try {
	                String fileName = null;
	                // �����̸� ����
	                while ((fileName = is.readUTF()) != null) {
	                	// ����ũ�� ����
	                	int fileSize = is.readInt();
	                	FileOutputStream fw = new FileOutputStream("./ServerFiles/" + fileName);
	                	int length;
						while (fileSize > 0) {
							// ���ϳ��� ����
							length = is.read(buffer, 0, Math.min(fileSize, buffer.length));
							fileSize -= length;
							fw.write(buffer, 0, length);
						}                	
	                	fw.close();
	                    System.out.println(fileName+" is received.");
	                }
                }
                catch (EOFException e) {
                	System.out.println("Finish Recieve...");
                	s.close();
                }
            }
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
            try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 		
    } 
} 