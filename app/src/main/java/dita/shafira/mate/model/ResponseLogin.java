package dita.shafira.mate.model;

public class ResponseLogin {
	private String msg;
	private Object errors;
	private Content content;
	private String status;

	public String getMsg(){
		return msg;
	}

	public Object getErrors(){
		return errors;
	}

	public Content getContent(){
		return content;
	}

	public String getStatus(){
		return status;
	}
}
