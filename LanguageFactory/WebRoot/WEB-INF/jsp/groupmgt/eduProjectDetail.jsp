<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">所属幼儿园</label>
				<div class="col-md-8">
					<input type="text" class="form-control" readonly id="gartenName"
						name="gartenId" placeholder="请输入所属幼儿园" />
				</div>
			</div>
		</div> -->
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">项目名称</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="eduProjectName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">描述</label>
				<div class="col-md-9">
					<textarea class="form-control" readonly rows="3" name="description" placeholder="请输入描述"></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">创建人</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createByName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="createTime"/>
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">备注</label>
				<div class="col-md-8">
					<textarea class="form-control" readonly rows="3" name="remark"
						placeholder="请输入备注 ..."></textarea>
				</div>
			</div>
		</div> -->
	</div>
</form>