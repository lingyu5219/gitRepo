<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="addForm" class="form-horizontal no-padding" role="form"
	method="post">
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">资源名称</label>
				<div class="col-md-10">
					<input type="text" class="form-control" name="mediaTitle"
						placeholder="请输入资源名称" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">资源格式</label>
				<div class="col-md-8">
					<select class="form-control mediaPatternSelect2" name="mediaPattern" style="width: 100%">
			    		<option selected="selected" value="0">请选择资源格式</option>
			    		<option value="1">图片</option>
			    		<option value="2">视频</option>
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<!-- <label class="col-md-3 control-label">资源集 </label>
				<div class="col-md-9">
					<select class="form-control mediaAlbumIdSelect2" name="mediaAlbumId" style="width: 100%">
			    		<option selected="selected"></option>
					</select>
				</div> -->
				<label class="col-xs-12 col-md-3 control-label">所属集团</label>
				<div class="col-xs-3 col-md-3">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="isGroupMedia"/>
					</div>
				</div>
				<div class="col-xs-9 col-md-6">
					<select class="form-control mediaGartenIdSelect2" name="mediaGartenId" style="width: 100%">
			    		<!-- <option selected="selected"></option> -->
					</select>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">上传资源</label>
				<div class="col-md-8">
					<input type="hidden" name="mediaUrl" /> 
					<input type="hidden" name="mediaFileName" /> 
					<input type="hidden" name="mediaFileSize" value="0"/>
					<div id="fileList" class="uploader-list"></div>
					<a id="mediaFileBtn" class="btn btn-primary">选择文件</a>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">上传封面</label>
				<div class="col-md-9">
					<input type="hidden" name="mediaCoverUrl" /> 
					<div id="coverFileList" class="uploader-list"></div>
					<a id="coverFileBtn" class="btn btn-primary">选择文件</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">资源描述</label>
				<div class="col-md-10">
					<textarea class="form-control" rows="3" name="mediaDesc"
						placeholder="请输入资源描述 ..."></textarea>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">首页轮播</label>
				<div class="col-md-8">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="mediaIsHome" value="2" checked/>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-3 control-label">前端显示</label>
				<div class="col-md-9">
					<div class="switch" data-on-label="是" data-off-label="否">
						<input type="checkbox" name="mediaIsFront" value="2" checked/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-10 col-sm-offset-2">
			<div class="col-md-12">
				<button type="button"
					class="btn btn-flat btn-block btn-primary btn-save">保存</button>
			</div>
		</div>
	</div>
</form>
