<!DOCTYPE html>
<html lang="en">
<%@ include file="../commonHeader.jsp"%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="peterli">
<style type="text/css">
.dataTables_info {
	width: 25%
}

.paging_full_numbers {
	width: 70%
}
</style>
<title>Data Table</title>
</head>
<body>
	<section class="panel">
		<!-- page start-->
		<s:if test="tableTitle != null && tableTitle.length() > 0">
			<header class="panel-heading">${tableTitle}</header>
		</s:if>
		<div class="panel-body">
			<form class="form-horizontal tasi-form">
			<div id="operationbutton"></div>
			</form>
		</div>
	</section>

	<div id="errorarea"><%@include file="../strutsMessage.jsp"%></div>
	<form id="exportForm" action="${actionPrex}/exportTable.action" method="post"></form>
	<div class="adv-table dataTables_wrapper form-inline" style="padding: 12px">
		<table id="${tableId}" class="table table-striped table-advance table-hover display  table-bordered">
		</table>
	</div>

	</div>
	</section>
	<!-- page end-->

<script type="text/javascript">
jQuery.fn.dataTableExt.oPagination.iFullNumbersShowPages = 1000;
var idName;
var tableId = "${tableId}";
var actionPrex = "${actionPrex}";
var cellFormatter = {};
window.exportExcel = false;
/*ALL OPTIONS*/
var options = {
    'edit':{
        'title':'Modify',
        'html': '<button title="Modify" style="margin-left:5px" class="btn btn-primary btn-xs" onclick="options[\'edit\'].onClick(this)"><i class="fa fa-pencil"></i></button>',
        'onClick' : function(button){
            var tableObj = $('#'+tableId).dataTable();
            var nTr = $(button).parents('tr')[0];
            var selectRowData =  tableObj.fnGetData( nTr );
            window.location = actionPrex + "/edit.action?${addButtonParameter}&id=" + selectRowData[idName];
        }
    },
    'delete': {
        'title':'Delete',
        'html' : '<button title="Delete" style="margin-left:5px" class="btn btn-danger btn-xs" onclick="options[\'delete\'].onClick(this)"><i class="fa fa-trash-o "></i></button>',
        'onClick' : function(button){
            if (confirm("Are you sure to delete it?")){
                var tableObj = $('#'+tableId).dataTable();
                var nTr = $(button).parents('tr')[0];
                var selectRowData =  tableObj.fnGetData( nTr );
                window.location = actionPrex + "/delete.action?${addButtonParameter}&id=" + selectRowData[idName]+"&ids=" + selectRowData[idName];
            }
        }
    }
}
/* Formating function for row details */
function fnFormatDetails ( oTable, nTr ){
    var aData = oTable.fnGetData( nTr );
    var aoColumns = oTable.fnSettings().aoColumns;
    var sOut = '<table cellpadding="5" cellspacing="0" border="0">';
    for(var i=0;i<aoColumns.length;i++){
        if(aoColumns[i].bVisible == false){
            sOut += '<tr><td>'+ aoColumns[i].sTitle+':</td><td>'+aData[aoColumns[i].mData]+'</td></tr>';
        }
    }
    sOut += '</table>';
    return sOut;
}

var operationButtons = [
    '<a class="btn btn-success" href="${actionPrex}/add.action?${addButtonParameter}"><i class="fa fa-plus"></i> Add </a>'
];



