package coffee.machine;

import lombok.Data;

@Data
public class Response {
    private State state;
    private String message;

    public Response(State state) {
        this.state = state;
    }

    public Response() {
    }
}
