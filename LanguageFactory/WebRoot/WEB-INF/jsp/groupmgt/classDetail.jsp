<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">所属幼儿园</label>
				<div class="col-xs-9 col-md-8">
					<input type="text" readonly class="form-control" id="gartenName" name="gartenName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">班级类型</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" id="classTypeName" name="classTypeName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">班级名称</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" id="className" name="className"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">入学年份</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control datepicker" id="year" name="year" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">限制名额</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control datepicker" id="capacity" readonly name="capacity" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">现有人数</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control datepicker" id="currentQuantity" readonly name="currentQuantity" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建人</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" name="createByName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建时间</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" name="createTime"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">描述</label>
				<div class="col-md-8">
					<textarea class="form-control" readonly rows="3" name="description" placeholder="请输入描述"></textarea>
				</div>
			</div>
		</div>
		<!-- <div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">备注</label>
				<div class="col-md-8">
					<textarea class="form-control" readonly rows="3" name="remark"></textarea>
				</div>
			</div>
		</div> -->
	</div>
</form>