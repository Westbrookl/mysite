/**
 * Tale的全局函数对象 var tale = new $.tale()
 */
$.extend({
    tale:function(){

    }
});
/**
 * 错误提示
 * @param options
 */
$.tale.prototype.alertError =  function(options){
    options = options.length ? {text:options} :(options || {});
    options.title = options.title || "错误信息";
    options.showCancleButton = true;
    options.type = "question";
    this.alertBox(options);
}
$.tale.prototype.alertOK = function(options){
    options = options.length?{text:options}:(options||{});
    options.title = options.title ||'操作成功';
    options.text = options.text;
    options.showCancelButton = false;
    options.showCloseButton = false;
    options.type = 'success';
    this.alertBox(options);
}
/**
 * 公共弹框
 * @param options
 */

$.tale.prototype.alertBox = function(options){
    swal({
        title:options.title,
        text :options.text,
        type:options.type,
        timer:options.timer||9999,
        showCloseButton:options.showCloseButton,
        showCancelButton:options.showCancelButton,
        showLoaderOnConfirm:options.showLoaderOnConfirm||false,
        confirmButtonColor:options.confirmButtonColor||'#3085d6',
        cancelButtonColor:options.cancelButtonColor||'#d33',
        confirmButtonText:options.confirmButtonText||'确定',
        cancelButtonText:options.cancelButtonText||'取消'
    }).then(function (e) {
        options.then && options.then(e);
    }).catch(swal.noop);
};
/**
 * 上传操作
 * @param options
 */
$.tale.prototype.post = function(options){
    var self = this;
    $.ajax({
        type:'POST',
        url:options.url,
        data:options.data || {},
        async:options.async || false,
        dataType:'json',
        success:function(result){
            // self.hideLoading();
            options.success && options.success(result);
        },
        error:function () {

        }
    });
};