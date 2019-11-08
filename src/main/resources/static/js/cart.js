// alert($);
function operateBtns(value, row, index) {       //显示行操作按钮
    return [
        '<button id="updateCartItem" type="button" class="btn btn-default" >修改</button>',
        '<button id="deleteCartItem" type="button" class="btn btn-default">删除</button>',
    ].join('');
}

function sumResult(data) {      //可以计算列的数值
    field = this.field;
    return data.reduce(function(sum, row) {
        return sum + (+row[field]);
    }, 0);
}


//  显示弹框，并且将这一行的信息填入进去
function updateAmount(goodsId,goodsName,amount){
    $('#goodsId').val(goodsId);
    $('#goodsName').val(goodsName);
    $('#amount').val(amount);
    $('#updateContent').modal('show');
}

//  提交订单
function updateCartItem(){
    $.post({
        url:"/cart/updateCartItem",
        data:{
            "goodsId":$("#goodsId").val(),
            "amount":$("#amount").val()
        },
        error : function () {
            alert("插入出错");
        },
        success: function(data){
            if (data == "200"){
                alert("插入成功");
            }
            else{
                alert("插入失败");
            }
            // $('#addContent').modal('hide');
        }
    })
}

window.operateEvents = {            //行事件的监听，一个是直接删除，一个是打开修改的弹窗
   'click #deleteCartItem': function (e, value, row, index) {
       $.ajax({
           url: "/cart/deleteCartItem",
           data: {
               id:row.id
           },
           dataType: "json",
           async:true,
           success: function (cartData) {
               if (cartData != null) {
                   alert("删除成功");
               }else{
                   alert("删除失败");
               }
               window.location.href="/cart/page";
           }
       })
    },'click #updateCartItem': function (e, value, row, index) {
        updateAmount(row.goodsId,row.goodsName,row.amount);
    }
};

$(function ( ) {
    $('#cartTable').bootstrapTable('destroy');
    $('#cartTable').bootstrapTable({    //表格初始化
        url: '/cart/list',
        search: true,      //用于开启搜索框
        searchAlign: "left",
        buttonsAlign: "left",
        editable: true, //开启编辑模式 ,bootstrap 可编辑表格没有开
        clickToSelect: true,
        clickEdit: true,
        method: 'get',
        pageSize: 10,   //每页3条
        pageNumber: 1,  //第1页
        pageList: [8, 25],   //在使用过程中根据情况调整每页条数.虽然你现在定义的每页3条，但你可以随时调整为10条或25条。
        cache: false,   //不缓存
        pagination: true,
        sidePagination: 'client',   //设置分页是在客户端分页，设置成server就是在服务器端分页
        showRefresh: true,      //刷新表格按钮
        showExport: false,  //导出表格按钮，需要引用export.js
        showFooter: true,   //显示footer，底部提示栏
        showToggle: true,   //如果列数过多以至于超过屏幕的话会出现滚动条
        columns: [
            {field: 'id', title: '订单号', sortable: true, align:"center",edit: {required: true, type: 'text'}},
            {field: 'goodsId', title: '商品编号', sortable: true,visible:false, align:"center"},
            {field: 'goodsName', title: '名称', sortable: true, align:"center"},
            {field: 'goodsPrice', title: '单价', sortable: true, align:"center"},
            {field: 'amount', title: '数量', sortable: true,align:"center"},
            {field: 'totalPrice',title: '总价', align:"center",formatter:function(value, row, index){return row.goodsPrice*row.amount}},
            {field: 'Button', title: '操作', align:"center", events: operateEvents, formatter: operateBtns, width: 180}]
    });
    var  sumPrice = 0;

    $.ajax({
        url: "/cart/list",
        success: function (cartListData) {
            for(var k=1;k<cartListData.length;k++){
                // alert(cartListData[k].goodsPrice);
                sumPrice =sumPrice+parseInt(cartListData[k].goodsPrice)*parseInt(cartListData[k].amount);
            }
            $("#total_price").val(sumPrice);
        }
    })

})
