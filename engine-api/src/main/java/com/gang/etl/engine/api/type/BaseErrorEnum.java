package com.gang.etl.engine.api.type;

import javax.ws.rs.core.Response;

/**
 * @Classname BaseErrorEnum
 * @Description TODO
 * @Date 2020/9/28 21:15
 * @Created by zengzg
 */
public enum BaseErrorEnum {

    SDK_SYNC_INIT("ESYNC000001", "", "SDK Init Error , 请检查配置"),

    SDK_SYNC_RUN("ESYNC000010", "", "SDK Runtime Error , 运行时异常"),

    SDK_QUERY_RUN("ESYNC000021", "", "SDK Query Error , 运行时异常"),

    PARAMETER_ERROR("ESDK0002", "", "Invalid Values , 请检查属性"),

    CREATE_ERR_EXT("ESYNC000031", "", "Check param by error msg"),

    UPDATE_ERR_EXT("ESYNC000035", "", "Check param by error msg"),

    DELETE_ERR_EXT("ESYNC000037", "", "Check param by error msg"),

    QUERY_ERR_EXT("ESYNC00013", "", "");

    private String code;
    private String msg;
    private String solution;
    private Response.Status responseStatus;

    BaseErrorEnum(String code, String msg, String solution) {
        this.code = code;
        this.msg = msg;
        this.solution = solution;
        if (code.equals("EMSG000087")) {
            this.responseStatus = Response.Status.FORBIDDEN;
        } else {
            this.responseStatus = Response.Status.BAD_REQUEST;
        }
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getSolution() {
        return solution;
    }

    public Response.Status getResponseStatus() {
        return responseStatus;
    }

    public static BaseErrorEnum getByCode(String code) {
        for (BaseErrorEnum enumItem : BaseErrorEnum.values()) {
            if (enumItem.getCode().equals(code)) {
                return enumItem;
            }
        }
        return null;
    }

}
