<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="common/meta.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			
			
			//调用接口获取校园信息
			initBasicSelect2({
				url: 'http://47.97.191.74:8080/tbrk_education/merchantInfo/selectListByGroupId.do',
				params: {groupMerchantInfoId:1},
				dataType: "jsonp",
				callback: function(rs){
					var data = rs.list;
					$("select.merchantInfoIdSelect2").empty()
					var option = $("<option value=\"0\" selected=\"selected\">请选择校园</option>");
					$("select.merchantInfoIdSelect2").append(option);
					/* var option1 = $("<option value=\"111001\">天府民居人之初幼儿园</option>");
					$("select.merchantInfoIdSelect2").append(option1); */
		        	for(var i = 0; i < data.length; i++){
    					var opt = $("<option value=\"" + data[i].id + "\">" + data[i].showName + "</option>");
    					$("select.merchantInfoIdSelect2").append(opt);
		    		}
					$("select.merchantInfoIdSelect2").trigger("change");
		        }
			});
			$("select.merchantInfoIdSelect2").on('change', function (e) {
    			//var merchantInfoId = parseInt($(this).val());
    			var merchantInfoId = $(this).val();
    			if(merchantInfoId == 0){
    				$("select.gradeWillBeSelect2").empty()
					var option = $("<option value=\"0\" selected=\"selected\">请选择年级</option>");
					$("select.gradeWillBeSelect2").append(option);
					return;
    			}
    			//获取首字母
    			$.ajax({
					type : 'post',
					url : 'http://47.97.191.74:8080/tbrk_education/merchantInfo/selectInfoById.do',
					data : {merchantInfoId : merchantInfoId},
					dataType : "jsonp",
					cache : "false",
					success : function(rs) {
						$("input[name='firstLetter']").val(rs.firstLetter);
					}
				});
    			//获取年级数据
    			initBasicSelect2({
    				url: 'http://47.97.191.74:8080/tbrk_education/teachingInstitutions/selectGradeListNoPage.do',
    				params: {merchantInfoId:merchantInfoId},
    				dataType: "jsonp",
    				callback: function(rs){
    					var data = rs.list;
    					$("select.gradeWillBeSelect2").empty()
    					var option = $("<option value=\"0\" selected=\"selected\">请选择年级</option>");
    					$("select.gradeWillBeSelect2").append(option);
    		        	for(var i = 0; i < data.length; i++){
	    					var opt = $("<option value=\"" + data[i].id + "\">" + data[i].gradeName + "</option>");
	    					$("select.gradeWillBeSelect2").append(opt);
    		    		}
    					//.trigger("change")
    		        }
    			});
			});
			
			/* $("select.gradeWillBeSelect2").on('change', function (e) {
				
			}); */
			
			
			//初始化bootstrap-switch开关控件
    		$("input[name='sex']").bootstrapSwitch({
				state:true,    			
    			onColor:"primary",
    			offColor:"warning",
    			onText:"男",  
    	        offText:"女",
    	        labelText:"<i class=\"fa fa-user\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
   	            	$(this).prop("checked",true);
    	        }
    		});
    		$("input[name='leftBehindChildrenFlag']").bootstrapSwitch({
				state:false,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-child\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		$("input[name='disabledChildrenFlag']").bootstrapSwitch({
    			state:false,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-child\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		$("input[name='parentTrailingChildFlag']").bootstrapSwitch({
    			state:false,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-child\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		$("input[name='orphanFlag']").bootstrapSwitch({
    			state:false,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-child\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		$("input[name='lowAllowanceFlag']").bootstrapSwitch({
    			state:false,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-medkit\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		$("input[name='staffChildFlag']").bootstrapSwitch({
    			state:false,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-child\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("0");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		/* $("input[name='stuSingle']").bootstrapSwitch({
				state:true,
    			onText:"是",  
    	        offText:"否",
    	        labelText:"<i class=\"fa fa-child\"></i>",
    			onSwitchChange:function(event,state){  
    	            if(state){  
    	            	$(this).val("1");
    	            }else{  
    	            	$(this).val("2");
    	            }  
    	            $(this).prop("checked",true);
    	        }
    		});
    		$("input[name='stuMedicalHistory']").bootstrapSwitch({
				state:false,
    			onText:"有",  
    	        offText:"无",
    	        labelText:"<i class=\"fa fa-medkit\"></i>",
    			onSwitchChange:function(event,state){  
   	            	$("input[name='stuMedicalHistoryDetail']").val("");
    	            if(state){  
    	            	$(this).val("1");
    	            	$("input[name='stuMedicalHistoryDetail']").removeClass("hide");
    	            	$("input[name='stuMedicalHistoryDetail']").rules("add",{required:true});
	   	            	$("input[name='stuMedicalHistoryDetail']").valid();
    	            }else{  
    	            	$(this).val("2");
    	            	$("input[name='stuMedicalHistoryDetail']").rules("remove");
	   	            	$("input[name='stuMedicalHistoryDetail']").valid();
    	            	$("input[name='stuMedicalHistoryDetail']").addClass("hide");
    	            } 
    	            $(this).prop("checked",true);
    	        }
    		}); */
			//初始化日期控件
    		$(".datepicker").datepicker({
    			format : "yyyy-mm-dd",
    			weekStart : 1,
    			language : "zh-CN",
    			autoclose : true,
    			todayHighlight : true
    		});
			
    		$("#signupForm").validate({
    			errorElement : "em",
				errorClass:"has-error",
				onsubmit:false,//关闭在提交表单时校验，在需要校验时，手动调用valid方法校验
				rules: {
					merchantInfoId:{
						selectRequired: true
					},
					gradeWillBe:{
						selectRequired: true
					},
					name: {
						required: true,
						isChinese: true
					},
					sex: {
						required: true
					},
					nationality: {
						isChinese: true
					},
					birthdayStr:{
						required: true
					},
					censusRegisterLocation:{
						required: true
					},
					homeAddress:{
						required: true
					},
					idCard:{
						required: true,
						isIdCard: true/* ,
						isUnique: {
							tableName:"tb_group_student", 
							queryFieldList:function(value, element){
								return [{fieldName:"stuIdCard",operator:"=", fieldValue:"'" + value +"'"}];
							}
						} */
					},
					phone:{
						required: true,
						isTelOrPhone: true
					},
					/* stuMatherPhone:{
						required: true,
						isTelOrPhone: true
					}, */
					verifyCode:{
						required: true,
						kaptcha: true
					}
				},
				errorPlacement: function(error, element) {  
					error.appendTo(element.parent());
				}
			});
    		
    		$("#kaptchaImage").click(function(){
    			var time = new Date().getTime();
                $(this).attr("src",basePath + "kaptcha?d=" + time);
    		});
    		
    		$("#kaptchaImage").tooltip();
    		
    		$(".btn-save").click(function(){
				var validResult = $("#signupForm").valid();
    			if(validResult){
    				//获取表单数据，此处可自行获取表单数据
    				var param = formToJson($("#signupForm"));
    				save({
    					type: "post",
    					data: param,
    					dataType: "jsonp",
    					url: "http://47.97.191.74:8080/tbrk_education/user/addSignUpPeopleMobile.do",
    					success:function(result){
    						if(result.code == "0000"){
	    						tcAlert2(result.message,"报名码为： " + result.signUpNo,function(){
	    							window.location.reload();
	    						});
    						} else {
    							tcAlert(result.message,function(){
    								$("#kaptchaImage").click();
    							});
    						}
    						
    					}
    				});
    			}
			});
		});
	</script>
</head>
<body class="hold-transition skin-custom layout-top-nav">
	<div class="wrapper">
		<jsp:include page="template/topNav.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<jsp:include page="template/carousel.jsp"></jsp:include>
			<section class="parent-service">
				<div class="gap hidden-xs hidden-sm"></div>
				<div class="container">
					<div class="gap hidden-xs hidden-sm"></div>
					<div class="row">
						<div class="col-md-4">
							<jsp:include page="template/parentServiceSide.jsp"></jsp:include>
						</div>
						<div class="col-md-8">
							<form id="signupForm" class="form-horizontal no-padding greenForm" role="form" method="post">
								<input type="hidden" name="makeRecordFlag" value="0"/>
								<input type="hidden" name="recordDelFlag" value="0"/>
								<input type="hidden" name="firstLetter"/>
								
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">校园：</label>
											<div class="col-xs-8 col-md-6">
												<select class="form-control merchantInfoIdSelect2" id="merchantInfoId" name="merchantInfoId" style="width: 100%">
													<option selected="selected" value="0">请选择校园</option>
												</select>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">年级：</label>
											<div class="col-xs-8 col-md-6">
												<select class="form-control gradeWillBeSelect2" id="gradeWillBe" name="gradeWillBe" style="width: 100%">
													<option selected="selected" value="0">请选择年级</option>
												</select>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">宝宝姓名：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control" name="name" placeholder="请输入宝宝姓名" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">性别：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="sex" value="1" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">国籍：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control" name="nationality" placeholder="请输入国籍" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">出生日期：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" readonly class="form-control datepicker"
												name="birthdayStr" placeholder="请选择出生日期" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">宝宝身份证：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="idCard" placeholder="请输入宝宝身份证号" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">联系电话：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="phone" placeholder="请输入联系电话" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">户籍地址：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="censusRegisterLocation" placeholder="请输入户籍详细地址" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">家庭地址：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="homeAddress" placeholder="请输入家庭详细地址" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<!-- <div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 control-label">独生子女：</label>
											<div class="col-xs-6">
												<div class="switch">
													<input class="switch-single" type="checkbox" name="stuSingle" value="1" checked="checked"/>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">既往病史：</label>
											<div class="col-xs-8 col-md-2">
												<div class="switch">
													<input class="switch-medical" type="checkbox" name="stuMedicalHistory" value="2" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-4 visible-xs-inline visible-sm-inline"></div>
											<div class="col-xs-8 col-md-4">
												<input type="text" class="form-control hide" 
												name="stuMedicalHistoryDetail" placeholder="如有，请填写病史" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div> -->
								
								<!-- <div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">爸爸职业：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="stuFatherJob" placeholder="请输入宝宝爸爸的职业" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">爸爸电话：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="stuFatherPhone" placeholder="请输入爸爸联系电话" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">妈妈职业：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="stuMotherJob" placeholder="请输入宝宝妈妈的职业" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">妈妈电话：</label>
											<div class="col-xs-8 col-md-6">
												<input type="text" class="form-control"
												name="stuMatherPhone" placeholder="请输入妈妈联系电话" />
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div> -->
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">留守儿童：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="leftBehindChildrenFlag" value="0" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">残疾儿童：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="disabledChildrenFlag" value="0" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">随迁子女：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="parentTrailingChildFlag" value="0" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">是否孤儿：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="orphanFlag" value="0" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">教职工子女：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="staffChildFlag" value="0" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">是否低保：</label>
											<div class="col-xs-6 col-md-6">
												<div class="switch">
													<input class="switch-sex" type="checkbox" name="lowAllowanceFlag" value="0" checked="checked"/>
												</div>
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">
											*
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">备注：</label>
											<div class="col-xs-8 col-md-6">
												<textarea rows="10" class="form-control" name="remarks" placeholder="请输入备注信息"></textarea>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-xs-4 col-md-4 control-label">验证码:</label>
											<div class="col-xs-4 col-md-3">
												<input type="text" class="form-control" name="verifyCode" placeholder="请输入验证码" />
											</div>
											<div class="col-xs-4 col-md-3">
												<img src="${basePath}kaptcha" id="kaptchaImage" class="kaptcha-img" data-toggle="tooltip" data-placement="top" title="看不清点击更换">
											</div>
											<div class="col-xs-12 col-md-2 error-info hidden-xs hidden-sm">*</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-8 col-xs-offset-4">
										<input type="button" class="col-xs-3 btn btn-success btn-save" value="提交"/>
										<input type="reset" class="col-xs-3 col-xs-offset-2 btn btn-success" value="重置"/>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="gap"></div>
			</section>
			<jsp:include page="template/sideBar.jsp"></jsp:include>
		</div>
		<jsp:include page="template/footer.jsp"></jsp:include>
	</div>
</body>
</html>