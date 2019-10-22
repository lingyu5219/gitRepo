package com.center.po.query;
/**
 * 
* ClassName: QueryCondition <br/>
* Function: 通用查询条件封装. <br/>
* Reason: TODO ADD REASON(可选). <br/>
* date: 2018年6月25日 下午3:18:47 <br/>
*
* @author Tony
* @version
 */
public class QueryCondition {  
  
    private Long    id;  
    
    private Integer draw;
    
    private Integer start;   
    
    private Integer length;  
    
    private Integer type;
    
    private String  startTime;
    
    private String  endTime;
    
    private String  keyword;
    
    private Integer status;
    
    //二维码专用
    private String path;
    
    private String qrcodePath;
    
    private String qrcodeUrl;
    
    public QueryCondition() {  
          
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getQrcodePath() {
		return qrcodePath;
	}

	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	@Override
	public String toString() {
		return "QueryCondition [id=" + id + ", draw=" + draw + ", start="
				+ start + ", length=" + length + ", type=" + type
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", keyword=" + keyword + ", status=" + status + ", path=" + path + ", qrcodePath=" + qrcodePath
				+ ", qrcodeUrl=" + qrcodeUrl + "]";
	}

} 