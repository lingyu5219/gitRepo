<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="detailForm" class="form-horizontal no-padding" role="form" method="post">
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">宝宝姓名</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="stuName" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">性别：</label>
				<div class="col-md-8">
					<div class="switch">
						<input readonly class="switch-sex" type="checkbox" name="stuSex"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">民族：</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="stuNation"/>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">出生日期：</label>
				<div class="col-md-8">
					<input type="text" readonly class="form-control" name="stuBirthday"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">户籍地址</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="stuPermanentAddress" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">家庭地址</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="stuHomeAddress" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">独生子女</label>
				<div class="col-md-8">
					<div class="switch">
						<input readonly class="switch-sex" type="checkbox" name="stuSingle"/>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">既往病史</label>
				<div class="col-md-8">
					<div class="switch">
						<input readonly class="switch-sex" type="checkbox" name="stuMedicalHistory"/>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">病史详情</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="stuMedicalHistoryDetail" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">宝宝身份证</label>
				<div class="col-md-10">
					<input readonly type="text" class="form-control" name="stuIdCard" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">爸爸职业</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="stuFatherJob" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">爸爸电话</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="stuFatherPhone" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">妈妈职业</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="stuMotherJob" />
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">妈妈电话</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="stuMatherPhone" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="form-group">
				<label class="col-md-2 control-label">备注</label>
				<div class="col-md-10">
					<div id="remark" style="background:#eee; border:1px solid #D2D6DE; padding:6px 12px;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="form-group">
				<label class="col-md-4 control-label">创建时间</label>
				<div class="col-md-8">
					<input readonly type="text" class="form-control" name="createTime" />
				</div>
			</div>
		</div>
	</div>
</form>
