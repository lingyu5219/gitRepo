<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="updateForm" class="form-horizontal no-padding" role="form"
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
					<select id="calendarType" name="calendarType" class="form-control">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>

				</div>
			</div>
		</div>

	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<input type="text" name="calendarDescribe"
						class="from-control  form-control" placeholder="请输入描述 ..." />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" id="remark" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="col-md-9 col-md-offset-3">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>
