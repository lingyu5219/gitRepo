package com.center.vo.common;

public class JsonVo {
	private String name;
	private String value;
	
	public JsonVo() {
		super();
	}
	public JsonVo(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "JsonVo [name=" + name + ", value=" + value + "]";
	}
}
