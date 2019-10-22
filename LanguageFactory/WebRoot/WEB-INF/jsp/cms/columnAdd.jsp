<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form"
	method="post">
	<input type="hidden" name="columnParentId" value="0"/>
	<input type="hidden" name="columnEnable" value="2"/>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">栏目名</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="columnName"
						placeholder="请输入栏目名" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">栏目Url</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="columnUrl"
						placeholder="请输入栏目Url" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">是否前端显示</label>
				<div class="col-md-10">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" id="columnEnable"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">栏目介绍</label>
				<div class="col-md-10">
					<!-- <textarea class="form-control" rows="8" name="remark"
						placeholder="请输入备注 ..."></textarea> -->
					<textarea id="remark" name="remark">请输入介绍</textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="col-md-10 col-md-offset-2">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>
