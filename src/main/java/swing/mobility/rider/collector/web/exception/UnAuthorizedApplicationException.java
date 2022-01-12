package swing.mobility.rider.collector.web.exception;

public class UnAuthorizedApplicationException extends BizBaseException {

    public UnAuthorizedApplicationException() {
        super(ErrorCode.UNAUTHORIZED_APP_KEY);
    }

}
