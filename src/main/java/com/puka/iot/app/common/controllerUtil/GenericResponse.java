package com.puka.iot.app.common.controllerUtil;
import java.io.Serializable;
import java.util.Objects;

import net.sf.json.JSONObject;

/**
 * 统一JSON返回类
 */
public class GenericResponse implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = -6904294818923653183L;
	
	/**
     * 程序定义状态码
     */
    private int code;
    
    /**
     * 必要的提示信息
     */
    private String message;
    
    /**
     * 业务数据
     */
    private Object datas;
    
    public GenericResponse() {
	}
    
    public GenericResponse(int code, String message, Object datas) {
		super();
		this.code = code;
		this.message = message;
		this.datas = datas;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	/**
     * 对业务数据单独处理
     * @return
     */
    @Override
    public String toString() {
        if(Objects.isNull(this.datas)){
            this.setDatas(new Object());
        }
        return JSONObject.fromObject(this).toString();
    }
}