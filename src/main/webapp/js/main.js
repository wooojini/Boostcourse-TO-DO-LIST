const JS_TODO_CARDS_CLASS = '.js-todo-cards__todo';
const JS_DOING_CARDS_CLASS = '.js-todo-cards__doing';
const JS_DONE_CARDS_CLASS = '.js-todo-cards__done';
const JS_MOVE_BUTTON_CLASS = 'js-btn-move';
const JS_CARD_INFO_CLASS = "js-todo-cards__card__info";

const TYPE_TODO = 'TODO';
const TYPE_DOING = 'DOING';
const TYPE_DONE = 'DONE';

var todos = null;
var doings = null;
var dones = null;

window.onload = function () {
    init()
    initEvent();
}

function init() {
    todos = document.querySelector(JS_TODO_CARDS_CLASS);
    doings = document.querySelector(JS_DOING_CARDS_CLASS);
    dones = document.querySelector(JS_DONE_CARDS_CLASS);
}

function initEvent() {
    todos.addEventListener("click", moveButtonEventListener);
    doings.addEventListener("click", moveButtonEventListener);
}

function moveButtonEventListener(event) {
    if (hasClass(event.target, JS_MOVE_BUTTON_CLASS)) {
    	moveCardNextType(event.target);
    }
}

function hasClass(node, classNameToFind) {
	var result = false;
    var classNames = node.getAttribute('class');
    
    if (classNames != null) {
        classNames.split(' ').forEach(className => {
            if (className === classNameToFind) {
            	result = true;
            }	
        });
    }
    
    return result;
}

//to-do 리스트 카드를 다음 상태로 이동하는 함수.
function moveCardNextType(moveButton) {
	var card = moveButton.parentNode;
    var cardInfo = getChildNodeByClassName(card, JS_CARD_INFO_CLASS);
    
    if (cardInfo != null) {
    	var id = cardInfo.dataset.id;
    	var type = cardInfo.dataset.type;
    	var httpMethod = 'PUT';
    	var url = 'http://localhost:8080/todolist/todos/?id='+id+'&type='+type;
    	
    	ajax(httpMethod, url, function(){	
    		if(this.status === 200){
	    		moveCard(card,type,cardInfo);
	    		
	    		if(type === TYPE_DOING){
	    			removeMoveButton(card, moveButton);
	    		}
    		}
    	});
    }
}

function getChildNodeByClassName(parent, className){
	var childNode = null;
	
	parent.childNodes.forEach(node => {
		if(node.nodeType === Node.ELEMENT_NODE 
				&& hasClass(node, className)){
			childNode = node;
		}
	});
	
	return childNode;
}

function ajax(httpMethod, url, callback){
	var oReq = new XMLHttpRequest();
	oReq.addEventListener('load', callback);
	oReq.open(httpMethod,url);
	oReq.send();
}

function moveCard(card, type, cardInfo){
	var parent;
	
	switch(type){
	case TYPE_TODO:
		parent = doings;
		cardInfo.dataset.type = TYPE_DOING;
		
		break;
	case TYPE_DOING:
		parent = dones;
		cardInfo.dataset.type = TYPE_DONE;
		
		break;
	}
	
	parent.appendChild(card);
}

function removeMoveButton(card, moveButton){
	card.removeChild(moveButton);
}