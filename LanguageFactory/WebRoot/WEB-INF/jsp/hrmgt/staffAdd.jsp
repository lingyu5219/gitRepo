<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">名字</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="name" name="staffName"
						placeholder="请输入教职工名" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">出生日期</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control datepicker"
						id="birthday" name="staffBirthday" placeholder="请输入出生日期" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">电话号码</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="phone"
						name="staffPhone" placeholder="请输入电话号码" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">Email</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="email"
						name="staffEmail" placeholder="请输入Email" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">职位</label>
				<div class="col-md-9">
					<select class="form-control positionIdSelect2" name="positionId" style="width: 100%">
			    		<!-- <option selected="selected"></option> -->
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-xs-12 col-md-3 control-label">集团员工</label>
				<div class="col-xs-3 col-md-3">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="isGroupStaff"/>
					</div>
				</div>
				<div class="col-xs-9 col-md-6">
					<select class="form-control staffGartenIdSelect2" name="staffGartenId" style="width: 100%">
			    		<!-- <option selected="selected"></option> -->
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
			<div class="col-md-12">
				<button type="button" class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>