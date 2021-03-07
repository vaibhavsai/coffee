package coffee.machine;

public class Response {
    private State state;
    private String message;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response(State state) {
        this.state = state;
    }

    public Response() {
    }
}
