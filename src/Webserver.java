import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Webserver {
    private static HttpResponse handleMainPage(){
        String body = "";
        TemplateProcessor processor = new TemplateProcessor("template.html");
        TreeMap map = new TreeMap();
        map.put("<tr><td><b>ID</b></td><td><b>Page</b></td><td><b>Relevance</b></td></tr> ","");
        map.put("<tr><td>1</td><td><a href=\"D.txt\">D.txt</a></td>","");
        map.put("<td>0.3065295545993977</td></tr>","");
        map.put("<tr><td>2</td><td><a href=\"C.txt\">C.txt</a></td>","");
        map.put("<td>0.1758871913668732</td></tr> ","");
        map.put("<tr><td>3</td><td><a href=\"E.txt\">E.txt</a></td> ","");
        map.put("<td>0.16589500683583558</td></tr>","");
        map.put("<tr><td>4</td><td><a href=\"B.txt\">B.txt</a></td> ","");
        map.put("<td>0.0208695652173913481</td></tr> ","");
        map.put("<tr><td>5</td><td><a href=\"A.txt\">A.txt</a></td>","");
        map.put("<td>0.020869565217391348</td></tr> ","");


        try {
            body =processor.replace(map);

            return new HttpResponse(HttpStatus.Ok, body);
        } catch (Exception e){
            System.out.println(e.toString());
            return new HttpResponse( HttpStatus.InternalServerError, "dummy");
        }


        }

    private static HttpResponse handleSearchRequest(String query){

        double pageRankDampingFactor = 0.85;
        double weightingFactor = 0.6;
        LinkedDocumentCollection ldc;
        {
            LinkedDocumentCollection temp = new LinkedDocumentCollection();
        temp.appendDocument(new LinkedDocument("B.txt", "", "", null, null, "link:A.txt link:E.txt", "B.txt"));
            ldc = temp.crawl();
        }
        String body = "";
        TemplateProcessor processor = new TemplateProcessor("template.html");
        TreeMap map = new TreeMap();
        double[] relevance = ldc.match(query, pageRankDampingFactor, weightingFactor);

        map.put("<form action=\"/search\">","");
        map.put("<h2>PGdP Search Engine</h2>","");
        map.put("<input type=\"text\" name=\"query\" value=\"einfach\">","");
        map.put("<input type=\"submit\" value=\"Submit Query\">","");
        map.put("</form>","");
        map.put("<tr><td>2</td><td><a href=\"D.txt\">D.txt</a></td> ","<tr><td>1</td><td><a href="+ldc.get(0).getTitle()+">"+ldc.get(0).getTitle()+"</a></td>");
        map.put("<td>0.3065295545993977</td></tr>","<td>"+relevance[0]+"</td></tr> ");
        map.put("<tr><td>2</td><td><a href=\"C.txt\">C.txt</a></td> ","<tr><td>1</td><td><a href="+ldc.get(1).getTitle()+">"+ldc.get(1).getTitle()+"</a></td>");
        map.put("<td>0.1758871913668732</td></tr>","<td>"+relevance[1]+"</td></tr> ");
        map.put("<tr><td>3</td><td><a href=\"E.txt\">E.txt</a></td>","<tr><td>1</td><td><a href="+ldc.get(2).getTitle()+">"+ldc.get(2).getTitle()+"</a></td>");
        map.put("<td>0.16589500683583558</td></tr>","<td>"+relevance[2]+"</td></tr> ");
        map.put("<tr><td>4</td><td><a href=\"B.txt\">B.txt</a></td>","<tr><td>1</td><td><a href="+ldc.get(3).getTitle()+">"+ldc.get(3).getTitle()+"</a></td>");
        map.put("<td>0.0208695652173913481</td></tr>","<td>"+relevance[3]+"</td></tr> ");
        map.put("<tr><td>5</td><td><a href=\"A.txt\">A.txt</a></td>","<tr><td>1</td><td><a href="+ldc.get(4).getTitle()+">"+ldc.get(4).getTitle()+"</a></td>");
        map.put("<td>0.020869565217391348</td></tr>","<td>"+relevance[4]+"</td></tr> ");

        try {
            body =processor.replace(map);

        } catch (Exception e){
            System.out.println(e);
            return new HttpResponse( HttpStatus.InternalServerError, "dummy");
        }
        return new HttpResponse(HttpStatus.Ok, body);


    }
    private static HttpResponse handleFileRequest(String fileName)
    {
        try {
            String body = Terminal.readFile(fileName)[1];
            return new HttpResponse(HttpStatus.Ok, body);
        } catch (Exception e)
        {
            return new HttpResponse(HttpStatus.BadRequest, "The file doesn't have a body");
        }


    }
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket (5000, 10, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e)
        {
            System.err.println("Could not listen on port: 5000.");
            System.exit(1);
        }
        while(true) {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();

            if(clientSocket != null)
                System.out.println("Connected");

        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }


        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        BufferedReader in = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())));


        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("\r\n");
        out.println(handleMainPage());
        out.flush();





            String request = in.readLine();

            System.out.println(request);

            HttpRequest req = new HttpRequest((request));

            //out.close();
            if (req.getParameters().get("query") != null) {


                out.println(handleSearchRequest(req.getParameters().get("query")));
                out.flush();


                System.out.println("reached");

            }
            if(req.path!=null){
                if(req.path.contains("txt"))
                out.println(handleFileRequest(req.path.substring(1)));
                out.flush();
            }

        }
        //clientSocket.close();
        //serverSocket.close();



    }

    }
