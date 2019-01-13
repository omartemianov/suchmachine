import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.TreeMap;

public class Webserver {
    private static HttpResponse handleMainPage(){
        String body = "";
        TemplateProcessor processor = new TemplateProcessor("template.txt");
        TreeMap map = new TreeMap();
        map.put("<tr><td><b>ID</b></td><td><b>Page</b></td><td><b>Relevance</b></td></tr> ","");
        map.put("<tr><td>1</td><td><a href=\"D.txt\">D.txt</a></td>","");
        map.put("<td>0.3065295545993977</td></tr>","");
        map.put("<tr><td>3</td><td><a href=\"E.txt\">E.txt</a></td> ","");
        map.put("<td>0.16589500683583558</td></tr>","");
        map.put("<tr><td>4</td><td><a href=\"B.txt\">B.txt</a></td> ","");
        map.put("<td>0.0208695652173913481</td></tr> ","");
        map.put("<tr><td>5</td><td><a href=\"A.txt\">A.txt</a></td>","");
        map.put("<td>0.020869565217391348</td></tr> ","");

        try {
            body =processor.replace(map);

        } catch (Exception e){
            return new HttpResponse( HttpStatus.InternalServerError, "dummy");
        }
        return new HttpResponse(HttpStatus.Ok, body);

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
        TemplateProcessor processor = new TemplateProcessor("template.txt");
        TreeMap map = new TreeMap();
        double[] relevance = ldc.match(query, pageRankDampingFactor, weightingFactor);

        map.put("<tr><td>1</td><td><a href=\"D.txt\">D.txt</a></td>","<tr><td>1</td><td><a href="+ldc.get(0).getTitle()+">"+ldc.get(0).getTitle()+"</a></td>");
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
        Socket socket = new Socket("www2.in.tum.de", 80);


    }

    }
