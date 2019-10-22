<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">用户名</label>
				<div class="col-md-9">
					<input type="text" class="form-control" name="userName"
						placeholder="请输入账号" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">密码</label>
				<div class="col-md-9">
					<input type="password" class="form-control" name="userPassword"
						placeholder="******" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">角色</label>
				<div class="col-md-9">
					<select class="form-control roleIdSelect2" name="roleId" style="width: 100%">

					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">关联员工</label>
				<div class="col-md-9">
					<select class="form-control staffIdSelect2" name="staffId" style="width: 100%">

					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="col-md-9 col-md-offset-3">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>
