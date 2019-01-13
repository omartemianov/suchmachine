import java.util.Map;
public class HttpRequest {
    String request;
    HttpMethod method;
    String path;
    Map<String,String> parameters;
    public HttpRequest(String request){
        this.request = request;

        if(request.substring(0, request.indexOf('/')-1).equalsIgnoreCase("GET"))
            this.method = HttpMethod.GET;
        else if (request.substring(0, request.indexOf('/')-1).equalsIgnoreCase("POST"))
            this.method = HttpMethod.POST;

        if(request.indexOf('?')!=-1)
        this.path = request.substring(request.indexOf('/'),request.indexOf('?'));
        else this.path = request.substring(request.indexOf('/'),request.indexOf(' '));

        if(request.indexOf('?')!=-1)
        this.parameters.put(request.substring(request.indexOf('?')+1,request.indexOf('=')),request.substring(request.indexOf('=')+1));



    }

}
