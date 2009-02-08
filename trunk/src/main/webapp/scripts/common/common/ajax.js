var ajax = {};

ajax.Request = function(url, params, callback, method, applyObj) {
	this.url = url;			
	this.params = params;	
	this.callback = callback;	
	this.method = method;	
	this.applyObj = (applyObj == null) ? null : applyObj;	
	
	this.send();
}

ajax.Request.prototype = {
	
	
	send: function() {
		this.req = this.getXMLHttpRequest();
		
		var httpMethod = this.method ? this.method : 'GET';
		if (httpMethod != 'GET' && httpMethod != 'POST') {
			httpMethod = 'GET';
		}
		var httpParams = (this.params == null || this.params == '') 
		? null : this.params;
		var httpUrl = this.url;
		if (httpMethod == 'GET' && httpParams != null) {
			httpUrl = httpUrl + "?" + httpParams;
		}
		this.req.open(httpMethod, httpUrl, true);
		this.req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		var request = this;
		this.req.onreadystatechange = function() {
			request.onStateChange.call(request);
		}
		this.req.send(httpMethod == 'POST' ? httpParams : null);
	},
	
	getXMLHttpRequest: function() {
		if (window.ActiveXObject) {
			try {
				return new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				try {
					return new ActiveXObject("Microsoft.XMLHTTP");
				} catch(e1) { return null; }
			}
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else {
			return null;
		}		
	},
	
	onStateChange: function() {
		if (this.applyObj) {
			this.callback.call(this.applyObj, this.req);
		} else {
			this.callback(this.req);
		}
	}
}