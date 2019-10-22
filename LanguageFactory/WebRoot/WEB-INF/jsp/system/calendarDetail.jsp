<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/meta.jsp"></jsp:include>
<form id="detailForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">日期</label>
				<div class="col-md-9">
					<input type="text" readonly name="calendarDate"
						class="from-control  form-control" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">是否需要上课</label>
				<div class="col-md-9">
					<input type="text" readonly id="calendarType" name="calendarType"
						class="from-control  form-control" />
						<script type="text/javascript">
						$(function(){ 
							var calendarType=$("#calendarType").val() ;
							if(calendarType===0){
								$("#calendarType").val('否');
							}else{
								$("#calendarType").val('是');
							}
						}); 
						</script>
				</div>
			</div>
		</div>

	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<input type="text" readonly name="calendarDescribe"
						class="from-control  form-control" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea readonly class="form-control" rows="3" id="remark"
						name="remark" placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
</form>
