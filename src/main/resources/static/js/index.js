// alert($);
function operateBtns(value, row, index) {
    return [
        '<button id="addToCart" type="button" class="btn btn-default" >购买</button>',
        '<button id="deleteGoods" type="button" class="btn btn-default">删除</button>',
    ].join('');
}

function aFormatter(value ,row ,index){
    return [
        '<a href="#">商品详情</a>'
    ].join('');
}
function refresh(){
    $.ajax({
        url:"/cart/list",
        success: function(data){
            alert(data);
            return data;
        }
    })
}

//  显示弹框，并且将这一行的信息填入进去
function addOrder(goodsId,goodsPrice,goodsName){
    $('#goodsId').val(goodsId);
    $('#goodsName').val(goodsName);
    $('#goodsPrice').val(goodsPrice);
    $('#addContent').modal('show');
}

//  提交订单
function addCartItem(){
    $.post({
        url:"/Goods/addToCart",
        data:{
            "goodsId":$("#goodsId").val() ,
            "goodsName":$("#goodsName").val(),
            "goodsPrice":$("#goodsPrice").val(),
            "amount":$("#amount").val()
        },
        error : function () {
            alert("插入出错");
            window.location.href="/";
        },
        success: function(data){
            if (data == "200"){
                alert("插入成功");
                window.location.href="/";
            }
            else{
                alert("插入失败");
            }
            // $('#addContent').modal('hide');
        }
    })
}

window.operateEvents = {
    'click #deleteGoods': function (e, value, row, index) {
        $.ajax({
            url: "/goods/deleteGoods",
            data: {
                goodsId:row.goodsId
            },
            dataType: "json",
            async:true,
            success: function (goodsData) {
                if (goodsData != null) {
                    alert("删除成功");
                }else{
                    alert("删除失败");
                }
            }
        })
    },'click #addToCart': function (e, value, row, index) {
        addOrder(row.goodsId,row.goodsPrice,row.goodsName);
    }
};

$(function ( ) {
    $('#goodsTable').bootstrapTable('destroy');
    $('#goodsTable').bootstrapTable({//表格初始化
        url: '/Goods/list',
        search: true,
        searchOnEnterKey: true,
        searchAlign: "left",
        buttonsAlign: "left",
        editable: true,//开启编辑模式
        clickToSelect: true,
        clickEdit: true,
        method: 'get',
        pageSize: 10, //每页3条
        pageNumber: 1,  //第1页
        pageList: [8, 25],   //在使用过程中根据情况调整每页条数.虽然你现在定义的每页3条，但你可以随时调整为10条或25条。
        cache: false,   //不缓存
        striped: true,
        pagination: true,
        sidePagination: 'client',
        showRefresh: true,
        showExport: false,
        showFooter: true,
        showToggle: true,
        columns: [
            {field: 'goodsId', title: '商品号', sortable: true, align:"center",edit: {required: true, type: 'text'}},
            {field: 'type', title: '类型', sortable: true, align:"center"},
            {field: 'goodsName', title: '名称', sortable: true, align:"center"},
            {field: 'goodsPrice', title: '价格', sortable: true, align:"center"},
            {field: 'Button', title: '操作', align:"center", events: operateEvents, formatter: operateBtns, width: 180},
            {field: 'operate',title: '详情', align:'center', valign:'middle',formatter:aFormatter,width:100}]
    });
})







