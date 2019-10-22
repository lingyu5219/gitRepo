<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">历程标题</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="historyTitle"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">历程日期</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="historyDate" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">历程内容</label>
				<div class="col-md-9">
					<textarea class="form-control" readonly rows="3" name="historyContent" placeholder="请输入历程内容"></textarea>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">备注</label>
				<div class="col-md-9">
					<input type="text" readonly class="form-control" name="remark"/>
				</div>
			</div>
		</div>
	</div>
</form>