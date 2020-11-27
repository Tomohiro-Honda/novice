/**
 * 新規登録フォーム用JS
 */

'use strict';
	function CheckPassword(confirm){
		// 入力値取得
		const input1 = password.value;
		const input2 = confirm.value;
		// パスワード比較
		if(input1 != input2){
			confirm.setCustomValidity("パスワードが一致しません。");
		}else{
			confirm.setCustomValidity('');
		}
	}
