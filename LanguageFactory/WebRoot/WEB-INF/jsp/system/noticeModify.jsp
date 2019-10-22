<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="updateForm" class="form-horizontal no-padding" role="form" method="post">
	<input type="hidden" name="noticeId" />
	<input type="hidden" name="noticeState" />
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">标题</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="noticeTitle"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">内容</label>
				<div class="col-md-10">
					<textarea id="noticeContent" name="noticeContent"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<textarea class="form-control" rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-10 col-md-offset-2">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>
