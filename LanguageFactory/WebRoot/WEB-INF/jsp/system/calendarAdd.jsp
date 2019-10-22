<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/meta.jsp"></jsp:include>
<form id="addForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">输入日期</label>
				<div class="col-md-10">
					<div class="input-daterange input-group" id="datepicker">
						<input type="text" readonly class="form-control" id=time1
							name="time1" placeholder="请输入开始日期" /> <span
							class="input-group-addon">to</span> <input type="text" readonly
							class="form-control" id=time2 name="time2" placeholder="请输入结束日期" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">输入描述</label>
				<div class="col-md-8">
					<input type="text" name="calendarDescribe"
						class="from-control  form-control" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">是否需要上课</label>
				<div class="col-md-8">
					<select id="calendarType" name="calendarType" class="form-control">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="col-md-4 col-md-offset-8">
					<button type="button"
						class="btn btn-flat btn-block btn-primary btn-save">保存</button>
				</div>
			</div>
		</div>
	</div>
</form>
