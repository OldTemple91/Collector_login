package swing.mobility.rider.collector.service.dto;

public enum TokenGrantType {
    PASSWORD("PASSWORD", "패스워드"),
    REFRESH_TOKEN("REFRESH_TOKEN", "리프레시"),
    ;

    String code;
    String name;

    TokenGrantType(String code, String name){
        this.code = code;
        this.name = name;
    }

}
