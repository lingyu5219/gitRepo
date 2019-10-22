<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">用户名</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="userName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">角色</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="roleName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea readonly class="form-control" rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>
