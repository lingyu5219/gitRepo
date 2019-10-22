<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">标题</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" id="noticeTitle"
						name="noticeTitle" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建者</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" id="createBy"
						name="createBy" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">备注</label>
				<div class="col-md-8">
					<textarea readonly class="form-control" rows="3" id="remark"
						name="remark"></textarea>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">创建时间</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" id="createTime"
						name="createTime" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">内容</label>
				<div class="col-md-10">
					<div id="noticeContent" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
				</div>
			</div>
		</div>
	</div>
</form>
