package net.kyong.complaint.common;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 视图层返回数据类对象
 * @param <T>
 */
@Slf4j
public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = 7251433606219732795L;
	private Integer code = 0;
	private String msg = "请求成功";
	private T result;

	public ResultModel<T> err401(ResultModel<T> resultModel, String msg) {
		error(resultModel, Constants.LOGIN_EXPIRE_EXCEPTION_CODE, msg);
		return resultModel;
	}

	public void err500(ResultModel<T> resultModel, Exception e) {
		error(resultModel, Constants.SERVER_EXCEPTION_CODE, Constants.SERVER_EXCEPTION_MSG, e);
	}

	public ResultModel<T> err503(ResultModel<T> resultModel, String msg) {
		error(resultModel, Constants.OPERATE_EXCEPTION_CODE, msg);
		return resultModel;
	}

	public ResultModel<T> err505(ResultModel<T> resultModel, String msg) {
		error(resultModel, Constants.EXCEL_EXCEPTION_CODE, msg);
		return resultModel;
	}

	public ResultModel<T> err506(ResultModel<T> resultModel, String msg) {
		error(resultModel, Constants.VALIDATION_EXCEPTION_CODE, msg);
		return resultModel;
	}

	public ResultModel<T> err508(ResultModel<T> resultModel, String msg) {
		error(resultModel, Constants.CONNECT_EXCEPTION_CODE, msg);
		return resultModel;
	}

	public ResultModel<T> error(ResultModel<T> resultModel, Integer code, String msg) {
		resultModel.setCode(code);
		resultModel.setMsg(msg);
		log.info(resultModel.toString());
		return resultModel;
	}

	public void error(ResultModel<T> resultModel, Integer code, String msg, Exception e) {
		resultModel.setCode(code);
		resultModel.setMsg(msg);
		log.info(resultModel.toString(), e);
	}

	public void ok(ResultModel<T> resultModel, String msg) {
		resultModel.setMsg(msg);
		log.info(resultModel.toString());
	}

	public Integer getCode() {

		return code;
	}

	public void setCode(Integer errCode) {

		this.code = errCode;
	}

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg == null ? "" : msg.trim();
	}

	public T getResult() {

		return result;
	}

	public void setResult(T result) {

		this.result = result;
	}

	@Override
	public String toString() {
		return "ResultModel [code=" + code + ", msg=" + msg + ", result=" + result + "]";
	}

}
