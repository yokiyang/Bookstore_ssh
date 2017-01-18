function selectAll(obj) {
	var cks = document.getElementsByName("itemCheck");
	if (obj.checked) {
		for ( var i in cks) {
			cks[i].checked = true;
		};
	} else {
		for ( var i in cks) {
			cks[i].checked = false;
		};
	};
};

function removeCart() {

	var ids = "";
	var count = 0;
	var cks = document.getElementsByName("itemCheck");

	var flag = window.confirm("确定删除吗!");
	if (!flag) {
		return;
	}
	for ( var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			ids += cks[i].value + ":";
			count++;
		};
	}
	if (count == 0) {
		alert("至少选中一个商品.");
		return;
	}
	//通过ajax来删除
	var xmlHttp;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("get", "/bms/cart/"+ids+"/removecart.do", true);
	xmlHttp.send();

	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.status == 200) {
			if (xmlHttp.readyState == 4) {
				//请求成功,接收返回的数据:
				//格式:成功的标志0或1
				//购物车的总数量和总价格
				var str = xmlHttp.responseText;
				var ids = str.split(":");
				if (ids[0] == "1") {
					document.getElementById("itemCount").innerHTML = ids[1];
					document.getElementById("allPrice").innerHTML = ids[2];
					document.getElementById("allPriceA").innerHTML = ids[2];
					var tb = document.getElementsByTagName("tbody")[0];
					for ( var i = cks.length - 1; i >= 0; i--) {
						if (cks[i].checked) {
							tb.removeChild(cks[i].parentNode.parentNode);
						}
						;
					}
					alert("删除成功!");
				} else{
					alert("删除失败!");
				}
			};
		};
	};
}

function modifyItem(id, obj, vs) {
	var count = obj.value;
	if (/^[1-9]\d*$/.test(count)) {
		var xmlHttp;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlHttp.open("get", "/bms/cart/"+id+"/"+count+"/modifyCart.do", true);
		xmlHttp.send();

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.status == 200) {
				if (xmlHttp.readyState == 4) {
					//请求成功,接收返回的数据:
					//格式:成功的标志0或1
					//购物车的总数量和总价格
					var str = xmlHttp.responseText;
					var arr = str.split(":");
					if (arr[0] == "1") {
						document.getElementById("itemCount").innerHTML = arr[1];
						document.getElementById("allPrice").innerHTML = arr[2];
						document.getElementById("allPriceA").innerHTML = arr[2];
						document.getElementById(vs).innerHTML = arr[3];
						alert("修改成功!");
					} else {
						alert("修改失败!");
					};
				};
			};
		};
	}else{
		alert("数字格式不对!!!");
	}
}
function clearCart() {
	var cks = document.getElementsByName("itemCheck");

	var flag = window.confirm("确定清空吗!");
	if (!flag) {
		return;
	}
	//通过ajax来清空
	var xmlHttp;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("get", "/bms/cart/clearCart.do", true);
	xmlHttp.send();

	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.status == 200) {
			if (xmlHttp.readyState == 4) {
				//请求成功,接收返回的数据:
				//格式:成功的标志0或1
				var str = xmlHttp.responseText;
				var arr = str.split(":");
				if (arr[0] == "1") {
					document.getElementById("itemCount").innerHTML = 0;
					document.getElementById("allPrice").innerHTML = 0.0;
					document.getElementById("allPriceA").innerHTML = 0.0;
					var tb = document.getElementsByTagName("tbody")[0];
					for ( var i = cks.length - 1; i >= 0; i--) {
						tb.removeChild(cks[i].parentNode.parentNode);
					}
					document.getElementById("checkAll").checked = false;
					alert("清空成功!");
				} else {
					alert("清空失败!");
				}
			};
		};
	};
};

function balanceItem() {

	var count = 0;
	var ids = "";
	var cks = document.getElementsByName("itemCheck");
	var flag = window.confirm("确定结算吗!");
	if (!flag) {
		return;
	}
	for ( var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			ids += cks[i].value + ":";
			count++;
		};
	}
	if(count > 0){
		window.location="/bms/permission/order/1/3/balance.do?ids="
			+ ids.substring(0, ids.length - 1);
	}else{
		alert("至少选中一个!");
		return;
	}
}