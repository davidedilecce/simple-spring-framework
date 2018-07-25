package utils.responses;

public class ErrorResponse extends BaseResponse {

    private String errorMessage;
    private int errorCode;

    public ErrorResponse(String errorMessage) {
        this.setError(true);
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(int errorCode) {
        this.setError(true);
        this.errorCode = errorCode;
    }



    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
