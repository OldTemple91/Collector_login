package swing.mobility.rider.collector.web.exception;

public class ResourceNotFoundException extends BizBaseException {
    public ResourceNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND);
    }
}
