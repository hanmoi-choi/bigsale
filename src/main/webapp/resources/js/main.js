<script type="text/javascript" src="jquery.js"></script>

function doSearchItemWithAjax() {
    $.ajax({
        url:'searchItemWithAjax.html',
        data:{item:$('#item').getText()},

        success:function (data) {
            $('#item').setData(data);
        }
    });
}
