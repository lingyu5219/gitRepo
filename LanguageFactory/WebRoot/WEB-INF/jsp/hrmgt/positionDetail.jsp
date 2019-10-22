<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">职位名称</label>
				<div class="col-md-10">
					<input type="text" readonly class="form-control" name="positionName"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<textarea readonly class="form-control" rows="3" name="remark"></textarea>
				</div>
			</div>
		</div>
	</div>
</form>