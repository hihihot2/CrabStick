/*
 * ver 1.1 makeObjectAtFirst() 추가
 * ver 1.2 makeObjectBeforeTarget() 추가
 */

/**
 * @param
 * 첫번째 argument: 부모가 될 객체의 ID(String) 또는 객체(Node타입의 Object)
 * 두번째 argument: 새로 만들 객체의 ID(String)
 * 세번째 argument: 새로 만들 객체의 태그 타입
 * 네번째 argument: 객체에 innerHTMl로 들어갈 내용
 * 다섯번째 이상 argument: 객체에 옵션으로 지정할 값 배열 ['속성 타입(String)', '속성에 할당할 값(String)'], ..[]
 * @return 생성된 객체(Node타입 Object) 반환
 */
function makeObject() {
	var length = arguments.length;
	var target = arguments[0];
	var newID = arguments[1];
	var type = arguments[2];
	var content = arguments[3];

	var obj = document.createElement(type);
	if(newID != null) {
		obj.setAttribute('id', newID);
	}

	// 추가 option과 관련된 arguments가 존재하면
	if(length > 4) {
		for(var i = 4; i < length; i++) {
			obj.setAttribute(arguments[i][0], arguments[i][1])
		}
	}

	obj.innerHTML = content;
	if(typeof target == 'string') {
		var targetObject = document.getElementById(target);
		targetObject.appendChild(obj)
	} else if(typeof target == 'object') {
		target.appendChild(obj)
	}
	
	return obj
}

/**
 * @param
 * 첫번째 argument: 부모가 될 객체의 ID(String) 또는 객체(Node타입의 Object)
 * 두번째 argument: 새로 만들 객체의 ID(String)
 * 세번째 argument: 새로 만들 객체의 태그 타입
 * 네번째 argument: 객체에 innerHTMl로 들어갈 내용
 * 다섯번째 이상 argument: 객체에 옵션으로 지정할 값 배열 ['속성 타입(String)', '속성에 할당할 값(String)'], ..[]
 * @return 생성된 객체(Node타입 Object) 반환
 */
function makeObjectAtFirst() {
	var length = arguments.length;
	var target = arguments[0];
	var newID = arguments[1];
	var type = arguments[2];
	var content = arguments[3];

	var obj = document.createElement(type);
	if(newID != null) {
		obj.setAttribute('id', newID);
	}

	// 추가 option과 관련된 arguments가 존재하면
	if(length > 4) {
		for(var i = 4; i < length; i++) {
			obj.setAttribute(arguments[i][0], arguments[i][1])
		}
	}

	obj.innerHTML = content;
	if(typeof target == 'string') {
		var targetObject = document.getElementById(target);
		var firstChild = targetObject.firstChild;
		targetObject.insertBefore(obj, firstChild);
	} else if(typeof target == 'object') {
		target.appendChild(obj)
		var firstChild = target.firstChild;
		target.insertBefore(obj, firstChild);
	}
	
	return obj
}

/**
 * @param
 * 첫번째 argument: 추가될 객체의 바로 뒤 형제 객체(트리구조에서 같은 레벨)의 ID(String) 또는 객체(Node타입의 Object)
 * 두번째 argument: 새로 만들 객체의 ID(String)
 * 세번째 argument: 새로 만들 객체의 태그 타입
 * 네번째 argument: 객체에 innerHTMl로 들어갈 내용
 * 다섯번째 이상 argument: 객체에 옵션으로 지정할 값 배열 ['속성 타입(String)', '속성에 할당할 값(String)'], ..[]
 * @return 생성된 객체(Node타입 Object) 반환
 */
function makeObjectBeforeTarget() {
	var length = arguments.length;
	var target = arguments[0];
	var newID = arguments[1];
	var type = arguments[2];
	var content = arguments[3];

	var obj = document.createElement(type);
	if(newID != null) {
		obj.setAttribute('id', newID);
	}

	// 추가 option과 관련된 arguments가 존재하면
	if(length > 4) {
		for(var i = 4; i < length; i++) {
			obj.setAttribute(arguments[i][0], arguments[i][1])
		}
	}

	obj.innerHTML = content;
	if(typeof target == 'string') {
		var targetObject = document.getElementById(target);
		targetObject.parentNode.insertBefore(obj, targetObject);
	} else if(typeof target == 'object') {
		target.parentNode.insertBefore(obj, target);
	}
	
	return obj
}

/**
 * @param
 * 첫번째 argument: 부모가 될 객체의 ID(String) 또는 객체(Node타입의 Object)
 * 두번째 argument: 새로 만들 객체의 name(String)
 * 세번째 argument: 새로 만들 객체의 value(String)
 * 네번째 argument: 버튼의 메서드명(String)
 * 다섯번째 argument: 메서드의 파라미터
 * @return 생성된 객체(Node타입 Object) 반환
 */
function makeButton() {
	var target = arguments[0];
	var name = arguments[1];
	var value = arguments[2];
	var onClickMethodName = arguments[3];
	
	var param = '';
	var isAdded = false;
	for(var i = 4; i < arguments.length; i++) {
		if(isAdded) {
			param += ', ';
		}
		param += arguments[i];
		isAdded = true;
	}
	makeObject(target, null, 'input', null, ['type', 'button'], ['name', name], ['value', value], ['onclick', onClickMethodName + '('+ param +')'])
}




/**
 * @param target 부모가 될 객체의 ID(String) 또는 객체(Node타입의 Object)
 * @returns	삭제된 객체의 부모 객체(Node타입의 Object) 반환
 */
function removeObject(target) {
	var targetObject;
	if(typeof target == 'string') {
		targetObject = document.getElementById(target);
	} else if(typeof target == 'object') {
		targetObject = target;
	}
	
	var parent = targetObject.parentNode;
	parent.removeChild(targetObject);
	return parent;
}

/**
 * @param
 * 첫번째 argument: 수정될 객체의 ID(String) 또는 객체(Node타입 Object)
 * 두번째 argument: 수정될 객체에 바뀔 내용(String)
 * 세번째 argument: 객체에 옵션으로 지정할 값 배열 ['속성 타입(String)', '속성에 할당할 값(String)'], ..[]
 * @return 생성된 객체(Node타입 Object) 반환
 */
function editObject() {
	var length = arguments.length;
	var target = arguments[0];
	var content = arguments[1];
	
	var obj;
	if(typeof target == 'string') {
		obj = documet.getElementById(target);
	} else if(typeof target == 'object') {
		obj = target;
	}
	
	obj.innerHTML = content;
	
	// 추가 option과 관련된 arguments가 존재하면
	if(length > 2) {
		for(var i = 2; i < length; i++) {
			obj.setAttribute(arguments[i][0], arguments[i][1])
		}
	}
	
	return obj;
}