$(document).ready(function() {
    //intial opration button
    <s:if test="#session['sessionUser'].name=='admin'">
      $("#operationbutton").html(operationButtons.join("&nbsp;"));
    </s:if>
    $(".btn.btn-success").click(function(event){event.stopPropagation();});

    var foundSearch = false;
    <s:iterator value="#parameters">
    {
        var searcher  = $(".form-horizontal.tasi-form [name='<s:property value="key"/>']");
        searcher.val('<s:property value="value"/>');
        if(searcher.length>0){
            foundSearch = true;
        }
    }
    </s:iterator>
    if(foundSearch){   
        $('.form-horizontal.tasi-form .panel-heading').click();
    }

    var tableUrl = "${actionPrex}/initTable.action?${addButtonParameter}";
    var param = {};

    $.getJSON( tableUrl, param, function (initParam) {
        if(initParam.disableTools){
            $('#tableTools').css('display','none');
        }
        idName = initParam.idName;
        var columns = [];
        for(var i=0;i<initParam.aoColumns.length ; i++){
            if(typeof cellFormatter[initParam.aoColumns[i].mData] == "function"){
                initParam.aoColumns[i].mRender = cellFormatter[initParam.aoColumns[i].mData];
            }
            if(initParam.aoColumns[i].hiddenColumn != true){
                columns.push(initParam.aoColumns[i]);
            }
        }
        /*
         * Initialse DataTables, with no sorting on the 'details' column
         */

        window.oTable = $('#${tableId}').dataTable( {
            "bProcessing": initParam.bProcessing,
            "bServerSide": false,
             searching:true,
            "iDisplayLength":initParam.iDisplayLength,
            "aLengthMenu": [[ "10", "25", "50", "100","500"], ["10", "25", "50", "100", "500"]],
            "bStateSave": true, //save state that keep page in cookie.
            bSortClasses:false,
            "sPaginationType":'full_numbers',
            "aoColumns": columns,
            "sAjaxSource": "${actionPrex}/queryTable.action?${addButtonParameter}",
            //"sDom": '<"H"lT><"clear">rt<"F"ip>',
            "sDom": 'lfrtip',
            "fnDrawCallback": function ( oSettings ) {
              <s:if test="#session['sessionUser'].name=='admin'">
                // add options
                var totalOptions = 0;
                for(var p in options){
                    totalOptions++;
                }
                if(totalOptions > 0 && $('#${tableId} thead tr th:last[arias="options"]').length == 0){
                    $('#${tableId} thead tr').each( function () {
                        var thObj =document.createElement( 'th' );
                        thObj.setAttribute("arias","options");
                        thObj.innerHTML ="Operation";
                        $(this).append(thObj);
                        $(thObj).css({width:''+totalOptions*40+'px'});
                    } );
                }
                if(totalOptions > 0){
                    $('#${tableId} tbody tr').each( function (i) {
                        if($("td.operation",this).length==0){
                          var nCloneTd = $('<td class="operation"></td>');
                          $(this).append(nCloneTd);
                          for(var p in options){
                            $(nCloneTd).append(options[p].html);
                          }
                        }

                    });
                }
               </s:if>

            },
            "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
                /* //======= method one===========
                 // Add some extra data to the sender
                 aoData.push( { "name": "more_data", "value": "my_value" } );
                 $.getJSON( sSource, aoData, function (json) {
                 // Do whatever additional processing you want on the callback, then tell DataTables
                 fnCallback(json)
                 } );
                 //======= method one END=========== */

                //========method two==================
                var mDataObj = {};
                var sortObj = {};
                var iMax = 0;
                for(var n=0;n<aoData.length;n++){
                    if(aoData[n].name == "iColumns"){
                        iMax = aoData[n].value;
                    }
                    if(aoData[n].name == "mDataProp_0"){
                        for(var i = 0; i < iMax;i++){
                            mDataObj[aoData[n].name] = aoData[n].value;
                            n++;
                        }
                    }
                    if(aoData[n].name == "iSortCol_0"){
                        for(var i = 0; i < iMax;i++){
                            if(aoData[n].name == "iSortCol_"+i){
                                sortObj[mDataObj["mDataProp_"+ aoData[n].value]] = aoData[++n].value;
                                n++;
                            }else{
                                break;
                            }
                        }
                    }
                }
                for(var p in sortObj){
                    aoData.push( { "name": "sort['"+p+"']", "value": sortObj[p] } );
                }
                <s:iterator value="tableInit.aoColumns" var="column">
                <s:if test="%{#column.isbSearchable()==true}">
                if($('#${column.mData}').val() != ''){
                    aoData.push( { "name": "filter['${column.mData}']", "value": $('#${column.mData}').val() } );
                }
                </s:if>
                </s:iterator>

                if(window.exportExcel==true){
                    window.exportExcel = false;
                    var form = $("#exportForm");
                    //removed all hidden element in this exportForm.
                    form.empty();
                    for(var i =0;i< aoData.length;i++){
                        var hiddenElement = $("<input>");
                        hiddenElement.attr("type","hidden");
                        hiddenElement.attr("name",aoData[i].name);
                        hiddenElement.attr("value",aoData[i].value);
                        form.append(hiddenElement);
                    }
                    this.oApi._fnProcessingDisplay(oSettings,false);
                    form.submit();
                }else{
                    oSettings.jqXHR = $.ajax( {
                        "dataType": 'json',
                        "type": "POST",
                        "url": sSource,
                        "data": aoData,
                        "success": function(result,status,response){
                            // Do whatever additional processing you want on the callback, then tell DataTables
                            fnCallback(result);
                            $('#${tableId}').css("width","100%");
                            //from Dashboard parameters.
                            <s:if test="#parameters.search != null">
                              jQuery("#DeviceInfoAction_table_filter input").val('<s:property value="#parameters.search"/>');
                              jQuery("#DeviceInfoAction_table_filter input").keyup();
                            </s:if>
                        }
                    } );
                }
                //========method two END==================
            }
        });
        // end table.
         
    } );
} );
<s:if test="#session['sessionUser'].name=='admin'">
window.admin = true;
window.actionPrex = "${actionPrex}";
</s:if>
</script>
	<s:if test="customJs != null && customJs.length() > 0">
		<script src="${customJs}" type="text/javascript"></script>
	</s:if>

	<s:if test="customJsp != null && customJsp.length() > 0">
		<s:include value="%{customJsp}" />
	</s:if>

	<script>
    <s:if test="#session['backendSessionUser'].name=='admin'">
    operationButtons.push('<a class="btn btn-success" onclick=\'window.exportExcel=true;$("#${tableId}").dataTable()._fnAjaxUpdate()\'><i class="fa fa-plus"></i> 批量导出 </a>');
    </s:if>
    // format time
    var formatDateTime = function (obj, IsMi) {
        var myDate = new Date(obj);
        var year = myDate.getFullYear();
        var month = ("0" + (myDate.getMonth() + 1)).slice(-2);
        var day = ("0" + myDate.getDate()).slice(-2);
        var h = ("0" + myDate.getHours()).slice(-2);
        var m = ("0" + myDate.getMinutes()).slice(-2);
        var s = ("0" + myDate.getSeconds()).slice(-2);
        var mi = ("00" + myDate.getMilliseconds()).slice(-3);
        if (IsMi == true) {
            return year + "-" + month + "-" + day;
        }
        else {
            return year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s;
        }
    };

    // createTime  modifyTime
    cellFormatter["modifyTime"]=cellFormatter["createTime"] = function ( data, type, full ) {
        if(data!=null){
            return formatDateTime(data.time,false);
        }else{
            return "";
        }
    }

    <s:iterator value="tableInit.aoColumns" var="column">
        <s:if test="%{#column.cellFormatter!=null}">
            <s:property value="#column.cellFormatter"/>;
        </s:if>
    </s:iterator>
</script>
</body>
</html>