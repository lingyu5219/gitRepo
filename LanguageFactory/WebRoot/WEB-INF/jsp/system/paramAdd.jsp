<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form role="form" id="addForm" class="form-horizontal no-padding"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">参数类型</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="paramType" placeholder="请输入参数类型" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">参数代码</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="paramCode" placeholder="请输入参数代码" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">参数值</label>
				<div class="col-md-9">
					<!-- <textarea class="form-control" rows="3" name="paramName"
						placeholder="请输入参数值 ..."></textarea> -->
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
					<textarea class="form-control" rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-9 col-md-offset-3">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>
