import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestItClient2 {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Trying 127.0.0.2");
        Socket socket = new Socket("127.0.0.2", 8000);
        System.out.println("Connected to 127.0.0.2");
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
                if(answer!=null)
                answer = answer.replace('|','\n');
                System.out.println(answer);
                //answer = in.readLine();
                // }


                input = Terminal.ask("Please write: ");


                out.println(input);
                out.flush();



            }
        } finally {
            socket.close();
            System.out.println("Connection closed by client");
        }

    }
}
