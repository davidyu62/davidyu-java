package org.algo.davidyu.process;

import java.io.*;

public class ProcessExecute {
    public void executeProcessBuilderWithParameter(){
        BufferedReader br = null;
        Process process = null;
        try {
//            process = new ProcessBuilder("C:\\davidyu\\MyEXE.exe").start();
            process = new ProcessBuilder("C:\\sp_workspace\\sp_java_test\\SUB2\\SP_TEST.bat").start();                 // parameter
//            process = new ProcessBuilder("C:\\davidyu\\MyEXE.exe", "davidyu").start();                 // parameter
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while(true){
                try{
                    if(((line = br.readLine()) != null)){
                        System.out.println(line);
                    }else{
                        break;
                    }
                }catch(IOException e){

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // send line
//        BufferedWriter bw = null;
//        try {
//            bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
//            String line = null;   // line + \n necessary
//            bw.write(line);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }


    public String  dataTransferToProcessBuilder(String command){
        ProcessBuilder b = new ProcessBuilder(command);
        StringBuffer ret = new StringBuffer();

        //표준 에러 출력을 머지 해 출력한다
        b.redirectErrorStream(false);
        Process p;

        try {
            p = b.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        BufferedReader reader = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
        String line = null;

        //표준 에러 출력이 표준 출력에 머지 해 출력되므로, 표준 출력만 읽어내면 된다
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
                ret.append(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        return ret.toString();
    }

    public static void main(String[] args){
        new ProcessExecute().executeProcessBuilderWithParameter();
    }
}
