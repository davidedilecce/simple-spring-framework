package utils.responses;

public class OkResponse extends BaseResponse {


    public OkResponse() {
        this.setError(false);
    }

    public OkResponse(Object value) {
        this.setError(false);
        this.setValue(value);
    }

}