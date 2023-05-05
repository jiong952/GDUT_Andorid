
window.alert = function(msg, callback) {
	var div = document.createElement("div");
	div.innerHTML = "<style type=\"text/css\">"
        + ".nbaMask { position: fixed; z-index: 1000; top: 0; right: 0; left: 0; bottom: 0; background: rgba(0, 0, 0, 0.5); }"
        + ".nbaMaskTransparent { position: fixed; z-index: 1000; top: 0; right: 0; left: 0; bottom: 0; }"
        + ".nbaDialog { position: fixed; z-index: 5000; width: 80%; max-width: 300px; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: #fff; text-align: center; border-radius: 8px; overflow: hidden; opacity: 1; color: #333; }"
        + ".nbaDialog .nbaDialogHd { padding: 15px; border-bottom: 1px solid #e6e6e6; }"
        + ".nbaDialog .nbaDialogHd .nbaDialogTitle { font-size: 18px; font-weight: bold; margin: 0; }"
        + ".nbaDialog .nbaDialogBd { padding: 20px; font-size: 16px; line-height: 1.5; word-wrap: break-word; word-break: break-all; color: #333; }"
        + ".nbaDialog .nbaDialogFt { display: flex; border-top: 1px solid #e6e6e6; }"
        + ".nbaDialog .nbaDialogBtn { display: flex; flex: 1; justify-content: center; align-items: center; color: #007AFF; text-decoration: none; -webkit-tap-highlight-color: transparent; }"
        + ".nbaDialog .nbaDialogBtn:first-child { border-right: 1px solid #e6e6e6; }"
        + ".nbaDialog a { text-decoration: none; -webkit-tap-highlight-color: transparent; }"
        + "</style>"
        + "<div id=\"dialogs2\" style=\"display: none\">"
        + "<div class=\"nbaMask\"></div>"
        + "<div class=\"nbaDialog\">"
        + "    <div class=\"nbaDialogHd\">"
        + "        <h3 class=\"nbaDialogTitle\"></h3>"
        + "    </div>"
        + "    <div class=\"nbaDialogBd\" id=\"dialog_msg2\">弹窗内容，告知当前状态、信息和解决方法，描述文字尽量控制在三行内</div>"
        + "    <div class=\"nbaDialogFt\">"
        + "        <a href=\"javascript:;\" class=\"nbaDialogBtn nbaDialogBtnPrimary\" id=\"dialog_ok2\">确定</a>"
        + "    </div>"
        + "</div>"
        + "</div>";

	document.body.appendChild(div);
 
	var dialogs2 = document.getElementById("dialogs2");
	dialogs2.style.display = 'block';
 
	var dialog_msg2 = document.getElementById("dialog_msg2");
	dialog_msg2.innerHTML = msg;
 
	// var dialog_cancel = document.getElementById("dialog_cancel");
	// dialog_cancel.onclick = function() {
	// dialogs2.style.display = 'none';
	// };
	var dialog_ok2 = document.getElementById("dialog_ok2");
	dialog_ok2.onclick = function() {
		dialogs2.style.display = 'none';
		callback();
	};
};