import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestItClient {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("127.0.0.1", 8000);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        String input="";
        try {
            while(!input.equalsIgnoreCase("exit")) {


                String answer =in.readLine();
                //String question = in.readLine();
                //System.out.println(question);
                    //while((answer = in.readLine())!=null) {
                    //    System.out.println(answer);
                   // }
                   // while(answer!=null)
                   // {
                        System.out.println(answer);
                        //answer = in.readLine();
                   // }


                input = Terminal.ask("");


                out.println(input);
                out.flush();

            }
        } finally {
            socket.close();
        }

    }
}
