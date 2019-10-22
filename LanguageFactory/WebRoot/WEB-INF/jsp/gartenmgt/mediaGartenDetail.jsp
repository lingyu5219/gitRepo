<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">资源名称</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="mediaTitle"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">所属校园</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="mediaGartenName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">资源格式</label>
				<div class="col-md-9">
					<select readonly class="form-control mediaPatternSelect2" name="mediaPattern" style="width: 100%">
			    		<option value="1">图片</option>
			    		<option value="2">视频</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建人</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="createByName"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">前端显示</label>
				<div class="col-md-9">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="mediaIsFront"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">资源</label>
				<div id="media" class="col-md-10">
					
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">资源描述</label>
				<div class="col-md-10">
					<div id="mediaDesc" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建时间</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="createTime"/>
				</div>
			</div>
		</div>
	</div>
</form>
