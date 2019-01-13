public class HttpResponse {
    private HttpStatus status;
    private String body;
    public HttpResponse(HttpStatus status, String body){
        this.status = status;
        this.body = body;
    }

    @Override
    public String toString()
    {
        return body;
    }
}
