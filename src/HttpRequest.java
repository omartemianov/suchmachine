import java.util.Map;
import java.util.HashMap;

public class HttpRequest {
    String request;
    HttpMethod method;
    String path;
    Map<String,String> parameters = new HashMap<String, String>();
    public HttpRequest(String request){
        this.request = request;
        if (request == null)
            return;

        String[] str = request.split(" ");
        if (request.length()<4) return;
        if(str[0].equalsIgnoreCase("GET"))
            this.method = HttpMethod.GET;
        else if (str[0].equalsIgnoreCase("POST"))
            this.method = HttpMethod.POST;
        else return;


        if (request.indexOf('?')!=-1)
            this.path = request.substring(request.indexOf('/'),request.indexOf('?'));
         else
             this.path = str[1];

        if(request.indexOf('?')!=-1)
            this.parameters.put(request.substring(request.indexOf('?')+1,request.indexOf('=')),request.substring(request.indexOf('=')+1));



    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public HttpMethod getMethod() {
        return method;
    }
}
