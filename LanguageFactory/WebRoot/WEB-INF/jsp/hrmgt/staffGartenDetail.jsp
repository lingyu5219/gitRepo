<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">名字</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="staffName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">出生日期</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="staffBirthday" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">电话号码</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="staffPhone"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">Email</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="staffEmail"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">职位</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="positionName"/>
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
					<input type="text" readonly class="form-control" name="staffGartenName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建人</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createByName">
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createTime">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<textarea class="form-control" readonly rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>