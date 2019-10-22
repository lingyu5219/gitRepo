<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form role="form" id="updateForm" class="form-horizontal no-padding"
	method="post">
	<input type="hidden" name="paramId" />
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">参数类型</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="paramType"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">参数代码</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="paramCode"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">参数值</label>
				<div class="col-md-9">
					<!-- <textarea class="form-control" rows="3" name="paramName"></textarea> -->
					<!-- <input type="text" class="form-control" name="paramName"/> -->
					<textarea id="paramName" name="paramName">请输入参数值</textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-9 col-md-offset-3">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-update">保存</button>
			</div>
		</div>
	</div>
</form>
