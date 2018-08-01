<#if (totalNum>pageVolume)>
<div class="scott" id="divId">
	<#list pageNums as num>
	    <#if num==curPageNum>
         <a href="javascript:;" class="hover">${num}</a>
         <#else>
         <a href="javascript:slipt_${formId}(${num});">${num}</a>
        </#if>
	</#list >
	<input type="text" onkeypress="directGoto_${formId}(event,this,this.value);" class="txt" size="3">
	${curPageNum}/${totalPageNum}页	
</div>
</#if>
<form action="${url}" onkeypress="return false"  method="post" id="${formId}" name="${formId}">
<input type="hidden" name="pageNo" id="curPageNum_${formId}" value="${curPageNum}" control="default">	
<#list param as tagParam>
	<input type="hidden" name="${tagParam.name}" value="${tagParam.value}" control="default">
</#list>
</form>	
<script type="text/javascript">	
	function directGoto_${formId}(e,obj,page){
		var regx=/^[1-9][0-9]*$/;
		var e=window.event||e;
		if(e.keyCode==13){
			if(!regx.test(page)){
				alertTip("请您输入页数!","pageNumError");
				obj.value="";
				return false;
			}
			if(page>2147483647){
				page=2147483647;
			}
			slipt_${formId}(page);
		}
	}
    function slipt_${formId}(page)
    {	
    	if(page==document.getElementById("curPageNum_${formId}").value)
    		return;
        if(page==0)
           return false;
        else
        {
           jQuery("#curPageNum_${formId}").attr("value",page);
           redirect_${formId}();
        }
    }
	function redirect_${formId}(){   
		<#if !divId??>
			document.getElementById('${formId}').submit();
		<#else>
		var param=jQuery("#${formId}").serialize();
		//jQuery("#${divId}").html("<img src='${ctx}/images/loading.gif'/>");
		jQuery("#${divId}").html("");
		jQuery.ajax({
			url:'${url}',
			type:'post',
			data:param,
			success:function(data){
					jQuery("#${divId}").html(data);
			}
		});
		</#if>	
	}
</script>