<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">栏目名</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="columnName" readonly/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">栏目Url</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="columnUrl" readonly/>
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
						<input type="checkbox" name="columnEnable"/>
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
					<!-- <textarea class="form-control" rows="8" name="remark" readonly></textarea> -->
					<div id="remark" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;">
						
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
