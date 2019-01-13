public enum HttpStatus {
    Ok(200, "Ok"),
    BadRequest(400, "Bad Request"),
    Forbidden(403, "Forbidden"),
    NotFound(404, "Not Found"),
    MethodNotAllowed(405, "Method Not Allowed"),
    InternalServerError(500,"Internal Server Error");

    private int code;
    private String text;
    public String getText(){
        return text;
    }

    public int getCode() {  return code;  }

    private HttpStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }
}


