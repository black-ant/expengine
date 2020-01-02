package com.gang.etl.common.output.sdk;

/**
 * @Classname outputAPI
 * @Description TODO
 * @Date 2019/12/8 17:30
 * @Created by zengzg
 */
public interface OutputAPI<T, D> {

    public T output(D source, OutputConfig outputConfig);
}
