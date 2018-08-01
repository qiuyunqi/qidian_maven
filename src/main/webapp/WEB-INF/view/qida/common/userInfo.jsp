<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- 弹窗 -->
<div class="userInfoComtent">
	<div class="inforBod">
		<div class="inforBody-gz">
			<div class="information-tox">
				<img class="userAvatar" src="${ctx}/web/images_qiDa/defTx.jpg" />
			</div>
			<a class="user-care">+关注</a>
		</div>
		<div class="userInfo-body">
			<div class="userInfo-text">
				<div class="chartNa defaultCol"><span class="nickName"></span></div>
				<div class="chartName-jifen smalSize">积分：<span class="integral"></span></div>
				<div class="chartName-jifen smalSize">用户级别：<span class="qidaRank"></span></div>
			</div>
			<div class="userInfo-else smalSize">
				<span class="userInfo-fens ">粉丝数<a class="beconNum"></a>人</span>
				<span class="userInfo-care">关注<a class="conNum"></a>人</span>
				<p class="userInfo-answer">已提供<a class="answerNum"></a>个答案，收到<a class="questionNum"></a>个邀请</p>
			</div>
		</div>
	</div>
	<p class="userInfo-p introduction"></p>
	<i class="card-to"></i>
</div>
<script>
var userTip = $(".userInfoComtent").html();
var allUser = "<div class='userInfoComtent' onmouseover='childMousove($(this))'  onmouseout='childMousout($(this))'>"+userTip+"</div>";
	function mousove(thsi,numb,lft,id){
	var posit = thsi.css("position");
	var fal = thsi.find("div").hasClass("userInfoComtent");
	if(posit != "relative" && fal == false){
		thsi.append(allUser);
		thsi.css("position","relative");
		thsi.find(".userInfoComtent").show();
		thsi.find(".userInfoComtent").css("top",numb+"px");
		thsi.find(".userInfoComtent").children(".card-to").css("left",lft+"px");
		}else if(posit != "relative" && fal == true){
			thsi.css("position","relative");
			thsi.find(".userInfoComtent").show();
			thsi.find(".userInfoComtent").css("top",numb+"px");
			thsi.find(".userInfoComtent").children(".card-to").css("left",lft+"px");
		}
		if(id==${fuUser.id}){
			$(".user-care").hide();
		}else{
			$(".user-care").show();
		}
		$.post("${ctx}/web/qida/userInfo.html",{id:id},function(data){
			if(data.userAvatar!=null){
				$(".userAvatar").attr("src",data.userAvatar);
			}else{
				$(".userAvatar").attr("src","${ctx}/web/images_qiDa/defTx.jpg");
			}
			$(".nickName").text(data.nickName);
			$(".integral").text(data.integral);
			$(".qidaRank").text(data.qidaRank);
			$(".beconNum").text(data.beconNum);
			$(".conNum").text(data.conNum);
			$(".answerNum").text(data.answerNum);
			$(".questionNum").text(data.questionNum);
			$(".introduction").text(data.introduction);
			if(data.res==1){
				$(".user-care").text("已关注");
				$(".user-care").attr("onclick","");
			}
			if(data.res==0){
				$(".user-care").text("+关注");
				$(".user-care").attr("onclick","concern("+id+")");
			}
		},"json")
	};
	
	function mousout(thsi){	
		var posit = thsi.css("position");
		if(posit == "relative"){
        thsi.find(".userInfoComtent").hide();
        thsi.css("position","");
		}
	};
	
	function concern(id){
		$.post("${ctx}/web/ai/concern.html",{id:id},function(data){
			if(data.message=="0"){
				globalTip("关注失败!");
				return false;
			}
			if(data.message=="1"){
				globalTip("关注成功!");
				$(".user-care").text("已关注");
				$(".user-care").attr("onclick","");
			}
		},"json")
	}
	
	function childMousove(thsi){
		thsi.show();
	}
	
	function childMousout(thsi){
		thsi.hide();
	}
</script>