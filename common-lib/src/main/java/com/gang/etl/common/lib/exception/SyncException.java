package com.gang.etl.common.lib.exception;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Base64;

/**
 * @Classname SyncException
 * @Description TODO
 * @Date 2019/12/29 21:48
 * @Created by zengzg
 */
public class SyncException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //    private final List<Pair<String, Map<String, String>>> elements = new ArrayList<>();

    //    private Optional<Pair<String, Map<String, String>>> present = Optional.empty();

    private SyncErrorEnum errorCodEnum;

    public SyncException() {
    }

    public SyncException(String s) {
        super(s);
    }

    public SyncException(SyncErrorEnum errorCodEnum) {
        //        super(errorCodEnum.getErrorInfo());
        this.errorCodEnum = errorCodEnum;
    }

    public SyncException(SyncErrorEnum errorCodEnum, String s) {
        super(s);
        this.errorCodEnum = errorCodEnum;
    }

    public SyncException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SyncException(Throwable throwable) {
        super(throwable);
    }

    protected SyncException(String s, Throwable throwable, boolean flag, boolean flag1) {
        super(s, throwable, flag, flag1);
    }

    public SyncErrorEnum getErrorEnum() {
        return errorCodEnum;
    }

    private Pair<String, Integer> getErrorInfo() {
        StackTraceElement[] ste = this.getStackTrace();
        String errorInfo = "";
        String className = "";
        int lineNum = -1;
        if (ste.length > 0) {
            StackTraceElement el = ste[0];
            lineNum = el.getLineNumber();
            className = el.getClassName();
            String[] strings = className.split("\\.");
            errorInfo = (strings.length > 0 ? strings[strings.length - 1]
                    : el.getClassName() + "-" + el.getMethodName());
        }

        return Pair.of(errorInfo, lineNum);
    }

    public SyncErrorEnum getErrorCodEnum() {
        return errorCodEnum;
    }

    public void setErrorCodEnum(SyncErrorEnum errorCodEnum) {
        this.errorCodEnum = errorCodEnum;
    }

    public String getErrorCode() {

        try {
            Pair<String, Integer> errorInfo = getErrorInfo();
            return new String(Base64.getEncoder().encode(errorInfo.getLeft().getBytes())) + errorInfo.getRight();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * override to return errorCodEnum Message<br>
     *
     * @return String error Message
     */
    @Override
    public String getMessage() {
        return null != errorCodEnum ? errorCodEnum.getErrorInfo() + " , " + errorCodEnum.getSolution() : super.getMessage();
    }
}

