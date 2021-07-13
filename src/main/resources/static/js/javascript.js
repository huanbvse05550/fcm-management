/**
 * 
 */
var checkBoxStack = [];
function myFunction(item) {
alert(item);
	var editButton = document.getElementById("edit");
	var resendButton = document.getElementById("resend");
	var itemId = item.value;
	alert(itemId);
	if (item.checked == true) {
		if (!checkBoxStack.includes(itemId)) {
			checkBoxStack.push(itemId);
		}
	} else {
		removeItem(itemId);
	}
	if (checkBoxStack.length >= 1) {
		if (checkBoxStack.length == 1) {
			editButton.disabled = false;
		} else {
			editButton.disabled = true;
		}
		resendButton.disabled = false;
	} else {
		editButton.disabled = true;
		resendButton.disabled = true;
	}
}
function removeItem(value) {
	checkBoxStack.splice(checkBoxStack.indexOf(value), 1);
}

function onclickResendButton() {
	var inputHidden = document.getElementById("messageId");
	inputHidden.value = "["  + checkBoxStack + "]";
	var resendForm = document.getElementById("resendForm");
	resendForm.submit();
}